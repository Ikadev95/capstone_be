package epicode.it.capstone_be.entities.utente;

import lombok.Data;

@Data
public class RegisterJudgeResponse {
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String categoria;

    public RegisterJudgeResponse(Long id, String username, String nome, String cognome, String email, String categoria) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.categoria = categoria;
    }
}
