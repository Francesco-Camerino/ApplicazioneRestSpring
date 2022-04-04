package it.devlecce.ApplicazioneRestSpring.configurazione;

import it.devlecce.ApplicazioneRestSpring.model.Utente;
import it.devlecce.ApplicazioneRestSpring.persistenza.UtentiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class PrimoInserimento {
    private static Logger logger = LoggerFactory.getLogger(PrimoInserimento.class); //si può anche aggiungere final
    @Bean
    CommandLineRunner inserisciElementi(UtentiRepository repository) {
        return args -> {
            Utente u1 = new Utente("Daniele", "Carratta");
            Utente u2 = new Utente("Maddalena", "Corvaglia");
            Utente u3 = new Utente("Enrico", "Pratticò");
            List<Utente> utenti = new ArrayList<>();
            Utente u1DelDB = new Utente();
            utenti.add(u2);
            utenti.add(u3);
            repository.save(u1);
            repository.saveAll(utenti);
           List<Utente> utentiDalDB = repository.findAll();

           int indice = 0;
           for(Utente u : utentiDalDB) {
               if(indice == 0) {
                   logger.error("Prendo il primo elemento del DB");
                   u1DelDB = u;
               }
               logger.info("Nome: "+u.getNome());
               logger.info("Cognome: "+u.getCognome());
               logger.warn(u.toString());
               indice++;
           }
           Utente utenteConID1 = repository.findById(1L).get();
           logger.info("Utente con id 1: "+utenteConID1.getNome());
           u1DelDB.setCognome("Nuovo Cognome");
           repository.save(u1DelDB);

           logger.error("Sto per fare una delete");
           repository.delete(u2);
           utentiDalDB = repository.findAll();
            for(Utente u : utentiDalDB) {
                logger.info("Nome: "+u.getNome());
                logger.info("Cognome: "+u.getCognome());
                logger.warn(u.toString());
                indice++;
            }

        };
    }
}
