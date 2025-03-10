package epicode.it.capstone_be.entities.concorso;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(4)
@AllArgsConstructor
public class ConcorsoRunner implements ApplicationRunner {

    @Autowired
    private ConcorsoRepo concorsoRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(concorsoRepo.count() == 0) {
            Concorso concorso = new Concorso();
            concorso.setAnno("2025");
            concorso.setTema("Sorsi di felicità");
            concorso.setData_invio_opere(LocalDate.now().plusMonths(3));
            concorso.setData_premiazione(concorso.getData_invio_opere().plusDays(10).atStartOfDay());
            concorso.setBando("Bando 1");
            concorsoRepo.save(concorso);
        }
    }
}
