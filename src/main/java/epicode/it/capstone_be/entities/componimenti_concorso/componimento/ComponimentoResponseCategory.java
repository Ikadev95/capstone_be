package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import lombok.Data;

import java.time.LocalDate;
@Data
public class ComponimentoResponseCategory {
    private String titolo;
    private LocalDate data_inserimento;
    private AppUserRequest user;

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
}
