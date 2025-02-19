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
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class VotoService {
    private final VotoRepo votoRepo;
    private final AppUserRepository appUserRepo;
    private final ComponimentoRepo componimentoRepo;

    public Voto saveVoto(VotoRequest voto, User userDetails) {
        if (!componimentoRepo.existsById(voto.getId_componimento())) {
            throw new EntityNotFoundException("Componimento non trovato");
        }

        AppUser user = appUserRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        Componimento c = componimentoRepo.findById(voto.getId_componimento())
                .orElseThrow(() -> new EntityNotFoundException("Componimento non trovato"));


        AppUser u = appUserRepo.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        Voto v = new Voto();
        v.setComponimento(c);
        v.setUser(u);
        v.setVoto(voto.getVoto());

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

    @Transactional
    public Voto modifyVoto(Float voto, Long id, String username) {
        System.out.println("Voto: " + voto.toString() + " IdComponimento: " + id + " username: " + username);

        AppUser u = appUserRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));


        Voto v = votoRepo.findByComponimentoIdAndUserId(id, u.getId());

        if (v == null) {
            throw new EntityNotFoundException("Voto non trovato per questo componimento e utente");
        }


        if (!v.getUser().getId().equals(u.getId())) { // Evita cicli infiniti
            throw new SecurityException("Non sei il proprietario di questo voto");
        }

        if (voto < 0 || voto > 10) {
            throw new IndexOutOfBoundsException("Voto non valido: deve essere tra 0 e 10");
        }

        v.setVoto(voto);
        return votoRepo.save(v);
    }

    @Transactional
    public Voto getVoto(Long id, String username) {
        AppUser u = appUserRepo.findByUsername(username).get();
        Voto v = votoRepo.findByComponimentoIdAndUserId(id, u.getId());
        if(!v.getUser().equals(u)) {
            throw new EntityNotFoundException("Non sei il proprietario di questo voto");
        }
        return v;
    }
}
