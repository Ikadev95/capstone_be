package epicode.it.capstone_be.entities.concorso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConcorsoService {

    @Autowired
    private ConcorsoRepo concorsoRepo;

    public Concorso getDatiConcorso() {
        return concorsoRepo.findById(1L).orElse(null);
    }

    public Concorso updateDatiConcorso(Concorso concorso) {
        concorso.setId(1L);
        return concorsoRepo.save(concorso);
    }
}
