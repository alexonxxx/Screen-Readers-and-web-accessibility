package accessibilitatweb.accessibilitatweb.webcontroller;

import accessibilitatweb.accessibilitatweb.services.LectorUseCase;
import accessibilitatweb.accessibilitatweb.services.UsuariUseCase;
import accessibilitatweb.accessibilitatweb.domain.LectorPantalla;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class LectorPantallaController {
    //tots els paramatres dels metodes són el resultat de la interacció amb les pàgines
    @Autowired
    private UsuariUseCase usuariUseCase;

    @Autowired
    private LectorUseCase lectorUseCase;

    private LectorPantalla lectorPantalla;


    public LectorPantallaController(UsuariUseCase usuariUseCase, LectorUseCase lectorUseCase){
        this.usuariUseCase=usuariUseCase;
        this.lectorUseCase=lectorUseCase;

    }

    @GetMapping("nuevo-lector")
    public LectorPantalla addLector(Model model){
        model.addAttribute("usuari", this.usuariUseCase.getUsuari());
        model.addAttribute("listLectors", lectorUseCase.getAllLectors());
        return new LectorPantalla();

    }
    @PostMapping("nuevo-lector")
    public String processaddLector(LectorPantalla lectorPantalla, RedirectAttributes redirectAttributes){
        try {

            lectorUseCase.afegirLector(lectorPantalla);

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
            redirectAttributes.addFlashAttribute("lectorPantalla",lectorPantalla);
            return "redirect:/nuevo-lector";
        }
        return "redirect:/gestion-lectores";
    }

    @GetMapping("editar-lector")
    public LectorPantalla editLector( @RequestParam String lectorId, Model model){
         this.lectorPantalla= this.lectorUseCase.getLectorActual(Long.decode(lectorId));
        model.addAttribute("usuari", this.usuariUseCase.getUsuari());
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());
        model.addAttribute("listSistemes", this.lectorUseCase.getSistemesLector(lectorPantalla));
        model.addAttribute("listNavegadors", lectorUseCase.getNavegadorsLector(lectorPantalla));
        model.addAttribute("comandes", lectorUseCase.getComandesLector(lectorPantalla));
        return lectorPantalla;

    }
    @PostMapping("editar-lector")
    public String processEditLector(@RequestParam(value="action") String action, LectorPantalla lectorPantalla, RedirectAttributes redirectAttributes){
        try {
            this.lectorUseCase.editarLector(lectorPantalla,this.lectorPantalla);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
            redirectAttributes.addFlashAttribute("lectorPantalla",lectorPantalla);
            return "redirect:/editar-lector?lectorId="+ this.lectorPantalla.getId();
        }
        switch(action) {
            case "novaComanda":
                LectorPantalla lectorAuxiliar=this.lectorPantalla;
                return "redirect:/nueva-comanda?lectorId="+ lectorAuxiliar.getId();

            //case "eliminarComanda":
             //   return "redirect:/gestion-lectores";

            default:
                return "redirect:/gestion-lectores";
        }
    }

    @GetMapping("lector-pantalla/{nomLector}")
    public String lectorPantalla(@PathVariable String nomLector,Model model){

        LectorPantalla lectoractual= this.lectorUseCase.getLectorActualByName(nomLector);
        Usuari usuari=this.usuariUseCase.getUsuari();
        if(this.usuariUseCase.getUsuari()!=null){
            model.addAttribute("usuari",usuari);
        }
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());
        model.addAttribute("lectorActual", lectoractual);
        model.addAttribute("rutaSistemes",this.lectorUseCase.getRutesSistemes(lectoractual.getSistemesOperatius()));
        model.addAttribute("rutaNavegadors",this.lectorUseCase.getRutesNavegadors(lectoractual.getNavegadors()));
        return "lector-pantalla";
    }

    @GetMapping("gestion-lectores")
    public String manageLectores(Model model){
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());
        model.addAttribute("usuari", this.usuariUseCase.getUsuari());
        model.addAttribute("lectorsUsuari", this.usuariUseCase.getLectorsUsuari());
        return "gestion-lectores";
    }

    @GetMapping("eliminar-lector/{lectorId}")
    public String handleDeleteLector(@PathVariable String lectorId) {

            this.lectorUseCase.eliminarLector(Long.decode(lectorId));
        return "redirect:/gestion-lectores";
    }






}
