package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.auth.requests_responses.AppUserRequest;
import epicode.it.capstone_be.entities.categoria.Categoria;
import epicode.it.capstone_be.entities.categoria.CategoriaRepo;
import epicode.it.capstone_be.entities.categoria.Sezioni;
import epicode.it.capstone_be.entities.componimenti_concorso.fotografia.FotografiaProjection;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PoesiaService {
    @Autowired
    private PoesiaRepo poesiaRepo;
    @Autowired
    private AppUserRepository appUserRepo;
    @Autowired
    private CategoriaRepo categoriaRepo;

    public Poesia getPoesia(Long id) {
        if (!poesiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Poesia non trovata");
        }
        return poesiaRepo.findById(id).get();
    }

    public List<Poesia> getAllPoesie() {
        return poesiaRepo.findAll();
    }

    @Transactional
    public Poesia createPoesia(PoesiaRequest poesia) {
        if (!appUserRepo.existsByUsername(poesia.getUsername())) {
            throw new EntityNotFoundException("Utente non trovato");
        }
        if (!categoriaRepo.existsById(poesia.getId_categoria())) {
            throw new EntityNotFoundException("Categoria non trovata");
        }
        AppUser u = appUserRepo.findByUsername(poesia.getUsername()).get();
        Categoria c = categoriaRepo.findById(poesia.getId_categoria()).get();

        if (c.getSezione() != Sezioni.POESIA) {
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
    public boolean deletePoesia(Long id) {
        if (!poesiaRepo.existsById(id)) {
            throw new EntityNotFoundException("Poesia non trovata");
        }
        poesiaRepo.deleteById(id);
        return true;
    }
    @Transactional
    public List<Poesia> getPoesieByUser(UserDetails userDetails) {
        return poesiaRepo.findByUsername(userDetails.getUsername());
    }

    public Page<PoesiaDTO> getPoesieByCategoria(String nomeCategoria, Pageable pageable) {
        int limit = pageable.getPageSize();
        int offset = (int) pageable.getOffset();

        List<Object[]> risultati = poesiaRepo.findPoesiePage(nomeCategoria, limit, offset);
        long totale = poesiaRepo.countPoesieByCategoria(nomeCategoria);

        List<PoesiaDTO> poesie = risultati.stream()
                .map(obj -> new PoesiaDTO(
                        (String) obj[0], // titolo
                        (String) obj[1], // testo
                        ((Number) obj[2]).doubleValue(), // mediaVoti
                        (String) obj[3], // nome
                        (String) obj[4],
                        (Long) obj[5]
                ))
                .collect(Collectors.toList());

        return new PageImpl<>(poesie, pageable, totale);
    }
}
