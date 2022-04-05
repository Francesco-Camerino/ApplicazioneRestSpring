package it.devlecce.ApplicazioneRestSpring.avviso;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class UtenteNonTrovatoRisposta {
    @ResponseBody
    @ExceptionHandler(UtenteNonTrovato.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String utenteNonTrovato(UtenteNonTrovato ex) {
        return "Eccezione: " + ex.getMessage();
    }

}
