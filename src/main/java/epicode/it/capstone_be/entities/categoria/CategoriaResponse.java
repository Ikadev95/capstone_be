package epicode.it.capstone_be.entities.categoria;

import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.ComponimentoResponse;
import lombok.Data;

import java.util.List;

@Data
public class CategoriaResponse {
    private String nome_categoria;
    private String descrizione;
    private List<ComponimentoResponse> componimenti;
}
