package accessibilitatweb.accessibilitatweb.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="COMANDES")
public class Comanda {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String nomComanda;

    private String funcio;

    public Comanda(String nomComanda, String funcio, String tipus) {
        this.nomComanda = nomComanda;
        this.funcio = funcio;

    }
    public Comanda(){

    }

    public Long getId() {
        return id;
    }

    public String getNomComanda() {
        return nomComanda;
    }

    public void setNomComanda(String nomComanda) {
        this.nomComanda = nomComanda;
    }

    public String getFuncio() {
        return funcio;
    }

    public void setFuncio(String funcio) {
        this.funcio = funcio;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comanda comanda = (Comanda) o;
        return Objects.equals(nomComanda, comanda.nomComanda);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nomComanda, funcio);
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "nomComanda='" + nomComanda + '\'' +
                ", funcio='" + funcio + '\'' +
                '}';
    }
}
