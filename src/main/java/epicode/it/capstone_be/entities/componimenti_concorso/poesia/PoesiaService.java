package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.categoria.Categoria;
import epicode.it.capstone_be.entities.categoria.CategoriaRepo;
import epicode.it.capstone_be.entities.categoria.Sezioni;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class PoesiaService {
    private final PoesiaRepo poesiaRepo;
    private final AppUserRepository appUserRepo;
    private final CategoriaRepo categoriaRepo;

    public Poesia getPoesia(Long id) {
        if (!poesiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Poesia non trovata");
        }
        return poesiaRepo.findById(id).get();
    }

    public List<Poesia> getAllPoesie() {
        return poesiaRepo.findAll();
    }

    public Poesia createPoesia(PoesiaRequest poesia) {
        if(!appUserRepo.existsById(poesia.getId_user())) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        if(!categoriaRepo.existsById(poesia.getId_categoria())){
            throw new EntityNotFoundException("Categoria non trovata");
        }
        AppUser u = appUserRepo.findById(poesia.getId_user()).get();
        Categoria c = categoriaRepo.findById(poesia.getId_categoria()).get();

        if(c.getSezione() != Sezioni.POESIA) {
            throw new EntityNotFoundException("Categoria non valida");
        }
        Poesia poesia1 = new Poesia();
        poesia1.setTesto(poesia.getTesto());
        poesia1.setTitolo(poesia.getTitolo());
        poesia1.setData_inserimento(LocalDate.now());
        poesia1.setUser(u);
        poesia1.setCategoria(c);

        return poesiaRepo.save(poesia1);
    }

    public Poesia updatePoesia(Poesia poesia) {
        return poesiaRepo.save(poesia);
    }

    public void deletePoesia(Long id) {
        if (!poesiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Poesia non trovata");
        }
        poesiaRepo.deleteById(id);
    }
}
