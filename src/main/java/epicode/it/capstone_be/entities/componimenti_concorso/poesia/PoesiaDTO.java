package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import jakarta.persistence.Lob;

public class PoesiaDTO {
    private String titolo;
    @Lob
    private String testo;
    private Double mediaVoti;
    private String nome;
    private String cognome;
    private Long id;

    public PoesiaDTO(String titolo, String testo, Double mediaVoti, String nome, String cognome, Long id) {
        this.titolo = titolo;
        this.testo = testo;
        this.mediaVoti = mediaVoti;
        this.nome = nome;
        this.cognome = cognome;
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Double getMediaVoti() {
        return mediaVoti;
    }

    public void setMediaVoti(Double mediaVoti) {
        this.mediaVoti = mediaVoti;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
