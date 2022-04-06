package it.devlecce.ApplicazioneRestSpring.persistenza;

import it.devlecce.ApplicazioneRestSpring.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface UtentiRepository extends JpaRepository<Utente, Long> {
    List<Utente> findByDatadinascitaBetween(Date datada, Date dataa);
    List<Utente> findByDatadiregistrazioneBetween(Date datada, Date dataa);
    List<Utente> findByRankingBetween(float min, float max);
    List<Utente> findByRankingLessThan(float max);
}
