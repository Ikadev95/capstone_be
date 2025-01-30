package epicode.it.capstone_be.entities.voto;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@PreAuthorize("hasRole('JUDGE')")
@RequestMapping("api/voto")
public class VotoController {
    private final VotoService votoService;
    private final VotoMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<VotoResponse> createVoto(@RequestBody VotoRequest voto){
        System.out.println(voto);
        Voto v = votoService.saveVoto(voto);
        return new ResponseEntity<>(mapper.mapVoto(v), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVoto(@Validated @PathVariable Long id, @AuthenticationPrincipal User userDetails){
        votoService.deleteVoto(id, userDetails.getUsername());
        return new ResponseEntity<>("Voto eliminato con successo", HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<VotoResponse> updateVoto(@Validated @PathVariable Long id, @RequestBody Float voto, @AuthenticationPrincipal User userDetails){
        Voto v = votoService.modifyVoto( voto, id, userDetails.getUsername());
        return new ResponseEntity<>(mapper.mapVoto(v), HttpStatus.OK);
    }

}
