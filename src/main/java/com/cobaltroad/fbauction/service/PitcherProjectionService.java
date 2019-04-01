package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.PlayerRepository;
import com.cobaltroad.fbauction.enumeration.Team;
import com.cobaltroad.fbauction.model.Hitter;
import com.cobaltroad.fbauction.model.HitterProjection;
import com.cobaltroad.fbauction.model.Pitcher;
import com.cobaltroad.fbauction.model.PitcherProjection;
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
public class PitcherProjectionService {

    @Autowired
    PlayerRepository repository;

    public void importCsv(String resourcePath) throws URISyntaxException, IOException {
        Map<String, String> mapping = new HashMap<>();
        mapping.put("Name", "fullName");
        mapping.put("Team", "teamName");

        mapping.put("W", "wins");
        mapping.put("L", "losses");
        mapping.put("GS", "gamesStarted");
        mapping.put("G", "gamesPlayed");
        mapping.put("IP", "inningsPitched");
        mapping.put("H", "hits");
        mapping.put("ER", "earnedRuns");
        mapping.put("HR", "homeruns");
        mapping.put("SO", "strikeouts");
        mapping.put("BB", "walks");

        HeaderColumnNameTranslateMappingStrategy<PitcherProjection> strategy = new HeaderColumnNameTranslateMappingStrategy<>();
        strategy.setType(PitcherProjection.class);
        strategy.setColumnMapping(mapping);
        Reader reader = Files.newBufferedReader(
                Paths.get(ClassLoader.getSystemResource(resourcePath).toURI())
        );
        CsvToBean cb = new CsvToBeanBuilder(reader)
                .withOrderedResults(false)
                .withMappingStrategy(strategy)
                .build();
        List<PitcherProjection> projections = cb.parse();
        for (PitcherProjection projection : projections) {
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
            Pitcher pitcher = new Pitcher(projection);
//            repository.save(pitcher);
        }
    }
}
