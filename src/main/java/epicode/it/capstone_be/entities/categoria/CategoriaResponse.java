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
}
