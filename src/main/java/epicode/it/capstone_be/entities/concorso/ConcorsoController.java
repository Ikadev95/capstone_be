package epicode.it.capstone_be.entities.concorso;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<Concorso> updateConcorso(@RequestBody Concorso concorso){
        concorsoService.updateDatiConcorso(concorso);
        return ResponseEntity.ok(concorso);
    }
}
