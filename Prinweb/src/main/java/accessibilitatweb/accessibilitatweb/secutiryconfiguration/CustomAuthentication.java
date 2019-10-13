package accessibilitatweb.accessibilitatweb.secutiryconfiguration;

import accessibilitatweb.accessibilitatweb.services.UsuariUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;


public class CustomAuthentication implements AuthenticationSuccessHandler {

    @Autowired
    private UsuariUseCase usuariUseCase;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws
            IOException, ServletException {

        User principal = (User) authentication.getPrincipal();
        boolean isAdmin = false;
        //this.usuariUseCase.logejarUsuari(principal.getUsername(),principal.getPassword());

        Iterator<GrantedAuthority> grantedAuthorityIterator = principal.getAuthorities().iterator();
        while (grantedAuthorityIterator.hasNext()) {
            if (grantedAuthorityIterator.next().getAuthority().equalsIgnoreCase("ADMIN")) {
                isAdmin = true;
            }
        }
        if (isAdmin) {
            response.sendRedirect("/admin");
        } else {
            response.sendRedirect("/");
        }
    }
}