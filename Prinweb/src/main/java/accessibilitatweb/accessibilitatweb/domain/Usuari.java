package accessibilitatweb.accessibilitatweb.domain;

import accessibilitatweb.accessibilitatweb.exceptions.CommandAlreadyExists;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="USUARIS")
public class Usuari {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Column(unique = true)
    private String nomUsuari;

    @NotNull
    private String password;

    @NotNull
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    private String rol;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "usuari_id")
    private List<AvaluacioPersonal> avaluacionsPersonals;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "usuari_id")
    private List<LectorPantalla> lectorsPantalla;

    public Usuari(String nomUsuari, String password, String email, String rol){
        this.nomUsuari = nomUsuari;
        this.password = password;
        this.email = email;
        this.rol= rol;
        this.avaluacionsPersonals= new LinkedList<AvaluacioPersonal>();
        this.lectorsPantalla= new LinkedList<LectorPantalla>();
    }
    public Usuari(){}

    public String getNomUsuari() {
        return nomUsuari;
    }

    public void setNomUsuari(String nomUsuari) {
        this.nomUsuari = nomUsuari;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getRol() {
        return rol;
    }

    public List<AvaluacioPersonal> getAvaluacionsPersonals() {
        return avaluacionsPersonals;
    }

    public List<LectorPantalla> getLectorsPantalla() {
        return lectorsPantalla;
    }

    public void afegirLectorPantalla(LectorPantalla lectorPantalla){
        this.lectorsPantalla.add(lectorPantalla);
    }


    public void editarLector(LectorPantalla lector,Long lectorId) {


        for (LectorPantalla lectorPantalla:this.lectorsPantalla
             ) {
            if(lectorPantalla.getId()==lectorId){
                lectorPantalla.editar(lector);
            }

        }

    }

    public void eliminarLector(Long lectorId) {
        // java.util.ConcurrentModificationException: null
        LinkedList<LectorPantalla> lectorsAuxiliars= new LinkedList<>(this.lectorsPantalla);
        for (LectorPantalla lectorPantalla: lectorsAuxiliars ) {
            if(lectorPantalla.getId()==lectorId){
                this.lectorsPantalla.remove(lectorPantalla);
            }
        }
    }
    //comprobar si funciona directament desde el repository davaluacions
    public void eliminarAvaluacio(Long avaluacioId) {
        // java.util.ConcurrentModificationException: null
        LinkedList<AvaluacioPersonal> avaluacionsPersonal= new LinkedList<>(this.avaluacionsPersonals);
        for (AvaluacioPersonal avaluacioPersonal: avaluacionsPersonal ) {
            if(avaluacioPersonal.getId()==avaluacioId){
                this.avaluacionsPersonals.remove(avaluacioPersonal);
            }
        }
    }

    public void editarAvaluacio(AvaluacioPersonal dadesAvaluacio,Long avaluacioId) {
        for (AvaluacioPersonal avaluacioPersonal:this.avaluacionsPersonals
                ) {
            if(avaluacioPersonal.getId()==avaluacioId){
                avaluacioPersonal.editarAvaluacio(dadesAvaluacio);
            }

        }

    }

    public void afegirAvaluacio(AvaluacioPersonal avaluacioPersonal) {
        this.avaluacionsPersonals.add(avaluacioPersonal);
    }

    public void eliminarComanda(Long lectorId, Long comandaId) {
        int cont=0;

        LinkedList<LectorPantalla> lectorsAuxiliars= new LinkedList<>(this.lectorsPantalla);
        for (LectorPantalla lectorPantalla: lectorsAuxiliars ) {
            if(lectorPantalla.getId()==lectorId){
                this.lectorsPantalla.get(cont).eliminarComanda(comandaId);
            }
            cont++;
        }
    }

    public void afegirComanda(Long lectorActualId, Comanda comanda) throws Exception{
        int cont=0;

        LinkedList<LectorPantalla> lectorsAuxiliars= new LinkedList<>(this.lectorsPantalla);
        for (LectorPantalla lectorPantalla: lectorsAuxiliars ) {

            if(lectorPantalla.getId()==lectorActualId){
                LectorPantalla lectorTrobat= this.lectorsPantalla.get(cont);
                if(lectorTrobat.getComandes().contains(comanda)){
                    throw new CommandAlreadyExists(comanda.getNomComanda());
                }

                lectorTrobat.afegirComanda(comanda);
            }
            cont++;
        }
    }
    @Override
    public String toString() {
        return "nomUsuari: " + nomUsuari +
                ", avaluacionsPersonals: " + avaluacionsPersonals +
                ", lectorsPantalla: " + lectorsPantalla;
    }
}
