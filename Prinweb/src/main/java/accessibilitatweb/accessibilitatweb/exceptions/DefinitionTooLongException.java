package accessibilitatweb.accessibilitatweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DefinitionTooLongException extends RuntimeException{

    //pendent capturar
        public DefinitionTooLongException() {

            super("La definición és demasiado larga.");
        }


}
