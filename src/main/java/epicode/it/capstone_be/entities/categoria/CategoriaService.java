package epicode.it.capstone_be.entities.categoria;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.auth.Role;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {

    @Autowired
    private CategoriaRepo categoriaRepo;
    @Autowired
    private AppUserRepository appUserRepo;

    public List<Categoria> findAllCategories() {
        return categoriaRepo.findAll();
    }

    public List<Categoria> findAllCategoriesBySezione(Sezioni sezione) {
        return categoriaRepo.findBySezione(sezione);
    }

    public Categoria getCategoriaById(Long id) {
        return categoriaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria non trovata"));
    }

    public Categoria createCategoria(CategoriaRequest c) {
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(c, categoria);
        return categoriaRepo.save(categoria);
    }

    public void deleteCategoria(Long id) {
        if (!categoriaRepo.existsById(id)) {
            throw new EntityNotFoundException("Categoria non trovata");
        }
        categoriaRepo.deleteById(id);
    }

    @Transactional
    public Boolean assegnaGiudice(Long id_categoria, Long id_giudice) {
        if (!appUserRepo.existsById(id_giudice)) {
            throw new EntityNotFoundException("Giudice non trovato");
        }
        if (!categoriaRepo.existsById(id_categoria)) {
            throw new EntityNotFoundException("Categoria non trovata");
        }

        Categoria c = getCategoriaById(id_categoria);
        AppUser u = appUserRepo.findById(id_giudice).get();

        if (!u.getRoles().contains(Role.ROLE_JUDGE)) {
            throw new EntityNotFoundException("L'utente non è un giudice");
        }

        if (!c.getGiudici().contains(u)) {
            c.getGiudici().add(u);
            u.setCategoria(c);
            categoriaRepo.save(c);
            appUserRepo.save(u);
            return true;
        }

        return false;
    }

    @Transactional
    public Boolean rimuoviGiudice(Long id_categoria, Long id_giudice) {
        if (!appUserRepo.existsById(id_giudice)) {
            throw new EntityNotFoundException("Giudice non trovato");
        }
        if (!categoriaRepo.existsById(id_categoria)) {
            throw new EntityNotFoundException("Categoria non trovata");
        }

        Categoria c = getCategoriaById(id_categoria);
        AppUser u = appUserRepo.findById(id_giudice).get();


        if (c.getGiudici().contains(u)) {
            c.getGiudici().remove(u);
            u.setCategoria(c);
            categoriaRepo.save(c);
            appUserRepo.save(u);
            return true;
        }

        return false;
    }
}