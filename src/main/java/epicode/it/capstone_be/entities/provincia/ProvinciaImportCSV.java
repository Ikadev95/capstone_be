package epicode.it.capstone_be.entities.provincia;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ProvinciaImportCSV {
    private final ProvinciaRepo provinciaRepo;

    public void importCsvProvincia(String filePath) {
        try(CSVReader csvReader = new CSVReader(new FileReader(filePath)) ){
            List<String[]> rows = csvReader.readAll();

            for (int i = 0; i < rows.size(); i++) {

                String[] input = rows.get(i);
                String field = input[0];
                String[] fields = field.split(";");


                Provincia provincia = new Provincia();
                provincia.setNome_provincia(fields[2]);
                provincia.setSigla(fields[3]);
                provincia.setRegione(fields[1]);
                provincia.setZona(fields[0]);
                provinciaRepo.save(provincia);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}
