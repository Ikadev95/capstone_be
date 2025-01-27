package epicode.it.capstone_be.entities.provincia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ProvinciaRunner implements ApplicationRunner {

    @Autowired
    private ProvinciaRepo provinciaRepo;

    @Autowired
    private ProvinciaImportCSV provinciaImportCSV;
    @Override
    public void run(ApplicationArguments args) throws Exception {
     if(provinciaRepo.count() == 0){
         String filePath = getClass().getClassLoader().getResource("province.csv").getPath();
         provinciaImportCSV.importCsvProvincia(filePath);
     }
    }
}
