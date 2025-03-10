package epicode.it.capstone_be.entities.provincia;

import epicode.it.capstone_be.entities.comune.Comune;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "province")
public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome_provincia;
    private String sigla;
    private String regione;
    private String zona;

    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_provincia() {
        return nome_provincia;
    }

    public void setNome_provincia(String nome_provincia) {
        this.nome_provincia = nome_provincia;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public List<Comune> getComuni() {
        return comuni;
    }

    public void setComuni(List<Comune> comuni) {
        this.comuni = comuni;
    }
}