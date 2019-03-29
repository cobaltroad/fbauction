package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.HitterProjectionRepository;
import com.cobaltroad.fbauction.model.HitterProjection;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@Disabled
public class HitterProjectionServiceTest {
    @Autowired
    HitterProjectionService service;

    @Autowired
    LeagueStatService leagueStatService;

    @Autowired
    HitterProjectionRepository repository;

    @Test
    public void importCsv() throws IOException, URISyntaxException {
        service.importCsv("fangraphs-batters.csv");
        List<HitterProjection> projections = repository.findAll();
        assertEquals(1265, projections.size());

        leagueStatService.aggregateStatsAndRatings();

        Sort sort = new Sort(Sort.Direction.DESC, "totalRating");
        List<HitterProjection> ratedProjections = repository.findAll(sort);
        assertEquals(1265, ratedProjections.size());
    }
}
