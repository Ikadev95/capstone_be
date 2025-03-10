package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.categoria.Categoria;
import epicode.it.capstone_be.entities.categoria.CategoriaRequest;
import epicode.it.capstone_be.entities.categoria.CategoriaResponse;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ComponimentoResponse {
    private String titolo;
    private LocalDate data_inserimento;
    private AppUserRequest user;
    private CategoriaRequest categoria;

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public LocalDate getData_inserimento() {
        return data_inserimento;
    }

    public void setData_inserimento(LocalDate data_inserimento) {
        this.data_inserimento = data_inserimento;
    }

    public AppUserRequest getUser() {
        return user;
    }

    public void setUser(AppUserRequest user) {
        this.user = user;
    }

    public CategoriaRequest getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaRequest categoria) {
        this.categoria = categoria;
    }
}
