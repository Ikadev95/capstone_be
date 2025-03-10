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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome_categoria() {
        return nome_categoria;
    }

    public void setNome_categoria(String nome_categoria) {
        this.nome_categoria = nome_categoria;
    }
}
