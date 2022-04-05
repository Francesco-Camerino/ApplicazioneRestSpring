package it.devlecce.ApplicazioneRestSpring.avviso;

public class UtenteNonTrovato extends RuntimeException{

    public UtenteNonTrovato(Long id) {
        super("Utente non trovato" + id);
    }
}
