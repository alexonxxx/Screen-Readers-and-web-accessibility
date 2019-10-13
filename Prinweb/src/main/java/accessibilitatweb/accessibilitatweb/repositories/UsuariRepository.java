package accessibilitatweb.accessibilitatweb.repositories;

import accessibilitatweb.accessibilitatweb.domain.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuariRepository extends JpaRepository<Usuari, Long> {

    Usuari findByNomUsuari(String nom);

}