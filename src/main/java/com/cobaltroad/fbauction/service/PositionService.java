package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.PlayerRepository;
import com.cobaltroad.fbauction.enumeration.Position;
import com.cobaltroad.fbauction.model.Hitter;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PositionService {
    @Autowired
    PlayerRepository repository;

    public void importCsv(String resourcePath, Position position) throws URISyntaxException, IOException {
        Reader reader = Files.newBufferedReader(
                Paths.get(ClassLoader.getSystemResource(resourcePath).toURI())
        );
        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        String[] line;
        while ((line = csvReader.readNext()) != null) {
            String[] names = line[0].split("\\s", 2);
            if (names.length == 0) {
                System.out.println(line);
            }
            Hitter hitter = (Hitter) repository.findByName(names[0], names[1]);
            if (hitter == null) {
                System.out.println(line);
            }
            List<Position> positions = hitter.getPositions();
            if (!positions.contains(position)) {
                positions.add(position);
                hitter.setPositions(positions);
                repository.save(hitter);
            }
        }
        reader.close();
        csvReader.close();
    }
}
