package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.HitterProjectionRepository;
import com.cobaltroad.fbauction.database.LeagueStatRepository;
import com.cobaltroad.fbauction.model.HitterProjection;
import org.junit.jupiter.api.Disabled;
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
@Disabled
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

        service.updateRatings();

        assertEquals(-0.16, projection1.getBattingAverageRating(), 0.01);
        assertEquals( 0.31, projection2.getBattingAverageRating(), 0.01);
        assertEquals(-0.15, projection3.getBattingAverageRating(), 0.01);
    }

    @Test
    public void aggregateRuns() {
        HitterProjection projection1 = HitterProjection.builder().league("nl").runs(50).build();
        hitterProjectionRepository.save(projection1);

        HitterProjection projection2 = HitterProjection.builder().league("nl").runs(100).build();
        hitterProjectionRepository.save(projection2);

        HitterProjection projection3 = HitterProjection.builder().league("nl").runs(80).build();
        hitterProjectionRepository.save(projection3);

        service.aggregateRuns("nl");

        assertEquals(76.67, leagueStatRepository.valueOf("nl:average:runs"), 0.01);
        assertEquals(25.17, leagueStatRepository.valueOf("nl:stddev:runs"), 0.01);

        service.updateRatings();

        assertEquals(-1.06, projection1.getRunsRating(), 0.01);
        assertEquals( 0.93, projection2.getRunsRating(), 0.01);
        assertEquals( 0.13, projection3.getRunsRating(), 0.01);
    }

    @Test
    public void aggregateRBIs() {
        HitterProjection projection1 = HitterProjection.builder().league("nl").runsBattedIn(50).build();
        hitterProjectionRepository.save(projection1);

        HitterProjection projection2 = HitterProjection.builder().league("nl").runsBattedIn(100).build();
        hitterProjectionRepository.save(projection2);

        HitterProjection projection3 = HitterProjection.builder().league("nl").runsBattedIn(80).build();
        hitterProjectionRepository.save(projection3);

        service.aggregateRBIs("nl");

        assertEquals(76.67, leagueStatRepository.valueOf("nl:average:rbi"), 0.01);
        assertEquals(25.17, leagueStatRepository.valueOf("nl:stddev:rbi"), 0.01);

        service.updateRatings();

        assertEquals(-1.06, projection1.getRbiRating(), 0.01);
        assertEquals( 0.93, projection2.getRbiRating(), 0.01);
        assertEquals( 0.13, projection3.getRbiRating(), 0.01);
    }

    @Test
    public void aggregateHRs() {
        HitterProjection projection1 = HitterProjection.builder().league("nl").homeruns(50).build();
        hitterProjectionRepository.save(projection1);

        HitterProjection projection2 = HitterProjection.builder().league("nl").homeruns(100).build();
        hitterProjectionRepository.save(projection2);

        HitterProjection projection3 = HitterProjection.builder().league("nl").homeruns(80).build();
        hitterProjectionRepository.save(projection3);

        service.aggregateHRs("nl");

        assertEquals(76.67, leagueStatRepository.valueOf("nl:average:hr"), 0.01);
        assertEquals(25.17, leagueStatRepository.valueOf("nl:stddev:hr"), 0.01);

        service.updateRatings();

        assertEquals(-1.06, projection1.getHomerunRating(), 0.01);
        assertEquals( 0.93, projection2.getHomerunRating(), 0.01);
        assertEquals( 0.13, projection3.getHomerunRating(), 0.01);
    }

    @Test
    public void aggregateSBs() {
        HitterProjection projection1 = HitterProjection.builder().league("nl").stolenBases(50).build();
        hitterProjectionRepository.save(projection1);

        HitterProjection projection2 = HitterProjection.builder().league("nl").stolenBases(100).build();
        hitterProjectionRepository.save(projection2);

        HitterProjection projection3 = HitterProjection.builder().league("nl").stolenBases(80).build();
        hitterProjectionRepository.save(projection3);

        service.aggregateSBs("nl");

        assertEquals(76.67, leagueStatRepository.valueOf("nl:average:sb"), 0.01);
        assertEquals(25.17, leagueStatRepository.valueOf("nl:stddev:sb"), 0.01);

        service.updateRatings();

        assertEquals(-1.06, projection1.getStolenBaseRating(), 0.01);
        assertEquals( 0.93, projection2.getStolenBaseRating(), 0.01);
        assertEquals( 0.13, projection3.getStolenBaseRating(), 0.01);

    }

}
