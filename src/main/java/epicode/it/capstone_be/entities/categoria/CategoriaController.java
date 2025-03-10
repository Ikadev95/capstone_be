package epicode.it.capstone_be.entities.categoria;

import epicode.it.capstone_be.entities.utente.UtenteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categorie")
@RequiredArgsConstructor

public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private CategoriaMapper mapper;


    @GetMapping("{id}")
    public ResponseEntity<CategoriaResponse> getCategoriaById(@Validated @PathVariable Long id){
        Categoria categoria = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok(mapper.mapCategoria(categoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> getCategoria(){
        List<Categoria> categorie = categoriaService.findAllCategories();
        return ResponseEntity.ok(mapper.mapCategoriaResponseList(categorie));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/sezione/{sezione}")
    public ResponseEntity<List<CategoriaResponse>> getCategoriaBySezione(@Validated @PathVariable Sezioni sezione){
        List<Categoria> categorie = categoriaService.findAllCategoriesBySezione(sezione);
        return ResponseEntity.ok(mapper.mapCategoriaResponseList(categorie));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<CategoriaResponse> createCategoria(@Validated @RequestBody CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaService.createCategoria(categoriaRequest);
        return new ResponseEntity<>(mapper.mapCategoria(categoria), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoria(@Validated @PathVariable Long id){
        categoriaService.deleteCategoria(id);
        return new ResponseEntity<>("Categoria eliminata con successo", HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id_categoria}/giudice/{id_giudice}")
    public ResponseEntity<String> rimuoviGiudice(
            @PathVariable Long id_categoria,
            @PathVariable Long id_giudice) {
            boolean removed = categoriaService.rimuoviGiudice(id_categoria, id_giudice);
            if (removed) {
                return ResponseEntity.ok("Giudice rimosso con successo.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Giudice o Categoria non trovati.");
            }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id_categoria}/giudice/{id_giudice}")
    public ResponseEntity<Map<String, String>> assegnaGiudice(
            @PathVariable Long id_categoria,
            @PathVariable Long id_giudice) {
        boolean assigned = categoriaService.assegnaGiudice(id_categoria, id_giudice);
        Map<String, String> response = new HashMap<>();

        if (assigned) {
            response.put("message", "Giudice assegnato con successo.");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Errore nell'assegnazione del giudice.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}
