package epicode.it.capstone_be.entities.provincia;

import lombok.Data;

@Data
public class ProvinciaResponse {
    private Long id;
    private String nome_provincia;
    private String sigla;
    private String regione;
    private String zona;

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
}
