package epicode.it.capstone_be.entities.utente;

import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.auth.requests_responses.LoginRequest;
import epicode.it.capstone_be.entities.comune.ComuneRequest;
import epicode.it.capstone_be.entities.indirizzo.IndirizzoRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UtenteResponse {
    @NotBlank
    private String nome;
    @NotBlank
    private String cognome;
    @NotBlank @Email
    private String email;
    @NotNull
    private LocalDate data_di_nascita;
    private String telefono;
    private String avatar;
    private boolean privacy;
    private IndirizzoRequest indirizzo;
    private ComuneRequest comune_di_nascita;
    private AppUserRequest appUser;

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

    public LocalDate getData_di_nascita() {
        return data_di_nascita;
    }

    public void setData_di_nascita(LocalDate data_di_nascita) {
        this.data_di_nascita = data_di_nascita;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public IndirizzoRequest getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(IndirizzoRequest indirizzo) {
        this.indirizzo = indirizzo;
    }

    public ComuneRequest getComune_di_nascita() {
        return comune_di_nascita;
    }

    public void setComune_di_nascita(ComuneRequest comune_di_nascita) {
        this.comune_di_nascita = comune_di_nascita;
    }

    public AppUserRequest getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserRequest appUser) {
        this.appUser = appUser;
    }
}
