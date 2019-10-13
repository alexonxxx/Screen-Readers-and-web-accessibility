package accessibilitatweb.accessibilitatweb.services;


import accessibilitatweb.accessibilitatweb.domain.AvaluacioPersonal;
import accessibilitatweb.accessibilitatweb.domain.LectorPantalla;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import accessibilitatweb.accessibilitatweb.exceptions.EmailAlreadyExistsException;
import accessibilitatweb.accessibilitatweb.exceptions.UsuariAlreadyExistException;
import accessibilitatweb.accessibilitatweb.repositories.LectorRepository;
import accessibilitatweb.accessibilitatweb.repositories.UsuariRepository;
import accessibilitatweb.accessibilitatweb.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariUseCase {

    @Autowired
    private UsuariRepository usuariRepository;

    @Autowired
    private LectorRepository lectorRepository;

    @Autowired
    private SecurityService securityService;





    public UsuariUseCase(LectorRepository lectorRepository, UsuariRepository usuariRepository) {
        this.usuariRepository= usuariRepository;
        this.lectorRepository=lectorRepository;
    }


    public Usuari getUsuariByNomUsuari(String nomUsuari){
        return this.usuariRepository.findByNomUsuari(nomUsuari);
    }

    public void registrarUsuari(Usuari usuari, String password ) throws Exception {


        if(usuariRepository.findByNomUsuari(usuari.getNomUsuari())!=null){
            throw new UsuariAlreadyExistException(usuari.getNomUsuari());
        }else {
            try {
                usuariRepository.save(usuari);
            } catch (Exception e) {

                throw new EmailAlreadyExistsException();

            }
            securityService.login(usuari.getNomUsuari(), password);
        }


    }

    public Usuari getUsuari(){
        String nomUsuari=this.securityService.findLoggedInUsername();
        if(nomUsuari!="anonymousUser"){
            return  getUsuariByNomUsuari(nomUsuari);

        }
        return null;
    }
    public void guardarUsuari(Usuari usuari){
        this.usuariRepository.save(usuari);
    }



    public List<LectorPantalla> getLectorsUsuari(){
        Usuari usuari= getUsuari();
        return usuari.getLectorsPantalla();
    }
    public List<AvaluacioPersonal> getAvaluacionsUsuari(){
        Usuari usuari= getUsuari();
        return usuari.getAvaluacionsPersonals();
    }



}
