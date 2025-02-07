package epicode.it.capstone_be.entities.componimenti_concorso.componimento;

import epicode.it.capstone_be.entities.categoria.Sezioni;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ComponimentoService {

    private ComponimentoRepo componimentoRepo;

    public List<Componimento> findAllComponimenti() {return componimentoRepo.findAll();}


}
