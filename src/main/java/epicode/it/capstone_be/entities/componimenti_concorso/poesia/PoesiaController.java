package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import epicode.it.capstone_be.entities.componimenti_concorso.fotografia.FotografiaProjection;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/poesie")
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
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

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/create")
    public ResponseEntity<PoesiaResponse> createPoesia(@Validated @RequestBody PoesiaRequest poesiaRequest){
        Poesia poesia = poesiaService.createPoesia(poesiaRequest);
        return new ResponseEntity<>(mapper.mapPoesia(poesia), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deletePoesia(@Validated @PathVariable Long id){
        poesiaService.deletePoesia(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Poesia eliminata");
        return ResponseEntity.ok(response);
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/user")
    public ResponseEntity<List<PoesiaResponse>> getPoesieByUser(@AuthenticationPrincipal UserDetails userDetails){
        List<Poesia> poesie = poesiaService.getPoesieByUser(userDetails);
        return ResponseEntity.ok(mapper.mapPoesiaResponseList(poesie));
    }

    @GetMapping("/categoria")
    public Page<PoesiaProjection> getFotografieByCategoria(
            @RequestParam String nomeCategoria,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        return poesiaService.getFotografieByCategoria(nomeCategoria, pageable);
    }
}
