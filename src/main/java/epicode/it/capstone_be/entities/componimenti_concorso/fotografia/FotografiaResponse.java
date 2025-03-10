package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.categoria.CategoriaRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FotografiaResponse {
    private Long id;
    private String titolo;
    private LocalDate data_inserimento;
    private AppUserRequest user;
    private CategoriaRequest categoria;
    private String estensioneFile;
    private String percorsoFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getEstensioneFile() {
        return estensioneFile;
    }

    public void setEstensioneFile(String estensioneFile) {
        this.estensioneFile = estensioneFile;
    }

    public String getPercorsoFile() {
        return percorsoFile;
    }

    public void setPercorsoFile(String percorsoFile) {
        this.percorsoFile = percorsoFile;
    }
}
