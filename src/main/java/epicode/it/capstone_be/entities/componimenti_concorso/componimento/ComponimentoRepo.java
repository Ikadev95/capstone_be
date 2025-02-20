package epicode.it.capstone_be.entities.componimenti_concorso.componimento;


import epicode.it.capstone_be.entities.categoria.Sezioni;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComponimentoRepo extends JpaRepository<Componimento, Long> {

    @Query("""
                SELECT c FROM Componimento c
                LEFT JOIN Fotografia f ON c.id = f.id
                LEFT JOIN Poesia p ON c.id = p.id
                WHERE c.categoria.id = :categoriaId
            """)
    Page<Componimento> findByCategoriaId(@Param("categoriaId") Long categoriaId, Pageable pageable);


}

