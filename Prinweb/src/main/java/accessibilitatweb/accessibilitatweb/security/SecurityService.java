package accessibilitatweb.accessibilitatweb.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


    @Service
    public class SecurityService {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private UserDetailsServiceImpl userDetailsService;

        public SecurityService(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
            this.authenticationManager = authenticationManager;
            this.userDetailsService = userDetailsService;
        }

        public static String findLoggedInUsername() {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            return auth.getName();
        }

        public void login(String username, String password) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            if (usernamePasswordAuthenticationToken.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }

        }

    }
