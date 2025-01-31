package epicode.it.capstone_be.entities.comune;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comuni")
@AllArgsConstructor
public class ComuneController {
    private final ComuneService comuneService;
    private final ComuneMapper mapper;

    @GetMapping("byProvincia/{id_provincia}")
    public ResponseEntity<List<ComuneResponse>> getComuneByProvincia(@PathVariable Long id_provincia ){
        List<Comune> comune = comuneService.getComuneByProvincia(id_provincia);
        return ResponseEntity.ok(mapper.mapComuneResponseList(comune));
    }
}
