package accessibilitatweb.accessibilitatweb.repositories;

import accessibilitatweb.accessibilitatweb.domain.AvaluacioPersonal;
import accessibilitatweb.accessibilitatweb.domain.LectorPantalla;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaluacioRepository extends JpaRepository<AvaluacioPersonal, Long> {

    List<AvaluacioPersonal> findByTaula(String taula);

}