package accessibilitatweb.accessibilitatweb.webcontroller;

import accessibilitatweb.accessibilitatweb.services.LectorUseCase;
import accessibilitatweb.accessibilitatweb.domain.Comanda;
import accessibilitatweb.accessibilitatweb.domain.LectorPantalla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class ComandaController {

    @Autowired
    private LectorUseCase lectorUseCase;

    public ComandaController(LectorUseCase lectorUseCase){
        this.lectorUseCase=lectorUseCase;

    }

    @GetMapping("nueva-comanda")
    public Comanda addComanda(Model model){

        model.addAttribute("usuari", this.lectorUseCase.getUsuariActual());
        model.addAttribute("listLectors", lectorUseCase.getAllLectors());
        model.addAttribute("lectorActual",  this.lectorUseCase.getLectorActualById().get());
        return new Comanda();

    }
    @PostMapping("nueva-comanda")
    public String processaddComanda(Comanda comanda, RedirectAttributes redirectAttributes){
        LectorPantalla lectorActual= this.lectorUseCase.getLectorActualById().get();

        try {

            this.lectorUseCase.afegirComanda(comanda);

        }catch (Exception e){
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());

            return "redirect:/nueva-comanda";
        }
        return "redirect:/editar-lector?lectorId=" + lectorActual.getId();
    }

    @GetMapping("eliminar-comanda/{comandaId}")
    public String handleDeleteComanda(@PathVariable String comandaId) {

        LectorPantalla lectorActual=this.lectorUseCase.getLectorActualById().get();
        this.lectorUseCase.eliminarComanda(Long.decode(comandaId));
        return "redirect:/editar-lector?lectorId="+lectorActual.getId();
    }


}
