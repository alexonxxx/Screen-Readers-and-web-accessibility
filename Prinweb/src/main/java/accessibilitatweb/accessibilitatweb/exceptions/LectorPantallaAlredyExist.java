package accessibilitatweb.accessibilitatweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class LectorPantallaAlredyExist extends RuntimeException {

    //pendent capturar
        public LectorPantallaAlredyExist(String nomLector) {
            //es llençara la excepcio al guardar el lector/ ja que cada un és unic
            super("El lector de pantalla" + nomLector + "ya existe.");
        }
    }

