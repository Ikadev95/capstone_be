package epicode.it.capstone_be.entities.componimenti_concorso.fotografia;

import epicode.it.capstone_be.entities.categoria.Sezioni;
import epicode.it.capstone_be.entities.componimenti_concorso.componimento.Componimento;
import epicode.it.capstone_be.entities.componimenti_concorso.poesia.Poesia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FotografiaRepo extends JpaRepository<Fotografia, Long> {
    @Query("SELECT f FROM Fotografia f WHERE f.user.username = :username " +
            "AND FUNCTION('DATE_PART', 'YEAR', f.data_inserimento) = FUNCTION('DATE_PART', 'YEAR', CURRENT_DATE)")
    List<Fotografia> findByUsername(@Param("username") String username);

    @Query(value = """
    SELECT 
        c.titolo AS titolo,
        f.percorso_file AS percorsoFile,
        COALESCE(AVG(v.voto), 0) AS mediaVoti, 
        u.nome AS nome,
        u.cognome AS cognome,
        cat.nome_categoria AS nomeCategoria
    FROM 
        fotografia f
    JOIN 
        componimenti c ON f.id = c.id
    JOIN 
        categorie cat ON c.categoria_id = cat.id
    JOIN 
        users a ON c.user_id = a.id
    JOIN 
        utenti u ON a.id = u.user_id
    LEFT JOIN 
        voti v ON f.id = v.componimento_id
    WHERE 
        cat.nome_categoria = :nomeCategoria
        AND EXTRACT(YEAR FROM c.data_inserimento) = EXTRACT(YEAR FROM CURRENT_DATE)
    GROUP BY 
        c.titolo, f.percorso_file, u.nome, u.cognome, cat.nome_categoria
    ORDER BY 
        mediaVoti DESC NULLS LAST
    """,
            countQuery = """
    SELECT COUNT(*)
    FROM 
        fotografia f
    JOIN 
        componimenti c ON f.id = c.id
    JOIN 
        categorie cat ON c.categoria_id = cat.id
    JOIN 
        users a ON c.user_id = a.id
    JOIN 
        utenti u ON a.id = u.user_id
    LEFT JOIN 
        voti v ON f.id = v.componimento_id
    WHERE 
        cat.nome_categoria = :nomeCategoria
        AND EXTRACT(YEAR FROM c.data_inserimento) = EXTRACT(YEAR FROM CURRENT_DATE)
    """,
            nativeQuery = true)
    Page<FotografiaProjection> findFotografieByCategoria(@Param("nomeCategoria") String nomeCategoria, Pageable pageable);

}
