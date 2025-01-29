package epicode.it.capstone_be.entities.categoria;

import epicode.it.capstone_be.entities.utente.UtenteMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//solo l'admin può crearle e gestirle
@RestController
@RequestMapping("/api/categorie")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaMapper mapper;


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

    @PostMapping("/create")
    public ResponseEntity<CategoriaResponse> createCategoria(@Validated @RequestBody CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaService.createCategoria(categoriaRequest);
        return new ResponseEntity<>(mapper.mapCategoria(categoria), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategoria(@Validated @PathVariable Long id){
        categoriaService.deleteCategoria(id);
        return new ResponseEntity<>("Categoria eliminata con successo", HttpStatus.OK);
    }

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

    @PostMapping("/{id_categoria}/giudice/{id_giudice}")
    public ResponseEntity<String> assegnaGiudice(
            @PathVariable Long id_categoria,
            @PathVariable Long id_giudice) {
            boolean assigned = categoriaService.assegnaGiudice(id_categoria, id_giudice);
            if (assigned) {
                return ResponseEntity.ok("Giudice assegnato con successo.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Errore nell'assegnazione del giudice.");
            }
    }

}
