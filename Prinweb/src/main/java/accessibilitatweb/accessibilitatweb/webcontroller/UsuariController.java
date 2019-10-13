package accessibilitatweb.accessibilitatweb.webcontroller;


import accessibilitatweb.accessibilitatweb.services.LectorUseCase;
import accessibilitatweb.accessibilitatweb.services.UsuariUseCase;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import accessibilitatweb.accessibilitatweb.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/")
public class UsuariController {
    //tots els paramatres dels metodes són el resultat de la interacció amb les pàgines
    @Autowired
    private UsuariUseCase usuariUseCase;

    @Autowired
    private LectorUseCase lectorUseCase;

    private Usuari usuari;

    @Autowired
    private SecurityService securityService;

    public UsuariController(UsuariUseCase usuariUseCase, LectorUseCase lectorUseCase, SecurityService securityService) {
        this.usuariUseCase=usuariUseCase;
        this.lectorUseCase=lectorUseCase;
        this.securityService=securityService;
    }

    @GetMapping("/")
    public String home(Model model){

        this.usuari=this.usuariUseCase.getUsuari();
        if(this.usuariUseCase.getUsuari()!=null){
            model.addAttribute("usuari", this.usuari);
        }
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());


        return "home";
    }
    @GetMapping("login")
    public String login(Model model) {
        model.addAttribute("listLectors", lectorUseCase.getAllLectors());

        return "login";
    }


    @GetMapping("registration")
    public Usuari register(Model model) {
        model.addAttribute("listLectors", lectorUseCase.getAllLectors());
        this.usuari=this.usuariUseCase.getUsuari();
        if(this.usuariUseCase.getUsuari()!=null){
            model.addAttribute("usuari", this.usuari);
        }
        return new Usuari();
    }

    @PostMapping("registration")
    public String processRegister(Usuari usuari, RedirectAttributes redirectAttributes){

        try {
            this.usuari=new Usuari(usuari.getNomUsuari(), usuari.getPassword(),usuari.getEmail(),"user");
            PasswordEncoder encoder =
                    PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String nonEncoderPasword=this.usuari.getPassword();
            this.usuari.setPassword(encoder.encode(nonEncoderPasword));
            usuariUseCase.registrarUsuari(this.usuari,nonEncoderPasword);

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage",e.getMessage());
            redirectAttributes.addFlashAttribute("usuari",usuari);
            return "redirect:/registration";
        }
        return "redirect:/";
    }





}

