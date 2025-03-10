package epicode.it.capstone_be.entities.provincia;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/province")
public class ProvinciaController {

    @Autowired
    private ProvinciaService provinciaService;
    @Autowired
    private ProvinciaMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProvinciaResponse>> getProvince(){
        List<Provincia> province = provinciaService.getAll();
        return ResponseEntity.ok(mapper.mapProvinciaResponseList(province));
    }
}
