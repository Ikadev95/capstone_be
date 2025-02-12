package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ComponimentoFullResponse {
    Long id;
    String titolo;
    LocalDate dataInserimento;
    Long categoriaId;
    Long userId;
    String estensioneFile; // Solo se è una fotografia
    String percorsoFile;   // Solo se è una fotografia
    String testo;

    // Costruttore senza argomenti
    public ComponimentoFullResponse() {}

    // Costruttore con parametri
    public ComponimentoFullResponse(Long id, String titolo, LocalDate dataInserimento,
                                    Long categoriaId, Long userId,
                                    String estensioneFile, String percorsoFile, String testo) {
        this.id = id;
        this.titolo = titolo;
        this.dataInserimento = dataInserimento;
        this.categoriaId = categoriaId;
        this.userId = userId;
        this.estensioneFile = estensioneFile;
        this.percorsoFile = percorsoFile;
        this.testo = testo;
    }
}