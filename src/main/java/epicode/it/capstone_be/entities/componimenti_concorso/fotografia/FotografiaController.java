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

import java.io.IOException;
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
