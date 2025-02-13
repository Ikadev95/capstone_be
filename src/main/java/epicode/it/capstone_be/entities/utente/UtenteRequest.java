package epicode.it.capstone_be.entities.utente;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UtenteRequest {
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String numero_telefono;
    private String avatar;
}
