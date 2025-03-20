package epicode.it.capstone_be.entities.comune;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import epicode.it.capstone_be.entities.provincia.Provincia;
import epicode.it.capstone_be.entities.provincia.ProvinciaRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
@AllArgsConstructor
public class ComuneImportCSV {

    @Autowired
    private  ComuneRepo comuneRepo;
    @Autowired
    private  ProvinciaRepo provinciaRepo;

    public void importCsvComune(InputStream inputStream) {
        try (CSVReader csvReader = new CSVReader(new BufferedReader(new InputStreamReader(inputStream)))) {
            List<String[]> rows = csvReader.readAll();

            for (int i = 0; i < rows.size(); i++) {
                String[] input = rows.get(i);
                String field = input[0];
                String[] fields = field.split(";");

                Provincia provincia = provinciaRepo.findBySigla(fields[1]);

                Comune comune = new Comune();
                comune.setNome_comune(fields[0]);
                comune.setCap(fields[3]);
                comune.setProvincia(provincia);
                comuneRepo.save(comune);
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }
}