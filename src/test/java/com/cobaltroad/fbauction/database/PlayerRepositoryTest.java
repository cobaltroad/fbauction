package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.enumeration.Position;
import com.cobaltroad.fbauction.enumeration.Team;
import com.cobaltroad.fbauction.model.Hitter;
import com.cobaltroad.fbauction.model.HitterProjection;
import com.cobaltroad.fbauction.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlayerRepositoryTest {
    @Autowired
    PlayerRepository repository;

    @Autowired
    HitterProjectionRepository hitterProjectionRepository;

    private Hitter hitter;

    @BeforeEach
    public void setup() {
        hitter = new Hitter("foo", "bar");
    }

    @Test
    public void hitterCanBeCreated() {
        repository.save(hitter);
        Hitter actual = (Hitter) repository.findByName("foo", "bar");
        assertEquals(hitter.getName(), actual.getName());
    }

    @Test
    public void hitterPositionsAreSaved() {
        List<Position> positions = Arrays.asList(Position.CATCHER, Position.FIRST_BASEMAN);
        hitter.setPositions(positions);
        repository.save(hitter);
        Hitter actual = (Hitter) repository.findByName("foo", "bar");
        assertEquals(hitter.getPositions(), actual.getPositions());
    }

    @Test
    public void hitterTeamIsSaved() {
        Team team = Team.PADRES;
        hitter.setTeam(team);
        repository.save(hitter);
        Hitter actual = (Hitter) repository.findByTeam(Team.PADRES).get(0);
        assertEquals(hitter.getName(), actual.getName());
    }

    @Test
    public void nlTeamHitters() {
        hitter.setTeam(Team.PADRES);
        repository.save(hitter);

        Hitter nlHitter = new Hitter("la", "dodger");
        nlHitter.setTeam(Team.DODGERS);
        repository.save(nlHitter);

        Hitter alHitter = new Hitter("anaheim", "angel");
        alHitter.setTeam(Team.ANGELS);
        repository.save(alHitter);

        List<Player> players = repository.findByNationalLeague();
        assertEquals(2, players.size());
        assertTrue(players.contains(nlHitter));
        assertFalse(players.contains(alHitter));
    }

    @Test
    public void alTeamHitters() {
        hitter.setTeam(Team.ANGELS);
        repository.save(hitter);

        Hitter nlHitter = new Hitter("la", "dodger");
        nlHitter.setTeam(Team.DODGERS);
        repository.save(nlHitter);

        Hitter alHitter = new Hitter("seattle", "mariner");
        alHitter.setTeam(Team.MARINERS);
        repository.save(alHitter);

        List<Player> players = repository.findByAmericanLeague();
        assertEquals(2, players.size());
        assertTrue(players.contains(alHitter));
        assertFalse(players.contains(nlHitter));
    }

    @Test
    public void hitterHasProjections() {
        HitterProjection projection = HitterProjection.builder().source("foo").build();
        List<HitterProjection> projections = Arrays.asList(projection);
        hitter.setProjections(projections);
        repository.save(hitter);

        Hitter actual = (Hitter) repository.findByName("foo", "bar");
        String source = actual.getProjections().get(0).getSource();
        assertEquals("foo", source);
    }
}
