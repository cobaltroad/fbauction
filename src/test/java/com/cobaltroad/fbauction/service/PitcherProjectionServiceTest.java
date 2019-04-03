package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.PitcherProjectionRepository;
import com.cobaltroad.fbauction.model.PitcherProjection;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Disabled
public class PitcherProjectionServiceTest {
    @Autowired
    PitcherProjectionService service;

    @Autowired
    LeagueStatService leagueStatService;

    @Autowired
    PitcherProjectionRepository repository;


    @Test
    public void importPitchersCsv() throws IOException, URISyntaxException {
        service.importCsv("fangraphs-pitchers.csv");
        List<PitcherProjection> projections = repository.findAll();
        assertEquals(1409, projections.size());

        leagueStatService.aggregateStatsAndRatings();

        Sort sort = new Sort(Sort.Direction.DESC, "totalRating");
        List<PitcherProjection> ratedProjections = repository.findAll(sort);
        assertEquals(1409, ratedProjections.size());
    }
}
