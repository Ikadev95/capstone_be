package epicode.it.capstone_be.entities.concorso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConcorsoService {

    @Autowired
    private ConcorsoRepo concorsoRepo;

    private final String UPLOAD_DIR = "uploads/files/";

    public Concorso getDatiConcorso() {
        return concorsoRepo.findById(1L).orElse(null);
    }

    public Concorso updateDatiConcorso(MultipartFile file, String tema, LocalDate data_invio_opere, LocalDateTime data_premiazione, String anno, Float prezzo_singolo, Float prezzo_tre) throws IOException {

        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.write(filePath, file.getBytes());

        Concorso concorso = new Concorso();
        concorso.setTema(tema);
        concorso.setData_invio_opere(data_invio_opere);
        concorso.setData_premiazione(data_premiazione);
        concorso.setAnno(anno);
        concorso.setPrezzo_singolo(prezzo_singolo);
        concorso.setPrezzo_tre(prezzo_tre);
        concorso.setId(1L);
        concorso.setBando(filePath.toString());
        return concorsoRepo.save(concorso);
    }
}
