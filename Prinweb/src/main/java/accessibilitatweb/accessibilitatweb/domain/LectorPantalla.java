package accessibilitatweb.accessibilitatweb.domain;

import accessibilitatweb.accessibilitatweb.exceptions.BrowserNullException;
import accessibilitatweb.accessibilitatweb.exceptions.SystemsNullException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="LECTORSPANTALLA ")
public class LectorPantalla implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nomLector;

    @NotNull
    @Column(length=2048)
    private String definicio;

    @ElementCollection
    @CollectionTable(name = "sistemesOperatius", joinColumns = @JoinColumn(name = "usuari_id"))
    @Column(name = "sistemaOperatiu")
    private List<String> sistemesOperatius;

    @ElementCollection
    @CollectionTable(name = "navegadors", joinColumns = @JoinColumn(name = "usuari_id"))
    @Column(name = "navegador")
    private List<String> navegadors;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "lector_id")
    private List<Comanda> comandes;



    public LectorPantalla(String nomLector, String definicio, LinkedList<String> sistemesOperatius, LinkedList<String> navegadors, LinkedList<Comanda> comandes) {
        this.nomLector = nomLector.toUpperCase();
        //excepcio si no hi ha definicio
        this.definicio = definicio;
        this.sistemesOperatius = sistemesOperatius;
        this.navegadors = navegadors;
        if( comandes==null){
            this.comandes= new LinkedList<>();
        }else {
            this.comandes = comandes;
        }
    }
    public LectorPantalla() {
    }

    public Long getId() {
        return id;
    }

    public String getNomLector() {
        return nomLector;
    }

    public String getDefinicio() {
        return definicio;
    }

    public List<String> getSistemesOperatius() {
        return sistemesOperatius;
    }

    public List<String> getNavegadors() {
        return navegadors;
    }

    public List<Comanda> getComandes() {
        return comandes;
    }

    public void setNomLector(String nomLector) {
        this.nomLector = nomLector;
    }

    public void setDefinicio(String definicio) {
        this.definicio = definicio;
    }

    public void setSistemesOperatius(List<String> sistemesOperatius) {
        this.sistemesOperatius = sistemesOperatius;
    }

    public void setNavegadors(List<String> navegadors) {
        this.navegadors = navegadors;
    }

    public void setComandes(List<Comanda> comandes) {
        this.comandes = comandes;
    }


    public void eliminarComanda(Long comandaId){
        LinkedList<Comanda> comandesAuxiliars= new LinkedList<>(this.comandes);
        for (Comanda comanda: comandesAuxiliars){
            if(comanda.getId()==comandaId){
                System.out.println("eliminada: "+comanda.getNomComanda());
                this.comandes.remove(comanda);
            }
        }
    }

    public void editar(LectorPantalla lector) {
        if(lector.getNavegadors().size()==0){
            throw new BrowserNullException();
        }
        if(lector.getSistemesOperatius().size()==0){
            throw new SystemsNullException();
        }
        this.nomLector= lector.nomLector.toUpperCase();
        this.sistemesOperatius= lector.sistemesOperatius;
        this.navegadors= lector.navegadors;
        this.definicio= lector.definicio;
        this.comandes= lector.comandes;
    }

    public void afegirComanda(Comanda comanda) {
        this.comandes.add(comanda);
    }

    @Override
    public String toString() {
        return "LectorPantalla{" +
                "nomLector='" + nomLector + '\'' +
                ", defincio='" + definicio + '\'' +
                ", sistemesOperatius=" + sistemesOperatius +
                ", navegadors=" + navegadors +
                ", comandes=" + comandes +
                '}';
    }


}
