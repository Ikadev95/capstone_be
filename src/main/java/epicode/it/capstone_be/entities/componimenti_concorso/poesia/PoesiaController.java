package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/poesie")
@AllArgsConstructor
public class PoesiaController {

    private final PoesiaService poesiaService;
    private final PoesiaMapper mapper;

    @GetMapping
    public ResponseEntity<List<PoesiaResponse>> getPoesie(){
        List<Poesia> poesie = poesiaService.getAllPoesie();
        return ResponseEntity.ok(mapper.mapPoesiaResponseList(poesie));
    }

    @GetMapping("{id}")
    public ResponseEntity<PoesiaResponse> getPoesia(@Validated @PathVariable Long id){
        Poesia poesia = poesiaService.getPoesia(id);
        return ResponseEntity.ok(mapper.mapPoesia(poesia));
    }

    @PostMapping("/create")
    public ResponseEntity<PoesiaResponse> createPoesia(@Validated @RequestBody PoesiaResponse poesiaResponse){
        Poesia poesia = new Poesia();
        BeanUtils.copyProperties(poesiaResponse, poesia);
        poesiaService.createPoesia(poesia);

        return new ResponseEntity<>(mapper.mapPoesia(poesia), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePoesia(@Validated @PathVariable Long id){
        poesiaService.deletePoesia(id);
        return new ResponseEntity<>("Poesia eliminata con successo", HttpStatus.OK);
    }
}
