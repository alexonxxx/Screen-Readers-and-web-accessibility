package accessibilitatweb.accessibilitatweb.webcontroller;

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
public class AccessibilitatController {
    @Autowired
    private UsuariUseCase usuariUseCase;

    @Autowired
    private LectorUseCase lectorUseCase;



    public AccessibilitatController(UsuariUseCase usuariUseCase, LectorUseCase lectorUseCase) {
        this.usuariUseCase = usuariUseCase;
        this.lectorUseCase = lectorUseCase;
    }

    @GetMapping("accesibilidad")
    public String accesibilidad(Model model){

        Usuari usuari=this.usuariUseCase.getUsuari();
        if(usuari!=null){
            model.addAttribute("usuari", usuari);
        }
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());


        return "accesibilidad";
    }



}
