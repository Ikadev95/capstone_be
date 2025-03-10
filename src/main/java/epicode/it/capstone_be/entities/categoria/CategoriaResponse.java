package epicode.it.capstone_be.entities.categoria;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.ComponimentoResponseCategory;
import lombok.Data;

import java.util.List;

@Data
public class CategoriaResponse {
    private Long id;
    private String nome_categoria;
    private Sezioni sezione;
    private List<AppUserRequest> giudici;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }

    public Sezioni getSezione() {
        return sezione;
    }

    public void setSezione(Sezioni sezione) {
        this.sezione = sezione;
    }

    public List<AppUserRequest> getGiudici() {
        return giudici;
    }

    public void setGiudici(List<AppUserRequest> giudici) {
        this.giudici = giudici;
    }
}
