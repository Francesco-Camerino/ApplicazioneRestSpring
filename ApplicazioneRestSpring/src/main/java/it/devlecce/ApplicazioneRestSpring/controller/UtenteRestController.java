package it.devlecce.ApplicazioneRestSpring.controller;

import it.devlecce.ApplicazioneRestSpring.avviso.UtenteNonTrovato;
import it.devlecce.ApplicazioneRestSpring.model.Utente;
import it.devlecce.ApplicazioneRestSpring.persistenza.UtentiRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
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

    @GetMapping("/utente/ricercadataregistrazione")
    public List<Utente> ricercaUtenteConDataDiRegistrazione(
            @RequestParam(name = "datada") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date datada,
            @RequestParam(name = "dataa") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date dataa
    ) {
        return repository.findByDatadiregistrazioneBetween(datada, dataa);
    }

    @GetMapping("/utente/ranking")
    public List<Utente> ricercaUtenteConRanking(
            @RequestParam(name = "min") float min,
            @RequestParam(name = "max") float max
    ) {
        return repository.findByRankingBetween(min, max);
    }

    @GetMapping("/utente/rankingmin")
    public List<Utente> ricercaUtenteConRankingMin(
            @RequestParam(name = "max") float max
    ) {
        return repository.findByRankingLessThan(max);
    }
    /* Caricamento di file */
    @PostMapping ("/caricafile")
    public  String caricaFile(@RequestParam ("file") MultipartFile file){
        String infoFile= file.getOriginalFilename() + " - "+file.getContentType();
        String conFormat = String.format("%S-%S", file.getOriginalFilename(),file.getContentType());
        logger.info((infoFile));
        logger.warn(conFormat);
        return conFormat;
    }

    @PostMapping("/utenti/csv")
     ResponseEntity<String> caricaCSV(@RequestParam("file") MultipartFile file) {
        Reader in = null;
        try {
            in = new InputStreamReader(file.getInputStream());
// Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder().build().parse(in);
            for (CSVRecord record : records) {
                String autore = record.get(0);
                logger.info("Autore: " + autore);
                String titolo = record.get(1);
                logger.warn("Titolo: " + titolo);
            }
        } catch (IOException e) {
            logger.error("Si è verificato un errore", e);
        }
        return ResponseEntity.ok("CSV");
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
