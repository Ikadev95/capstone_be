package epicode.it.capstone_be.entities.componimenti_concorso.componimento;


import epicode.it.capstone_be.entities.categoria.Sezioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComponimentoRepo extends JpaRepository<Componimento, Long> {

}
