package accessibilitatweb.accessibilitatweb.webcontroller;

import accessibilitatweb.accessibilitatweb.domain.Contacte;
import accessibilitatweb.accessibilitatweb.services.LectorUseCase;
import accessibilitatweb.accessibilitatweb.services.UsuariUseCase;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Controller
@RequestMapping("/")
public class ContacteController {
    @Autowired
    private UsuariUseCase usuariUseCase;

    @Autowired
    private LectorUseCase lectorUseCase;


    private Contacte contacte;


    public ContacteController(UsuariUseCase usuariUseCase, LectorUseCase lectorUseCase) {
        this.usuariUseCase = usuariUseCase;
        this.lectorUseCase = lectorUseCase;
    }

    @GetMapping("contacto")
    public Contacte contacte(Model model){
        Usuari usuari=this.usuariUseCase.getUsuari();
        if(usuari!=null){
            model.addAttribute("usuari", usuari);
        }
        model.addAttribute("listLectors", this.lectorUseCase.getAllLectors());


        return new Contacte();
    }

    @PostMapping("contacto")
    public String processContacte(Contacte contacte, RedirectAttributes redirectAttributes){

        try {
            this.contacte=contacte;
            enviarEmail();

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("errorMessage","El mensaje no se ha podido enviar correctamente");
            return "redirect:/contacto";
        }
        return "redirect:/";
    }

    private void enviarEmail() throws Exception{
        String to = "alex.ballo0806@gmail.com";
        String host = "smtp.gmail.com";
        String password="080695ballo";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(to, password);
                    }
                });


        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(contacte.getEmail()));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(contacte.getAssumpte());
        message.setText("Nom: " + contacte.getNom() + "\nCognoms: " + contacte.getCognoms() + "\nE-mail: " + contacte.getEmail() + "\nTelèfon: " + contacte.getTelefon() + "\nCompanyia: " + contacte.getCompanyia() + "\nComentari: " + contacte.getComentari());
        Transport.send(message);
        System.out.println("message sent successfully...");

    }




}
