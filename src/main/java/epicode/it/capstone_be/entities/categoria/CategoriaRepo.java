package epicode.it.capstone_be.entities.categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoriaRepo extends JpaRepository<Categoria, Long> {
    @Query("SELECT c FROM Categoria c WHERE c.nome_categoria = :nome_categoria")
    Categoria findByNome_categoria(@Param("nome_categoria") String nome_categoria);

    @Query("SELECT c FROM Categoria c WHERE c.sezione = :sezione")
    List<Categoria> findBySezione(@Param("sezione") Sezioni sezione);

}
