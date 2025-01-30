package epicode.it.capstone_be.entities.voto;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.auth.Role;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.ComponimentoRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VotoService {
    private final VotoRepo votoRepo;
    private final AppUserRepository appUserRepo;
    private final ComponimentoRepo componimentoRepo;

    public Voto saveVoto(VotoRequest voto) {
        if(!componimentoRepo.existsById(voto.getId_componimento())) {
            throw new EntityNotFoundException("Componimento non trovato");
        }
        if(!appUserRepo.existsById(voto.getId_giudice())) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        Componimento c = componimentoRepo.findById(voto.getId_componimento()).get();
        AppUser u = appUserRepo.findById(voto.getId_componimento()).get();

        Voto v = new Voto();
        v.setComponimento(c);
        v.setUser(u);
        System.out.println(voto.getVoto());
        v.setVoto( voto.getVoto());
        return votoRepo.save(v);
    }

    public void deleteVoto(Long id, String username) {
        AppUser u = appUserRepo.findByUsername(username).get();
        Voto v = votoRepo.findById(id).get();
        if(!v.getUser().equals(u)) {
            throw new EntityNotFoundException("Non sei il proprietario di questo voto");
        }
        if(!votoRepo.existsById(id)) {
            throw new EntityNotFoundException("Voto non trovato");
        }
        votoRepo.deleteById(id);
    }

    public Voto modifyVoto(Float voto, Long id, String username) {
        if(!votoRepo.existsById(id)) {
            throw new EntityNotFoundException("Voto non trovato");
        }
        AppUser u = appUserRepo.findByUsername(username).get();
        Voto v = votoRepo.findById(id).get();
        if(!v.getUser().equals(u)) {
            throw new EntityNotFoundException("Non sei il proprietario di questo voto");
        }
        if (voto < 0 || voto > 10) {
            throw new IndexOutOfBoundsException("Voto non valido") ;
        }

        v.setVoto( voto);
        votoRepo.save(v);
        return v;
    }
}
