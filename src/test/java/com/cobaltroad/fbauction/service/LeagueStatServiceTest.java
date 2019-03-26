package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.HitterProjectionRepository;
import com.cobaltroad.fbauction.database.LeagueStatRepository;
import com.cobaltroad.fbauction.model.HitterProjection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class LeagueStatServiceTest {

    @Autowired
    HitterProjectionRepository hitterProjectionRepository;

    @Autowired
    LeagueStatRepository leagueStatRepository;

    @Autowired
    LeagueStatService service;

    @Test
    public void aggregateHits() {
        HitterProjection projection1 = HitterProjection.builder().league("nl").hits(1).atBats(2).build();
        hitterProjectionRepository.save(projection1);

        HitterProjection projection2 = HitterProjection.builder().league("nl").hits(30).atBats(90).build();
        hitterProjectionRepository.save(projection2);

        HitterProjection projection3 = HitterProjection.builder().league("nl").hits(50).atBats(250).build();
        hitterProjectionRepository.save(projection3);

        service.aggregateHits("nl");

        assertEquals(27.0, leagueStatRepository.valueOf("nl:average:hits"));
        assertEquals(24.64, leagueStatRepository.valueOf("nl:stddev:hits"), 0.01);

        service.aggregateAtBats("nl");

        assertEquals(114.0, leagueStatRepository.valueOf("nl:average:atbats"));
        assertEquals(125.73, leagueStatRepository.valueOf("nl:stddev:atbats"), 0.01);

        assertEquals(-0.16, service.battingAverageRatingFor(projection1), 0.01);
        assertEquals( 0.31, service.battingAverageRatingFor(projection2), 0.01);
        assertEquals(-0.15, service.battingAverageRatingFor(projection3), 0.01);
    }
}
