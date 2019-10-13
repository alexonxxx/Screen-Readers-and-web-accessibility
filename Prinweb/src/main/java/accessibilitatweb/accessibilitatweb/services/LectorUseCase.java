package accessibilitatweb.accessibilitatweb.services;

import accessibilitatweb.accessibilitatweb.domain.Comanda;
import accessibilitatweb.accessibilitatweb.domain.LectorPantalla;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import accessibilitatweb.accessibilitatweb.exceptions.LectorPantallaAlredyExist;
import accessibilitatweb.accessibilitatweb.repositories.LectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class LectorUseCase {
   @Autowired
   UsuariUseCase usuariUseCase;

    @Autowired
    private LectorRepository lectorRepository;

    private Long lectorActualId;
    public LectorUseCase(LectorRepository lectorRepository, UsuariUseCase usuariUseCase) {
        this.usuariUseCase=usuariUseCase;
        this.lectorRepository=lectorRepository;
    }
    public List<LectorPantalla> getAllLectors() {
        return this.lectorRepository.findAll();
    }

    public LectorPantalla getLectorActual(Long lectorId) {
        Optional<LectorPantalla> lectorActual= this.lectorRepository.findById(lectorId);
        this.lectorActualId=lectorId;
        return lectorActual.get();
    }
    public Usuari getUsuariActual(){
       return this.usuariUseCase.getUsuari();
    }

    public LectorPantalla getLectorActualByName(String nomLector) {
        LectorPantalla lectorPantalla=this.lectorRepository.findLectorPantallaByNomLector(nomLector);

        return lectorPantalla;
    }
    public void afegirLector(LectorPantalla lectorPantalla) throws Exception{
        Usuari usuari= this.usuariUseCase.getUsuari();
        usuari.afegirLectorPantalla(lectorPantalla);
        if(lectorRepository.findLectorPantallaByNomLector(lectorPantalla.getNomLector().toUpperCase())!=null){
            throw new LectorPantallaAlredyExist(lectorPantalla.getNomLector());
        }
        else
            this.usuariUseCase.guardarUsuari(usuari);
    }

    public void editarLector(LectorPantalla dadesLector,LectorPantalla lectorActual) throws Exception{
        Usuari usuari= this.usuariUseCase.getUsuari();
        usuari.editarLector(dadesLector,lectorActual.getId());
        this.usuariUseCase.guardarUsuari(usuari);

    }

    public void eliminarLector(Long lectorId) {
        Usuari usuari=this.usuariUseCase.getUsuari();

        usuari.eliminarLector(lectorId);
        this.usuariUseCase.guardarUsuari(usuari);
        this.lectorRepository.deleteById(lectorId);
    }

    public List<String> getRutesSistemes(List<String> sistemes){
        LinkedList<String> rutaSistemes=new LinkedList<>();
        for (String sistema:sistemes){
            switch (sistema.toUpperCase()) {
                case "WINDOWS":
                    rutaSistemes.add("windowslogo.png");
                    break;
                case "MAC OS":
                    rutaSistemes.add("applelogo.png");
                    break;
                case "IOS":
                    rutaSistemes.add("ioslogo.png");
                    break;
                case "ANDROID":
                    rutaSistemes.add("androidlogo.png");
                    break;
                case "LINUX":
                    rutaSistemes.add("androidlogo.png");
                default: break;
            }
        }
        return rutaSistemes;
    }
    public List<String> getRutesNavegadors(List<String> navegadors){
        LinkedList<String> rutaNavegadors=new LinkedList<>();
        for (String navegador:navegadors){
            switch (navegador.toUpperCase()) {
                case "GOOGLE CHROME":
                    rutaNavegadors.add("chromelogo.png");
                    break;
                case "INTERNET EXPLORER":
                    rutaNavegadors.add("explorerlogo.jpg");
                    break;
                case "FIREFOX":
                    rutaNavegadors.add("firefoxlogo.png");
                    break;
                case "SAFARI":
                    rutaNavegadors.add("safarilogo.png");
                    break;
                case "OPERA":
                    rutaNavegadors.add("operalogo.png");
                default: break;
            }
        }
        return rutaNavegadors;

    }
    public Object getSistemesLector(LectorPantalla lectorActual) {

        return lectorActual.getSistemesOperatius();
    }
    public Object getNavegadorsLector(LectorPantalla lectorActual) {

        return lectorActual.getNavegadors();
    }

    public Object getComandesLector(LectorPantalla lectoractual) {
        return lectoractual.getComandes();
    }

    public void eliminarComanda(Long comandaId) {
        Usuari usuari= this.usuariUseCase.getUsuari();
        usuari.eliminarComanda(this.lectorActualId,comandaId);
        this.usuariUseCase.guardarUsuari(usuari);

    }

    public Optional<LectorPantalla> getLectorActualById() {
        return this.lectorRepository.findById(this.lectorActualId);
    }


    public void afegirComanda(Comanda comanda) throws Exception {
        Usuari usuari= this.usuariUseCase.getUsuari();
        usuari.afegirComanda(this.lectorActualId,comanda);
        this.usuariUseCase.guardarUsuari(usuari);
    }


}
