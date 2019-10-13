package accessibilitatweb.accessibilitatweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CommandAlreadyExists extends RuntimeException {

    public CommandAlreadyExists(String comanda) {

        super("La comanda " + comanda + " ya existe para este lector.");
    }
}
