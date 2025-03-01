package epicode.it.capstone_be.entities.componimenti_concorso.poesia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoesiaRepo extends JpaRepository<Poesia, Long> {
    @Query(value = "SELECT DISTINCT p.* FROM poesia p JOIN componimenti c ON p.id = c.id WHERE c.user_id = (SELECT u.id FROM users u WHERE u.username = :username) AND EXTRACT(YEAR FROM c.data_inserimento) = EXTRACT(YEAR FROM CURRENT_DATE)", nativeQuery = true)
    List<Poesia> findByUsername(@Param("username") String username);

    @Query(value = """
      SELECT 
      c.titolo AS titolo,
      p.testo AS testo,
      COALESCE(AVG(v.voto), 0) AS mediaVoti,  
      u.nome AS nome,
      u.cognome AS cognome,
      cat.nome_categoria AS nomeCategoria
      FROM 
      poesia p
      JOIN 
      componimenti c ON p.id = c.id
      JOIN 
      categorie cat ON c.categoria_id = cat.id
      JOIN 
      users a ON c.user_id = a.id
      JOIN 
      utenti u ON a.id = u.user_id
      LEFT JOIN 
      voti v ON p.id = v.componimento_id
      WHERE 
      cat.nome_categoria = :nomeCategoria
      AND EXTRACT(YEAR FROM c.data_inserimento) = EXTRACT(YEAR FROM CURRENT_DATE)
      GROUP BY c.titolo, p.testo, u.nome, u.cognome, cat.nome_categoria
      ORDER BY
       mediaVoti DESC NULLS LAST
      """,
            countQuery = """
      SELECT COUNT(*)
      FROM 
      poesia p
      JOIN 
      componimenti c ON p.id = c.id
      JOIN 
      categorie cat ON c.categoria_id = cat.id
      JOIN 
      users a ON c.user_id = a.id
      JOIN
      utenti u ON a.id = u.user_id
      LEFT JOIN 
      voti v ON p.id = v.componimento_id
      WHERE 
      cat.nome_categoria = :nomeCategoria
      AND EXTRACT(YEAR FROM c.data_inserimento) = EXTRACT(YEAR FROM CURRENT_DATE)
      """, nativeQuery = true)
    Page<PoesiaProjection> findPoesieByCategoria(@Param("nomeCategoria") String nomeCategoria, Pageable pageable);
}
