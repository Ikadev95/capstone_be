package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoesiaRepo extends JpaRepository<Poesia, Long> {
    @Query ("SELECT p FROM Poesia p WHERE p.user.username = :username")
    List<Poesia> findByUsername(@Param("username") String username);
}
