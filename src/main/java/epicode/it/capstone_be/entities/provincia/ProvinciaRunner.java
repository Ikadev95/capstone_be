package epicode.it.capstone_be.entities.provincia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Order(1)
public class ProvinciaRunner implements ApplicationRunner {

    @Autowired
    private ProvinciaRepo provinciaRepo;

    @Autowired
    private ProvinciaImportCSV provinciaImportCSV;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (provinciaRepo.count() == 0) {
            // Legge il file province.csv come InputStream
            InputStream is = getClass().getClassLoader().getResourceAsStream("province.csv");
            if (is != null) {
                provinciaImportCSV.importCsvProvincia(is);
            } else {
                System.err.println("File province.csv non trovato nel classpath!");
            }
        }
    }
}