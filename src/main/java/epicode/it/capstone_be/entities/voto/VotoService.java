package epicode.it.capstone_be.entities.voto;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.ComponimentoRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
        if(!appUserRepo.existsById(voto.getId_user())) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        Componimento c = componimentoRepo.findById(voto.getId_componimento()).get();
        AppUser u = appUserRepo.findById(voto.getId_user()).get();
        Voto v = new Voto();
        v.setComponimento(c);
        v.setUser(u);
        v.setVoto(voto.getVoto());
        return votoRepo.save(v);
    }

    public void deleteVoto(Long id) {
        if(!votoRepo.existsById(id)) {
            throw new EntityNotFoundException("Voto non trovato");
        }
        votoRepo.deleteById(id);
    }

    public Voto modifyVoto(double voto, Long id) {
        if(!votoRepo.existsById(id)) {
            throw new EntityNotFoundException("Voto non trovato");
        }
        Voto v = votoRepo.findById(id).get();
        if (voto < 0 || voto > 10) {
            throw new IndexOutOfBoundsException("Voto non valido") ;
        }

        v.setVoto( voto);
        votoRepo.save(v);
        return v;
    }
}
