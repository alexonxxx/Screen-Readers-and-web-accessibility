package accessibilitatweb.accessibilitatweb.repositories;

import accessibilitatweb.accessibilitatweb.domain.LectorPantalla;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectorRepository extends JpaRepository<LectorPantalla, Long> {

    LectorPantalla findLectorPantallaByNomLector(String nomLector);
    Optional<LectorPantalla> findById(Long id);

}