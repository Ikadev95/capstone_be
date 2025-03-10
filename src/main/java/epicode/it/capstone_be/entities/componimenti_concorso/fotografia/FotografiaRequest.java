package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FotografiaRequest {
    private String titolo;
    private String username;
    private Long id_categoria;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }
}
