package it.devlecce.ApplicazioneRestSpring.controller;

import it.devlecce.ApplicazioneRestSpring.avviso.UtenteNonTrovato;
import it.devlecce.ApplicazioneRestSpring.model.Utente;
import it.devlecce.ApplicazioneRestSpring.persistenza.UtentiRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UtenteRestController {
    private static Logger logger = LoggerFactory.getLogger(UtenteRestController.class);
    private UtentiRepository repository;

    UtenteRestController(UtentiRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/utenti")
    public List<Utente> leggiTuttiGliUtenti() {
        logger.info("Prendo tutti gli utenti");
        return repository.findAll();
    }

    @GetMapping("/utente/{id}")
    public Utente trovaUtenteConID(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UtenteNonTrovato(id));
    }

    @GetMapping("/utente/ricercatradate")
    public List<Utente> ricercaUtenteTraDate(
            @RequestParam(name = "datada") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date datada,
            @RequestParam(name = "dataa") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date dataa
    ) {
        return repository.findByDatadinascitaBetween(datada, dataa);
    }

    @PostMapping("/utente")
    public Utente inserisciUnNuovoUtente(@RequestBody Utente nuovoUtente) {
        return repository.save(nuovoUtente);
    }

    @PutMapping("/utente/{id}")
    public Utente aggiornaDatiUtente(@PathVariable Long id,
                                     @RequestBody Utente utente) {
        return repository.findById(id).map(
                /* crea un nuovo utente */
                nuovoUtente -> {
                    nuovoUtente.setNome(utente.getNome());
                    nuovoUtente.setCognome(utente.getCognome());
                    return repository.save(nuovoUtente);
                }
        ).orElseGet(
                () -> {
                    utente.setId(id);
                    return repository.save(utente);
                }

        );
    }

    @PutMapping("/utenteput")
     public Utente aggiornaSingoloUtente(@RequestBody Utente utente) {
         return repository.save(utente);
     }
    @DeleteMapping("/utente/{id}")
    void eliminaUtente(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
