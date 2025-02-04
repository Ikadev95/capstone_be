package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/fotografie")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
public class FotografiaController {
    private final FotografiaService fotografiaService;
    private final FotografiaMapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<FotografiaResponse>> getFotografie(){
        List<Fotografia> fotografie = fotografiaService.getAllFotografie();
        return ResponseEntity.ok(mapper.mapFotografiaResponseList(fotografie));
    }

    @GetMapping("{id}")
    public ResponseEntity<FotografiaResponse> getFotografia(@Validated @PathVariable Long id){
        Fotografia fotografia = fotografiaService.getFotografia(id);
        return ResponseEntity.ok(mapper.mapFotografia(fotografia));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFotografia(@Validated @PathVariable Long id){
        fotografiaService.deleteFotografia(id);
        return new ResponseEntity<>("Fotografia eliminata con successo", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFotografia(
            @RequestParam("file") MultipartFile file,
            @RequestParam("titolo") String titolo,
            @RequestParam("id_user") String username,
            @RequestParam("id_categoria") Long id_categoria
    ) throws IOException {

        if (username == null || id_categoria == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID utente o ID categoria non possono essere nulli");
        }

        FotografiaRequest fotografiaRequest = new FotografiaRequest();
        fotografiaRequest.setTitolo(titolo);
        fotografiaRequest.setUsername(username);
        fotografiaRequest.setId_categoria(id_categoria);

        Fotografia fotoSalvata = fotografiaService.salvaFotografia(file, fotografiaRequest);
        return ResponseEntity.ok("Fotografia salvata con percorso: " + fotoSalvata.getPercorsoFile());
    }
    }
