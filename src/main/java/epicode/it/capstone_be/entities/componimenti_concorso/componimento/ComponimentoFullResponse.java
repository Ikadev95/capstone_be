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
}