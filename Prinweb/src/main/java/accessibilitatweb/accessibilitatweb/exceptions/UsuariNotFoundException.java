package accessibilitatweb.accessibilitatweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuariNotFoundException extends RuntimeException{
    public UsuariNotFoundException() {
        //pendent capturar
        super("El usuario o la contraseña són incorrectos.\n");

    }
}
