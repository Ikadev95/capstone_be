package epicode.it.capstone_be.entities.concorso;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "concorso")
public class Concorso {
    @Id
    private Long id = 1L;

    private String tema;
    private LocalDate data_invio_opere;
    private LocalDateTime data_premiazione;
    private String anno;
    private String bando;
    private Float prezzo_singolo;
    private Float prezzo_tre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public LocalDate getData_invio_opere() {
        return data_invio_opere;
    }

    public void setData_invio_opere(LocalDate data_invio_opere) {
        this.data_invio_opere = data_invio_opere;
    }

    public LocalDateTime getData_premiazione() {
        return data_premiazione;
    }

    public void setData_premiazione(LocalDateTime data_premiazione) {
        this.data_premiazione = data_premiazione;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getBando() {
        return bando;
    }

    public void setBando(String bando) {
        this.bando = bando;
    }

    public Float getPrezzo_singolo() {
        return prezzo_singolo;
    }

    public void setPrezzo_singolo(Float prezzo_singolo) {
        this.prezzo_singolo = prezzo_singolo;
    }

    public Float getPrezzo_tre() {
        return prezzo_tre;
    }

    public void setPrezzo_tre(Float prezzo_tre) {
        this.prezzo_tre = prezzo_tre;
    }
}