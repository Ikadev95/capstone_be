package epicode.it.capstone_be.entities.voto;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private VotoService votoService;
    @Autowired
    private VotoMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<VotoResponse> createVoto(@RequestBody VotoRequest voto,@AuthenticationPrincipal User userDetails ){
        System.out.println(voto);
        Voto v = votoService.saveVoto(voto, userDetails);
        return new ResponseEntity<>(mapper.mapVoto(v), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteVoto(@Validated @PathVariable Long id, @AuthenticationPrincipal User userDetails){
        votoService.deleteVoto(id, userDetails.getUsername());
        return new ResponseEntity<>("Voto eliminato con successo", HttpStatus.OK);
    }

    @PatchMapping("update")
    public ResponseEntity<VotoResponse> updateVoto( @RequestBody VotoRequest voto, @AuthenticationPrincipal User userDetails){
        Voto v = votoService.modifyVoto( voto.getVoto(), voto.getId_componimento(), userDetails.getUsername());
        return new ResponseEntity<>(mapper.mapVoto(v), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<VotoResponse> getVoto(@Validated @PathVariable Long id, @AuthenticationPrincipal User userDetails){
        Voto voto = votoService.getVoto(id, userDetails.getUsername());
        return ResponseEntity.ok(mapper.mapVoto(voto));
    }

}
