package epicode.it.capstone_be.entities.utente;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class UtenteRequest {
    private String nome;
    private String cognome;
    private String email;
    private String numero_telefono;

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

    public String getNumero_telefono() {
        return numero_telefono;
    }

    public void setNumero_telefono(String numero_telefono) {
        this.numero_telefono = numero_telefono;
    }

    public UtenteRequest(String nome, String cognome, String email, String numero_telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.numero_telefono = numero_telefono;
    }
}
