package epicode.it.capstone_be.entities.categoria;

import lombok.Data;

@Data
public class CategoriaRequest {
    private String nome_categoria;
    private Sezioni sezione;

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
}
