package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/user")
    public ResponseEntity<List<FotografiaResponse>> getFotografieByUser(@AuthenticationPrincipal UserDetails userDetails){
        List<Fotografia> fotografie = fotografiaService.getFotografieByUser(userDetails);
        return ResponseEntity.ok(mapper.mapFotografiaResponseList(fotografie));
    }


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFotografia(@Validated @PathVariable Long id){
        fotografiaService.deleteFotografia(id);
        return new ResponseEntity<>("Fotografia eliminata con successo", HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> uploadFotografia(
            @RequestParam("file") MultipartFile file,
            @RequestParam("titolo") String titolo,
            @RequestParam("id_user") String username,
            @RequestParam("id_categoria") Long id_categoria
    ) throws IOException {

        // Definisci la dimensione massima consentita in byte
        long MAX_FILE_SIZE = 5 * 1024 * 1024;

        // Definisci le dimensioni massime consentite
        int MAX_WIDTH = 4000;
        int MAX_HEIGHT = 3000;

        // Verifica la dimensione del file
        if (file.getSize() > MAX_FILE_SIZE) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Il file è troppo grande. La dimensione massima consentita è di 5 MB.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Controlla la risoluzione dell'immagine
        try (InputStream is = file.getInputStream()) {
            BufferedImage image = ImageIO.read(is);
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();
                if (width > MAX_WIDTH || height > MAX_HEIGHT) {
                    Map<String, String> response = new HashMap<>();
                    response.put("message", "La risoluzione dell'immagine è troppo alta. La dimensione massima consentita è "
                            + MAX_WIDTH + "x" + MAX_HEIGHT + "px.");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Il file non è un'immagine valida.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

        // Verifica che id_user e id_categoria non siano nulli
        if (username == null || id_categoria == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "ID utente o ID categoria non possono essere nulli");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        FotografiaRequest fotografiaRequest = new FotografiaRequest();
        fotografiaRequest.setTitolo(titolo);
        fotografiaRequest.setUsername(username);
        fotografiaRequest.setId_categoria(id_categoria);

        Fotografia fotoSalvata = fotografiaService.salvaFotografia(file, fotografiaRequest);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Fotografia salvata con successo");
        response.put("path", "uploads/fotografie/" + file.getOriginalFilename());
        return ResponseEntity.ok(response);
    }
    }
