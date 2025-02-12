package epicode.it.capstone_be.entities.utente;

import lombok.Data;

@Data
public class RegisterJudgeResponse {
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String nome_categoria;

    public RegisterJudgeResponse(Long id, String username, String nome, String cognome, String email, String nome_categoria) {
        this.id = id;
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        if(nome_categoria == null) {
            this.nome_categoria = "non assegnato";}
        this.nome_categoria = nome_categoria;
    }
}
