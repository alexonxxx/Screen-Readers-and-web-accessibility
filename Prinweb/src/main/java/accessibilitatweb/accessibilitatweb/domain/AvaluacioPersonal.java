package accessibilitatweb.accessibilitatweb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="AVALUACIONSPERSONALS")
public class AvaluacioPersonal implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(length=2048)
    private String avaluacio;

    @NotNull
    private String taula;

    @NotNull
    private String usuari;

    @Transient
    public final String[] taulesdisponibles={"texto a audio","visualización","otras funcionalidades"};


    public AvaluacioPersonal(String avaluacio, String taula, String usuari) {
        this.avaluacio = avaluacio;
        this.taula=taula;
        this.usuari=usuari;

    }
    public AvaluacioPersonal(){
    }

    public Long getId() {
        return id;
    }

    public String getAvaluacio() {
        return avaluacio;
    }

    public void setAvaluacio(String avaluacio) {
        this.avaluacio = avaluacio;
    }

    public String getTaula() {
        return taula;
    }

    public void setTaula(String taula) {
        this.taula = taula;
    }

    public void editarAvaluacio(AvaluacioPersonal avaluacioPersonal){
        this.taula= avaluacioPersonal.getTaula();
        this.avaluacio= avaluacioPersonal.getAvaluacio();
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    @Override
    public String toString() {
        return "AvaluacioPersonal{" +
                "avaluacio='" + avaluacio + '\'' +
                '}';
    }


}
