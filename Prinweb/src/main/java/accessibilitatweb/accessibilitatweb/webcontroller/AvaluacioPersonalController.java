package accessibilitatweb.accessibilitatweb.webcontroller;

import accessibilitatweb.accessibilitatweb.domain.AvaluacioPersonal;
import accessibilitatweb.accessibilitatweb.domain.LectorPantalla;
import accessibilitatweb.accessibilitatweb.services.AvaluacioUseCase;
import accessibilitatweb.accessibilitatweb.services.LectorUseCase;
import accessibilitatweb.accessibilitatweb.services.UsuariUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/")
public class AvaluacioPersonalController {
    @Autowired
    private UsuariUseCase usuariUseCase;
    @Autowired
    private AvaluacioUseCase avaluacioUseCase;

    private AvaluacioPersonal avaluacioPersonal;

    @Autowired
    private LectorUseCase lectorUseCase;

    public AvaluacioPersonalController(UsuariUseCase usuariUseCase, AvaluacioUseCase avaluacioUseCase){
        this.usuariUseCase=usuariUseCase;
        this.avaluacioUseCase=avaluacioUseCase;

    }
    @GetMapping("gestion-evaluaciones")
    public String manageAvaluaciones(Model model){
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());
        model.addAttribute("usuari", this.usuariUseCase.getUsuari());
        model.addAttribute("avaluacionsUsuari", this.usuariUseCase.getAvaluacionsUsuari());
        return "gestion-evaluaciones";
    }

    @GetMapping("nueva-evaluacion")
    public AvaluacioPersonal addAvaluacio(Model model){
        model.addAttribute("usuari", this.usuariUseCase.getUsuari());
        model.addAttribute("listLectors", lectorUseCase.getAllLectors());
        AvaluacioPersonal avaluacioPersonal=new AvaluacioPersonal();
        List<String> llistaTaules = Arrays.asList(avaluacioPersonal.taulesdisponibles);
        model.addAttribute("llistaTaules", llistaTaules);
        return avaluacioPersonal;


    }

    @PostMapping("nueva-evaluacion")
    public String processAddAvaluacio(AvaluacioPersonal avaluacioPersonal, RedirectAttributes redirectAttributes){
        try {
            avaluacioUseCase.afegirAvaluacio(avaluacioPersonal);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
            redirectAttributes.addFlashAttribute("avaluacioPersonal",avaluacioPersonal);
            return "redirect:/nueva-evaluacion";
        }
        return "redirect:/gestion-evaluaciones";
    }
    @GetMapping("editar-evaluacion")
    public AvaluacioPersonal editAvaluacio(@RequestParam String avaluacioId, Model model){
        this.avaluacioPersonal= this.avaluacioUseCase.getAvaluacioActual(Long.decode(avaluacioId));
        model.addAttribute("usuari", this.usuariUseCase.getUsuari());
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());
        List<String> llistaTaules = Arrays.asList(this.avaluacioPersonal.taulesdisponibles);

        model.addAttribute("llistaTaules", llistaTaules);
        return this.avaluacioPersonal;

    }
    @PostMapping("editar-evaluacion")
    public String processEditAvaluacio(AvaluacioPersonal avaluacioPersonal){

        this.avaluacioUseCase.editarAvaluacio(avaluacioPersonal,this.avaluacioPersonal);
        return "redirect:/gestion-evaluaciones";

    }

    @GetMapping("eliminar-evaluacion/{avaluacioId}")
    public String handleDeleteAvaluacio(@PathVariable String avaluacioId) {

        this.avaluacioUseCase.eliminarAvaluacio(Long.decode(avaluacioId));
        return "redirect:/gestion-evaluaciones";
    }

}
