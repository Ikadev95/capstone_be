package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ComponimentoFullResponse {
    Long id;
    String titolo;
    Long categoriaId;
    String estensioneFile;
    String percorsoFile;
    String testo;


    public ComponimentoFullResponse() {}


    public ComponimentoFullResponse(Long id, String titolo, LocalDate dataInserimento,
                                    Long categoriaId, Long userId,
                                    String estensioneFile, String percorsoFile, String testo) {
        this.id = id;
        this.titolo = titolo;
        this.categoriaId = categoriaId;
        this.estensioneFile = estensioneFile;
        this.percorsoFile = percorsoFile;
        this.testo = testo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getEstensioneFile() {
        return estensioneFile;
    }

    public void setEstensioneFile(String estensioneFile) {
        this.estensioneFile = estensioneFile;
    }

    public String getPercorsoFile() {
        return percorsoFile;
    }

    public void setPercorsoFile(String percorsoFile) {
        this.percorsoFile = percorsoFile;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}