package epicode.it.capstone_be.entities.voto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/voto")
public class VotoController {
    private final VotoService votoService;
    private final VotoMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<VotoResponse> createVoto(@RequestParam VotoRequest voto){
        Voto v = votoService.saveVoto(voto);
        return new ResponseEntity<>(mapper.mapVoto(v), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVoto(@Validated @PathVariable Long id){
        votoService.deleteVoto(id);
        return new ResponseEntity<>("Voto eliminato con successo", HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<VotoResponse> updateVoto(@Validated @PathVariable Long id, @RequestBody double voto){
        Voto v = votoService.modifyVoto( voto, id);
        return new ResponseEntity<>(mapper.mapVoto(v), HttpStatus.OK);
    }

}
