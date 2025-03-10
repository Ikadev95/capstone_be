package epicode.it.capstone_be.entities.comune;

import epicode.it.capstone_be.entities.provincia.ProvinciaRequest;
import lombok.Data;

@Data
public class ComuneRequest {
    private String nome_comune;
    private int cap;
    private ProvinciaRequest provincia;

    public String getNome_comune() {
        return nome_comune;
    }

    public void setNome_comune(String nome_comune) {
        this.nome_comune = nome_comune;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public ProvinciaRequest getProvincia() {
        return provincia;
    }

    public void setProvincia(ProvinciaRequest provincia) {
        this.provincia = provincia;
    }
}
