package epicode.it.capstone_be.auth.requests_responses;

import epicode.it.capstone_be.entities.indirizzo.IndirizzoRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDate;

@Data
public class RegisterRequest {

    @NotBlank(message = "Username è obbligatorio")
    private String username;

    @NotBlank(message = "Password è obbligatoria")
    private String password;

    @NotBlank(message = "Nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Cognome è obbligatorio")
    private String cognome;

    @NotBlank(message = "Email è obbligatoria")
    @Email(message = "Email non valida")
    private String email;

    private String telefono;

    @NotNull(message = "La privacy deve essere accettata")
    private Boolean privacy;

    @NotNull(message = "La data di nascita è obbligatoria")
    private LocalDate data_di_nascita;

    @NotNull(message = "Indirizzo è obbligatorio")
    private IndirizzoRequest indirizzo;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public LocalDate getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(LocalDate data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public IndirizzoRequest getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(IndirizzoRequest indirizzo) {
        this.indirizzo = indirizzo;
    }
}