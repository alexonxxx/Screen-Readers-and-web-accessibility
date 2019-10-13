package accessibilitatweb.accessibilitatweb;


import accessibilitatweb.accessibilitatweb.services.AvaluacioUseCase;
import accessibilitatweb.accessibilitatweb.services.LectorUseCase;
import accessibilitatweb.accessibilitatweb.services.UsuariUseCase;
import accessibilitatweb.accessibilitatweb.domain.AvaluacioPersonal;
import accessibilitatweb.accessibilitatweb.domain.Comanda;
import accessibilitatweb.accessibilitatweb.domain.LectorPantalla;
import accessibilitatweb.accessibilitatweb.domain.Usuari;
import accessibilitatweb.accessibilitatweb.repositories.UsuariRepository;
import javafx.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.LinkedList;

@SpringBootApplication
public class AccessibilitatwebApplication implements CommandLineRunner {


	@Autowired
	private UsuariRepository usuariRepository;

	@Autowired
	private UsuariUseCase usuariUseCase;
	@Autowired
	private AvaluacioUseCase avaluacioUseCase;

	@Autowired
	private LectorUseCase lectorUseCase;


	public static void main(String[] args) {
		SpringApplication.run(AccessibilitatwebApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		//eliminar Usuaris de la base de dades cada cop que s'executa l'aplicació(desenvolupament)
		usuariRepository.deleteAll();
		Usuari usuari= new Usuari("Alexon", "1234", "aballo@edu.tecnocampus.cat", "adm");
		try{
			PasswordEncoder encoder =
				PasswordEncoderFactories.createDelegatingPasswordEncoder();
			String nonEncoderPasword=usuari.getPassword();
			usuari.setPassword(encoder.encode(nonEncoderPasword));
			usuariUseCase.registrarUsuari(usuari,nonEncoderPasword);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		LectorPantalla nvda = new LectorPantalla("nvda","NVDA es un lector de pantalla de donación que está disponible en todas las versiones de Windows a partir de Windows XP (última actualización). La aplicación es compatible con la mayoría de navegadores más conocidos como Google Chrome, Mozilla Firefox, Opera, Internet Explorer, Safari y Microsoft Edge. Es bastante sencillo de utilizar ya que aunque no dispone de un panel de control como la mayoría de lectores de pantalla, no lo necesita. La principal funcionalidad que proporciona ayuda a personas con ceguera o problemas de visión es convertir el texto en audio. Además, una de las cosas más sorprendentes es que aunque sea un lector de pantalla de donación, también es compatible con un amplio rango de dispositivos Braille.",getSistemesNvda(),getNavegadorsNvda() ,getComandesNvda());
		LectorPantalla jaws = new LectorPantalla("jaws","JAWS es un lector de pantalla de pago que cuesta 900 euros y está disponible en Windows 7, 8.1 y 10. Sólo es compatible con Internet Explorer para leer adecuadamente el contenido. Es un programa relativamente sencillo de utilizar pero incorpora la configuración de muchas características de la aplicación innecesarias y poco comprensibles como el asistente del teclado o el visor de marcos. Una de las cosas más sorprendentes es que por el precio que cuesta esperas que tenga alguna funcionalidad incorporada más que los lectores de pantalla gratuitos o de precios bajos, pero sólo convierte el texto en audio y ofrece soporte para dispositivos Braille.",getSistemesJaws(),getNavegadorsJaws() ,getComandesJaws());
		LectorPantalla supernova = new LectorPantalla("SuperNova Magnifier & Screen Reader","SuperNova Magnifier & Screen Reader es un lector de pantalla de pago que está disponible en Windows 7, 8.1 y 10. Una de las principales carencias de la aplicación es que sólo está disponible para un navegador, Internet Explorer. Este lector de pantalla proporciona muchas funcionalidades para facilitar el uso de los dispositivos con personas con ceguera o problemas de visión. Permite convertir el texto en audio, mejorar la visibilidad de la pantalla, soporta muchos dispositivos braille y ofrece una secuencia de comandos para hacer más fácil la usabilidad de la aplicación. Es bastante sencillo de utilizar siempre que sepas lo que estás buscando, ya que tiene tantas funcionalidades que para poder extraer todo el potencial de este lector de pantalla se puede pasar días e incluso semanas para descubrir todas. Una de las cosas más impactante de esta aplicación es que el precio del producto es de 1534 €.",getSistemesSupernova(),getNavegadorsSupernova() ,getComandesSupernova());
		LectorPantalla readwrite = new LectorPantalla("Read&Write","Read&Write es un lector de pantalla de pago que cuesta 180 £ (200 € aproximadamente) y está disponible en la mayoría de sistemas operativos como Windows, Mac OS, IOS y Android. Además, es compatible con la mayoría de navegadores más utilizados que son Google Chrome, Internet Explorer, Mozilla Firefox, Safari, Opera y Microsoft Edge. El panel es una barra con todas las facilidades que proporciona, a simple vista parece sencillo de utilizar, pero la manera de leer el texto que ofrece no es muy intuitiva (lo observaremos en la comparativa). También ofrezca una pequeña mejora para mejorar la visibilidad de la pantalla, pero es uno de los lectores de pantalla que no ofrece soporte para dispositivos Braille.",getSistemesReadWrite(),getNavegadorsReadWrite() ,null);
		lectorUseCase.afegirLector(nvda);
		lectorUseCase.afegirLector(jaws);
		lectorUseCase.afegirLector(supernova);
		lectorUseCase.afegirLector(readwrite);
		avaluacioUseCase.afegirAvaluacio(new AvaluacioPersonal("Como se puede observar en la comparativa, el lector de pantalla que ofrece la mejor visualización de la pantalla y el ratón es Supernova Magnifier & Screen Reader. La principal funcionalidad que los lectores de pantalla deben ofrecer es poder visualizar el lugar donde está situado el ratón o donde se está leyendo, porque hay mucha gente que instala lectores de pantalla porque tienen dificultad a la hora de leer la pantalla y no son ciegos.\n" +
				"Una de las cosas importantes que deberían proporcionar la mayoría de lectores de pantalla es ampliar la pantalla donde está situada el ratón para que puede ayudar mucho a la hora de leer un párrafo bastante largo sin tener que ir desplazando el ratón.","visualización",usuari.getNomUsuari()));
		avaluacioUseCase.afegirAvaluacio(new AvaluacioPersonal("Según los datos recopilados en la comparativa se puede observar que el lector de pantalla que ha conseguido mejor puntuación siguiendo las métricas aplicadas es NVDA. A pesar de ser el único de los lectores de pantalla evaluados gratuito, es el que ofrece más funcionalidades a la hora de hacer la lectura de páginas.\n" +
				"Una de las cosas que me ha llamado más la atención, es que tanto supernova como JAWS que son unos de los lectores de pantalla más reconocidos y utilizados, no leen el texto donde se encuentra el puntero del ratón. Es una de las métricas más importantes para que la hora de situarse sobre un enlace puedes saber en qué página te dirigirá sin tener que pulsar directamente con él o mantener el ratón pulsado para saberlo.","texto a audio", usuari.getNomUsuari()));
		avaluacioUseCase.afegirAvaluacio(new AvaluacioPersonal("En esta evaluación de características adicionales como se puede observar los que obtienen la mejor puntuación son los más conocidos, utilizados y más costosos, JAWS y SuperNova Magnifier & Screen Reader. Principalmente, estas características ayudan a mejorar la experiencia de usuario, por ello gran cantidad de gente decide pagar una cierta cantidad de dinero para poder disfrutar de una voz agradable cuando se lee la página, elegir el tipo de técnicas a usar como para ejemplo visualizar ratón o no, anunció cuando se cambia de página, regularizar la velocidad de la voz, etc.\n" +
				"Una de las cosas más sorprendentes es que SuperNova Magnifier & Screen Reader no proporciona pedidos para poder mejorar la accesibilidad en la web, un lector de pantalla que cuesta más de 1000 € debería tener incorporada esta funcionalidad. Además, como que ofrece soporte para dispositivos Braille, los usuarios tendrían acceso al teclado de una manera más eficiente para poder utilizar los pedidos y no tener que desplazarse con el ratón.","otras funcionalidades",usuari.getNomUsuari()));
	}
	public LinkedList<String> getSistemesNvda() {
		LinkedList<String> sistemes= new LinkedList<>();
		sistemes.add("WINDOWS");
		return sistemes;
	}
	public LinkedList<String> getSistemesSupernova() {
		LinkedList<String> sistemes= new LinkedList<>();
		sistemes.add("WINDOWS");
		return sistemes;
	}
	public LinkedList<String> getSistemesJaws() {
		LinkedList<String> sistemes= new LinkedList<>();
		sistemes.add("WINDOWS");
		return sistemes;
	}
	public LinkedList<String> getSistemesReadWrite() {
		LinkedList<String> sistemes= new LinkedList<>();
		sistemes.add("WINDOWS");
		sistemes.add ("ANDROID");
		sistemes.add ("MAC OS");
		sistemes.add ("IOS");
		return sistemes;
	}
	public LinkedList<String> getNavegadorsNvda() {
		LinkedList<String> navegadors= new LinkedList<>();
		navegadors.add("GOOGLE CHROME");
		navegadors.add ("INTERNET EXPLORER");
		navegadors.add ("FIREFOX");
		navegadors.add ("OPERA");
		navegadors.add ("SAFARI");
		return navegadors;
	}
	public LinkedList<String> getNavegadorsSupernova() {
		LinkedList<String> navegadors= new LinkedList<>();
		navegadors.add ("INTERNET EXPLORER");

		return navegadors;
	}
	public LinkedList<String> getNavegadorsJaws() {
		LinkedList<String> navegadors= new LinkedList<>();
		navegadors.add ("INTERNET EXPLORER");
		return navegadors;
	}
	public LinkedList<String> getNavegadorsReadWrite() {
		LinkedList<String> navegadors= new LinkedList<>();
		navegadors.add("GOOGLE CHROME");
		navegadors.add ("INTERNET EXPLORER");
		navegadors.add ("FIREFOX");
		navegadors.add ("OPERA");
		navegadors.add ("SAFARI");
		return navegadors;
	}

	public LinkedList<Comanda> getComandesNvda() {
		LinkedList<Comanda> comandesNvda= new LinkedList<>();
		//lectura
		comandesNvda.add(new Comanda("Ctrl + Alt + N", "Ejecutar aplicación (NVDA pedido)","lectura"));
		comandesNvda.add(new Comanda("NVDA  + Q", "Cerrar aplicación","lectura"));
		comandesNvda.add(new Comanda("Numpad +", "Empezar lectura de toda la página","lectura"));
		comandesNvda.add(new Comanda("NVDA + flecha abajo", "Empezar a leer desde la posición actual","lectura"));
		comandesNvda.add(new Comanda("Ctrl", "Parar de leer","lectura"));
		comandesNvda.add(new Comanda("NVDA + flecha arriba o Numpad 8", "Leer línea actual","lectura"));
		comandesNvda.add(new Comanda("Ctrl + flecha derecha / izquierda o Numpad 4/6", "palabra anterior / siguiente","lectura"));
		comandesNvda.add(new Comanda("Flecha arriba o Numpad 7", "Leer línea anterior","lectura"));
		comandesNvda.add(new Comanda("Flecha abajo o Numpad 9", "Leer línea siguiente","lectura"));
		comandesNvda.add(new Comanda("Flecha derecha / izquierda o Numpad 1/3", "Carácter anterior / siguiente","lectura"));
		comandesNvda.add(new Comanda("F5 / Ctrl + F5", "Refrescar la página","lectura"));
		comandesNvda.add(new Comanda("NVDA + Ctrl + flecha arriba / abajo", "Incrementar / disminuir velocidad de lectura","lectura"));

		//navegacio
		comandesNvda.add(new Comanda("Tab/Tab + shift", "Enlace / anterior","navegación"));
		comandesNvda.add(new Comanda("H", "Headings","navegación"));
		comandesNvda.add(new Comanda("D", "Landmarks","navegación"));
		comandesNvda.add(new Comanda("1-6", "Headings de 1-6","navegación"));
		comandesNvda.add(new Comanda("F", "Formularios","navegación"));
		comandesNvda.add(new Comanda("T", "Tablas","navegación"));
		comandesNvda.add(new Comanda("B", "Botones","navegación"));
		comandesNvda.add(new Comanda("L", "Lista","navegación"));
		comandesNvda.add(new Comanda("I", "Elementos de la lista","navegación"));
		comandesNvda.add(new Comanda("NVDA +F7", "Elements de una llista ","navegación"));
		comandesNvda.add(new Comanda("Ctrl + Home", "Parte más alta de la página","navegación"));
		comandesNvda.add(new Comanda("Ctrl + End", "Parte más baja de la página","navegación"));
		comandesNvda.add(new Comanda("Alt + D o F6", "Barra del navegador","navegación"));

		//formulari
		comandesNvda.add(new Comanda("Intro o NVDA + Space", "Entrar en modo formulario","formulario"));
		comandesNvda.add(new Comanda("NVDA + Space", "Salir del modo de formulario","formulario"));
		comandesNvda.add(new Comanda("Tab y shift + Tab", "Navegar por los elementos del formulario","formulario"));
		comandesNvda.add(new Comanda("Space", "Marcar y desmarcar checkboxes","formulario"));
		comandesNvda.add(new Comanda("Flecha arriba / abajo", "Seleccionar elemento de un grupo de radio buttons o de un combo box","formulario"));


		return comandesNvda;
	}
	public LinkedList<Comanda> getComandesSupernova(){
		LinkedList<Comanda> comandesSupernova= new LinkedList<>();
		comandesSupernova.add(new Comanda("Alt", "Mover el foco en la barra de elementos","lectura"));
		comandesSupernova.add(new Comanda("Alt + a", "Mover el foco en la casilla archivo de la barra de elementos","lectura"));
		comandesSupernova.add(new Comanda("Tab", "Control siguiente del panel de control","lectura"));
		comandesSupernova.add(new Comanda("Ctrl + barra espaciadora", "Abrir el panel de control","lectura"));
		comandesSupernova.add(new Comanda("Escape", "Minimizar el panel de control o cancelar los controles o elementos entrados","lectura"));
		comandesSupernova.add(new Comanda("Ctrl +/-", "Ampliar / reducir el ampliador de pantalla","lectura"));
		comandesSupernova.add(new Comanda("Ctrl derecho + flecha arriba / abajo / izquierda / derecha", "Mover la ampliación de donde estar ciudad el ratón","lectura"));
		comandesSupernova.add(new Comanda("Ctr derecho + número", "Posicionar pantalla en una parrilla de 3x3","lectura"));
		comandesSupernova.add(new Comanda("Alt + v", "Mover el foco de barra visual","lectura"));
		comandesSupernova.add(new Comanda("Mayus + bloq + flecha", "Seleccionar líneas o caracteres","lectura"));


		return comandesSupernova;
	}

	public LinkedList<Comanda> getComandesJaws(){
		LinkedList<Comanda> comandesJaws= new LinkedList<>();
		//lectura
		comandesJaws.add(new Comanda("Insert + Flecha abajo", "Empezar a leer toda la página","lectura"));
		comandesJaws.add(new Comanda("Página arriba / abajo", "Incrementar / disminuir la velocidad de la voz cuando se lee toda la página","lectura"));
		comandesJaws.add(new Comanda("Ctrl", "Parar de leer","lectura"));
		comandesJaws.add(new Comanda("Insert + Flecha arriba", "Leer línea actual","lectura"));
		comandesJaws.add(new Comanda("Insert + Flecha izquierda / derecha", "Leer palabra anterior / siguiente","lectura"));
		comandesJaws.add(new Comanda("Flecha arriba", "Leer la línea anterior","lectura"));
		comandesJaws.add(new Comanda("Flecha abajo", "Leer la línea siguiente","lectura"));
		comandesJaws.add(new Comanda("Flecha izquierda / derecha", "Leer anterior / siguiente carácter","lectura"));
		comandesJaws.add(new Comanda("F5/shift + F5", "Refrescar la página","lectura"));
		//navegacio
		comandesJaws.add(new Comanda("Tab/Tab + shift", "Enlace / anterior","navegación"));
		comandesJaws.add(new Comanda("H", "Headings","navegación"));
		comandesJaws.add(new Comanda("R", "Landmarks","navegación"));
		comandesJaws.add(new Comanda("Q", "Contenido principal","navegación"));
		comandesJaws.add(new Comanda("1-6", "Headings de 1-6","navegación"));
		comandesJaws.add(new Comanda("F", "Formularios","navegación"));
		comandesJaws.add(new Comanda("T", "Tablas","navegación"));
		comandesJaws.add(new Comanda("N", "Saltar al primer escrito que no sea un enlace","navegación"));
		comandesJaws.add(new Comanda("B", "Botones","navegación"));
		comandesJaws.add(new Comanda("L", "Lista","navegación"));
		comandesJaws.add(new Comanda("I", "Elementos de la lista","navegación"));
		comandesJaws.add(new Comanda("Shift + cualquier tecla anterior", "Buscar el elemento anterior","navegación"));
		comandesJaws.add(new Comanda("Ctrl + insert + cualquier tecla anterior", "Mostrar lista del tipo de elemento","navegación"));
		comandesJaws.add(new Comanda("Insert + f1", "Ayuda elemento actual\n","navegación"));
		comandesJaws.add(new Comanda("Insert + f5", "Lista de los elementos del formulario","navegación"));
		comandesJaws.add(new Comanda("Insert + f6", "Lista de los heading","navegación"));
		comandesJaws.add(new Comanda("Insert + f7", "Lista de los links","navegación"));
		comandesJaws.add(new Comanda("Crtl + home", "Parte más alta de la página","navegación"));
		comandesJaws.add(new Comanda("Crtl + end", "Parte más baja de la página","navegación"));
		comandesJaws.add(new Comanda("Alt + D o F6", "Barra del navegador","navegación"));
		//taula
		comandesJaws.add(new Comanda("Ctrl + Alt Flecha arriba / abajo / izquierda / derecha", "Recorrer las casillas de la tabla","tabla"));
		comandesJaws.add(new Comanda("Ctrl + Alt + Numpad 5", "Leer la fila o la columna","tabla"));

		//formulari
		comandesJaws.add(new Comanda("Intro", "Entrar en modo formulario","formulario"));
		comandesJaws.add(new Comanda("Numpad +", "Salir del modo de formulario","formulario"));
		comandesJaws.add(new Comanda("Tab i shift + tab", "Navegar por los elementos del formulario","formulario"));
		comandesJaws.add(new Comanda("Space", "Marcar y desmarcar checkboxes","formulario"));
		comandesJaws.add(new Comanda("Flecha arriba / abajo", "Seleccionar elemento de un grupo de radio buttons o de un combo box","formulario"));
		return comandesJaws;
	}
}
