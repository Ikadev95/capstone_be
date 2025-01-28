package epicode.it.capstone_be.entities.categoria;

import epicode.it.capstone_be.entities.utente.UtenteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(mapper.mapCategoria(categoria));
    }

}
