package epicode.it.capstone_be.entities.comune;

import epicode.it.capstone_be.entities.provincia.ProvinciaRequest;
import lombok.Data;

@Data
public class ComuneResponse {
    private Long id;
    private String nome_comune;
    private String cap;
    private ProvinciaRequest provincia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_comune() {
        return nome_comune;
    }

    public void setNome_comune(String nome_comune) {
        this.nome_comune = nome_comune;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public ProvinciaRequest getProvincia() {
        return provincia;
    }

    public void setProvincia(ProvinciaRequest provincia) {
        this.provincia = provincia;
    }
}
