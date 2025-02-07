package epicode.it.capstone_be.entities.componimenti_concorso.componimento;


import epicode.it.capstone_be.entities.categoria.Sezioni;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("isAuthenticated()")
@RequestMapping("api/componimenti")
public class ComponimentoController {
    private final ComponimentoService componimentoService;
    private final ComponimentoMapper mapper;

    @GetMapping
    public ResponseEntity<List<ComponimentoResponse>> getComponimenti(){
        List<Componimento> componimenti = componimentoService.findAllComponimenti();
        return ResponseEntity.ok(mapper.mapComponimentoResponseList(componimenti));
    }


}
