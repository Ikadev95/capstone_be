package epicode.it.capstone_be.entities.categoria;

import lombok.Data;

@Data
public class CategoriaRequest {
    private String nome_categoria;
    private Sezioni sezione;
}
