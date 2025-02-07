package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import epicode.it.capstone_be.entities.categoria.Sezioni;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
import epicode.it.capstone_be.entities.componimenti_concorso.poesia.Poesia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FotografiaRepo extends JpaRepository<Fotografia, Long> {
    @Query("SELECT f FROM Fotografia f WHERE f.user.username = :username")
    List<Fotografia> findByUsername(@Param("username") String username);
}
