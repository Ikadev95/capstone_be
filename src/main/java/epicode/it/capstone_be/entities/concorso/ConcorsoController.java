package epicode.it.capstone_be.entities.concorso;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("api/concorso")
@AllArgsConstructor
public class ConcorsoController {
    @Autowired
    private ConcorsoService concorsoService;

    @GetMapping
    public ResponseEntity<Concorso> getConcorso(){
        Concorso concorso = concorsoService.getDatiConcorso();
        return ResponseEntity.ok(concorso);
    }

    @PostMapping(path = "/upload/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Concorso> updateConcorso(
            @RequestParam(value = "file", required = false)MultipartFile file,
            @RequestParam("tema") String tema,
            @RequestParam("data_invio_opere") LocalDate data_invio_opere,
            @RequestParam("data_premiazione") LocalDateTime data_premiazione,
            @RequestParam("anno") String anno,
            @RequestParam("prezzo_singolo") Float prezzo_singolo,
            @RequestParam("prezzo_tre") Float prezzo_tre
            ) throws IOException {

        Concorso concorso = concorsoService.updateDatiConcorso(file, tema, data_invio_opere, data_premiazione, anno, prezzo_singolo, prezzo_tre);
        return ResponseEntity.ok(concorso);
    }
}
