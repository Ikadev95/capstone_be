package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import epicode.it.capstone_be.auth.AppUser;
import epicode.it.capstone_be.auth.AppUserRepository;
import epicode.it.capstone_be.entities.categoria.Sezioni;
import epicode.it.capstone_be.entities.componimenti_concorso.fotografia.Fotografia;
import epicode.it.capstone_be.entities.componimenti_concorso.poesia.Poesia;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ComponimentoService {

    @Autowired
    private ComponimentoRepo componimentoRepo;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private ComponimentoResponseMapper componimentoResponseMapper;

    public List<Componimento> findAllComponimenti() {return componimentoRepo.findAll();}

    @Transactional
    public Page<ComponimentoFullResponse> findByCategoriaId(UserDetails userDetails, Pageable pageable) {
        AppUser u = appUserRepository.findByUsername(userDetails.getUsername()).get();

        Page<Componimento> componimenti = componimentoRepo.findByCategoriaId(u.getCategoria().getId(), pageable);

        return componimenti.map(componimentoResponseMapper::mapComponimento);
    }


}
