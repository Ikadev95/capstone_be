package epicode.it.capstone_be.entities.comune;

import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@AllArgsConstructor
public class ComuneRunner implements ApplicationRunner {

    private final ComuneImportCSV comuneImportCSV;
    private  final ComuneRepo comuneRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(comuneRepo.count() == 0) {
            String filePath = getClass().getClassLoader().getResource("comuni.csv").getPath();
            comuneImportCSV.importCsvComune(filePath);
        }
    }
}
