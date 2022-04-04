package it.devlecce.ApplicazioneRestSpring.persistenza;

import it.devlecce.ApplicazioneRestSpring.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtentiRepository extends JpaRepository<Utente, Long> {


}
