package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/fotografie")
@AllArgsConstructor
public class FotografiaController {
    private final FotografiaService fotografiaService;
    private final FotografiaMapper mapper;

    @GetMapping
    public ResponseEntity<List<FotografiaResponse>> getFotografie(){
        List<Fotografia> fotografie = fotografiaService.getAllFotografie();
        return ResponseEntity.ok(mapper.mapFotografiaResponseList(fotografie));
    }

    @GetMapping("{id}")
    public ResponseEntity<FotografiaResponse> getFotografia(@Validated @PathVariable Long id){
        Fotografia fotografia = fotografiaService.getFotografia(id);
        return ResponseEntity.ok(mapper.mapFotografia(fotografia));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFotografia(@Validated @PathVariable Long id){
        fotografiaService.deleteFotografia(id);
        return new ResponseEntity<>("Fotografia eliminata con successo", HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<FotografiaResponse> createFotografia(@Validated @RequestBody FotografiaRequest fotografiaRequest){
        Fotografia fotografia = fotografiaService.createFotografia(fotografiaRequest);
        return new ResponseEntity<>(mapper.mapFotografia(fotografia), HttpStatus.CREATED);
    }


}
