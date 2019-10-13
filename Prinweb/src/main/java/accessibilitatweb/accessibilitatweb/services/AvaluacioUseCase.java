package accessibilitatweb.accessibilitatweb.services;


import accessibilitatweb.accessibilitatweb.domain.AvaluacioPersonal;
import accessibilitatweb.accessibilitatweb.domain.Comanda;
import accessibilitatweb.accessibilitatweb.domain.LectorPantalla;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import accessibilitatweb.accessibilitatweb.exceptions.LectorPantallaAlredyExist;
import accessibilitatweb.accessibilitatweb.repositories.AvaluacioRepository;
import accessibilitatweb.accessibilitatweb.repositories.LectorRepository;
import accessibilitatweb.accessibilitatweb.repositories.UsuariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AvaluacioUseCase {
    @Autowired
    UsuariUseCase usuariUseCase;

    @Autowired
    private AvaluacioRepository avaluacioRepository;

    private Long lectorActualId;
    public AvaluacioUseCase(AvaluacioRepository avaluacioRepository, UsuariUseCase usuariUseCase) {
        this.usuariUseCase=usuariUseCase;
        this.avaluacioRepository=avaluacioRepository;
    }



    public AvaluacioPersonal getAvaluacioActual(Long avaluacioId) {
        Optional<AvaluacioPersonal> avaluacioPersonal= this.avaluacioRepository.findById(avaluacioId);
        return avaluacioPersonal.get();
    }
    public Usuari getUsuariActual(){
        return this.usuariUseCase.getUsuari();
    }


    public void afegirAvaluacio(AvaluacioPersonal avaluacioPersonal) throws Exception{
        Usuari usuari= this.usuariUseCase.getUsuari();
        avaluacioPersonal.setUsuari( usuari.getNomUsuari());
        usuari.afegirAvaluacio(avaluacioPersonal);
        this.usuariUseCase.guardarUsuari(usuari);
    }

    public void editarAvaluacio(AvaluacioPersonal dadesAvaluacio,AvaluacioPersonal avaluacioActual) {
        Usuari usuari= this.usuariUseCase.getUsuari();
        usuari.editarAvaluacio(dadesAvaluacio,avaluacioActual.getId());
        this.usuariUseCase.guardarUsuari(usuari);

    }

    public void eliminarAvaluacio(Long avaluacioId) {
        Usuari usuari=this.usuariUseCase.getUsuari();
        usuari.eliminarAvaluacio(avaluacioId);
        this.usuariUseCase.guardarUsuari(usuari);
        this.avaluacioRepository.deleteById(avaluacioId);
    }




    public List<AvaluacioPersonal> getAllavaluacionsByTaula(String taula) {

        return this.avaluacioRepository.findByTaula(taula);
    }
}
