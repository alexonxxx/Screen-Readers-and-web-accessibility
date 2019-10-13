package accessibilitatweb.accessibilitatweb.webcontroller;

import accessibilitatweb.accessibilitatweb.services.AvaluacioUseCase;
import accessibilitatweb.accessibilitatweb.services.LectorUseCase;
import accessibilitatweb.accessibilitatweb.services.UsuariUseCase;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ComparativaController {
    @Autowired
    private UsuariUseCase usuariUseCase;

    @Autowired
    private LectorUseCase lectorUseCase;

    @Autowired
    private AvaluacioUseCase avaluacioUseCase;

    public ComparativaController(UsuariUseCase usuariUseCase, LectorUseCase lectorUseCase, AvaluacioUseCase avaluacioUseCase) {
        this.usuariUseCase = usuariUseCase;
        this.lectorUseCase = lectorUseCase;
        this.avaluacioUseCase = avaluacioUseCase;
    }

    @GetMapping("/texto-audio")
    public String textoAudio(Model model){


        if(this.usuariUseCase.getUsuari()!=null){
            model.addAttribute("usuari", this.usuariUseCase.getUsuari());
        }
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());
        model.addAttribute("listAvaluacions", this.avaluacioUseCase.getAllavaluacionsByTaula("texto a audio"));

        return "texto-audio";
    }
    @GetMapping("/visualizacion")
    public String visualicacion(Model model){

        Usuari usuari=this.usuariUseCase.getUsuari();
        if(usuari!=null){
            model.addAttribute("usuari", usuari);
        }
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());
        model.addAttribute("listAvaluacions", this.avaluacioUseCase.getAllavaluacionsByTaula("visualización"));

        return "visualizacion";
    }
    @GetMapping("/otras-funcionalidades")
    public String otrasFuncionalidades(Model model){

        Usuari usuari=this.usuariUseCase.getUsuari();
        if(usuari!=null){
            model.addAttribute("usuari", usuari);
        }
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());
        model.addAttribute("listAvaluacions", this.avaluacioUseCase.getAllavaluacionsByTaula("otras funcionalidades"));

        return "otras-funcionalidades";
    }

}
