package epicode.it.capstone_be.entities.categoria;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(3)
@AllArgsConstructor
public class CategoriaRunner implements ApplicationRunner {

    private final CategoriaRepo categoriaRepo;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        if (categoriaRepo.count() == 0) {

            Categoria categoria = new Categoria();
            categoria.setNome_categoria("Poesia in ITALIANO a tema fisso");
            categoria.setSezione(Sezioni.POESIA);
            categoriaRepo.save(categoria);

            Categoria categoria1 = new Categoria();
            categoria1.setNome_categoria("Poesia in ITALIANO a tema libero");
            categoria1.setSezione(Sezioni.POESIA);
            categoriaRepo.save(categoria1);

            Categoria categoria2 = new Categoria();
            categoria2.setNome_categoria("Poesia in PIEMONTESE a tema fisso");
            categoria2.setSezione(Sezioni.POESIA);
            categoriaRepo.save(categoria2);

            Categoria categoria3 = new Categoria();
            categoria3.setNome_categoria("Poesia in PIEMONTESE a tema libero");
            categoria3.setSezione(Sezioni.POESIA);
            categoriaRepo.save(categoria3);

            Categoria categoria4 = new Categoria();
            categoria4.setNome_categoria("Fotografia a tema fisso");
            categoria4.setSezione(Sezioni.FOTOGRAFIA);
            categoriaRepo.save(categoria4);

            Categoria categoria5 = new Categoria();
            categoria5.setNome_categoria("Fotografia a tema libero");
            categoria5.setSezione(Sezioni.FOTOGRAFIA);
            categoriaRepo.save(categoria5);
        }
    }
}
