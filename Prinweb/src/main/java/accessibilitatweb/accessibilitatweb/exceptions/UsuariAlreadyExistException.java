package accessibilitatweb.accessibilitatweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ResponseStatus(HttpStatus.CONFLICT)
public class UsuariAlreadyExistException extends RuntimeException{

        public UsuariAlreadyExistException(String nomUsuri) {
            //es llençara la excepcio al guardar el lector/ ja que cada un és unic
            super("El usuario " + nomUsuri + " ya existe.");
        }
    }

