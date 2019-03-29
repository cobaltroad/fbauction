package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.HitterProjectionRepository;
import com.cobaltroad.fbauction.enumeration.Team;
import com.cobaltroad.fbauction.model.HitterProjection;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cobaltroad.fbauction.enumeration.Team.alTeams;
import static com.cobaltroad.fbauction.enumeration.Team.nlTeams;

@Service
public class HitterProjectionService {

    @Autowired
    HitterProjectionRepository repository;

    public void importCsv(String resourcePath) throws URISyntaxException, IOException {
        Map<String, String> mapping = new HashMap<>();
// "Name","Team","G","PA","AB","H","2B","3B","HR","R","RBI","BB","SO","HBP","SB","CS","AVG","OBP","SLG","OPS","wOBA","Fld","BsR","WAR","ADP","playerid"
        mapping.put("Name", "fullName");
        mapping.put("Team", "teamName");

        mapping.put("G", "gamesPlayed");
        mapping.put("PA", "plateAppearances");
        mapping.put("AB", "atBats");
        mapping.put("H", "hits");
        mapping.put("2B", "doubles");
        mapping.put("3B", "triples");
        mapping.put("HR", "homeruns");
        mapping.put("R", "runs");
        mapping.put("RBI", "runsBattedIn");
        mapping.put("BB", "walks");
        mapping.put("SO", "strikeouts");
        mapping.put("HBP", "hitByPitch");
        mapping.put("SB", "stolenBases");
        mapping.put("CS", "caughtStealing");

        HeaderColumnNameTranslateMappingStrategy<HitterProjection> strategy = new HeaderColumnNameTranslateMappingStrategy<HitterProjection>();
        strategy.setType(HitterProjection.class);
        strategy.setColumnMapping(mapping);
        Reader reader = Files.newBufferedReader(
                Paths.get(ClassLoader.getSystemResource(resourcePath).toURI())
        );
        CsvToBean cb = new CsvToBeanBuilder(reader)
                .withOrderedResults(false)
                .withMappingStrategy(strategy)
                .build();
        List<HitterProjection> projections = cb.parse();
        for (HitterProjection projection : projections) {
            String teamName = projection.getTeamName();

            if (teamName.isEmpty()) {
                projection.setLeague("fa");
            } else {
                Team team = Team.valueOf(teamName.toUpperCase().replace(' ', '_'));

                if (alTeams.contains(team)) {
                    projection.setLeague("al");
                } else if (nlTeams.contains(team)) {
                    projection.setLeague("nl");
                } else {
                    projection.setLeague("unknown");
                }
            }
            repository.save(projection);
        }
    }
}
