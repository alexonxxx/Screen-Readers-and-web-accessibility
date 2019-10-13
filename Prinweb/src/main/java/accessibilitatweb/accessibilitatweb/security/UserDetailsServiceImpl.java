package accessibilitatweb.accessibilitatweb.security;

import accessibilitatweb.accessibilitatweb.domain.Usuari;
import accessibilitatweb.accessibilitatweb.repositories.UsuariRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UsuariRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String nomUsuari) throws UsernameNotFoundException {
        Usuari user = userRepository.findByNomUsuari(nomUsuari);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRol()));


        return new org.springframework.security.core.userdetails.User(user.getNomUsuari(), user.getPassword(), grantedAuthorities);
    }
}