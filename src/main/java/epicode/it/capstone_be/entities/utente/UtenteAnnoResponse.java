package epicode.it.capstone_be.entities.utente;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UtenteAnnoResponse {
    private String username;
    private String nome;
    private String cognome;
    private String email;
}
