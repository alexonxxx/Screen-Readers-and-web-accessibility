package accessibilitatweb.accessibilitatweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class SystemsNullException extends RuntimeException{

    public SystemsNullException() {

        super("Seleccione al menos un sistema.");
    }
}
