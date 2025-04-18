package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PoesiaRepo extends JpaRepository<Poesia, Long> {
    @Query("SELECT p FROM Poesia p WHERE p.user.username = :username " +
            "AND FUNCTION('DATE_PART', 'YEAR', p.data_inserimento) = FUNCTION('DATE_PART', 'YEAR', CURRENT_DATE)")
    List<Poesia> findByUsername(@Param("username") String username);

    @Query(value = """
      SELECT 
        c.titolo,
        p.testo,
        COALESCE(AVG(v.voto), 0) AS mediaVoti,
        u.nome,
        u.cognome, 
        p.id    
      FROM 
        poesia p
        JOIN componimenti c ON p.id = c.id
        JOIN categorie cat ON c.categoria_id = cat.id
        JOIN users a ON c.user_id = a.id
        JOIN utenti u ON a.id = u.user_id
        LEFT JOIN voti v ON p.id = v.componimento_id
      WHERE 
        cat.nome_categoria = :nomeCategoria
        AND EXTRACT(YEAR FROM c.data_inserimento) = EXTRACT(YEAR FROM CURRENT_DATE)
      GROUP BY 
        c.titolo, p.testo, u.nome, u.cognome, p.id
      ORDER BY 
        mediaVoti DESC NULLS LAST
      LIMIT :limit OFFSET :offset
      """, nativeQuery = true)
    List<Object[]> findPoesiePage(
            @Param("nomeCategoria") String nomeCategoria,
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    @Query(value = """
      SELECT COUNT(*) 
      FROM 
        poesia p
        JOIN componimenti c ON p.id = c.id
        JOIN categorie cat ON c.categoria_id = cat.id
        JOIN users a ON c.user_id = a.id
        JOIN utenti u ON a.id = u.user_id
        LEFT JOIN voti v ON p.id = v.componimento_id
      WHERE 
        cat.nome_categoria = :nomeCategoria
        AND EXTRACT(YEAR FROM c.data_inserimento) = EXTRACT(YEAR FROM CURRENT_DATE)
      """, nativeQuery = true)
    long countPoesieByCategoria(@Param("nomeCategoria") String nomeCategoria);

    public Poesia getPoesiaById(Long id);
}
