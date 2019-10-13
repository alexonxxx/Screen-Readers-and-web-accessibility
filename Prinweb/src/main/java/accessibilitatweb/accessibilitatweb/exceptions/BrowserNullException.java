package accessibilitatweb.accessibilitatweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BrowserNullException extends RuntimeException{

    public BrowserNullException() {

        super("Seleccione al menos un navegador.");
    }
}
