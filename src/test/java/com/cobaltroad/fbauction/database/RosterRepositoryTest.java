package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.enumeration.Team;
import com.cobaltroad.fbauction.model.Hitter;
import com.cobaltroad.fbauction.model.Pitcher;
import com.cobaltroad.fbauction.model.Player;
import com.cobaltroad.fbauction.model.Roster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RosterRepositoryTest {
    @Autowired
    RosterRepository rosterRepository;

    @Autowired
    PlayerRepository playerRepository;

    @BeforeEach
    public void setup() {
        Map<String, String> alMap = new HashMap<>();
        alMap.put("Matt Rocca", "Rodney Kings");
        alMap.put("Ron Dollete", "Michigan Left");
        alMap.put("Matt Johnson", "Blue Crush");
        alMap.put("Chris Pimentel", "SeaLab 2022");
        alMap.put("Eric Field", "Field and Son");
        alMap.put("Randy King", "Blame Buckner");
        alMap.put("Neal Campbell", "Needle Free");
        alMap.put("Mike Fiske", "The PerfectPlex");
        alMap.put("Jon Chatalian", "Pythonic Pitufos");
        alMap.put("Craig Fez", "Yabos");
        alMap.put("Dave Catanzano", "Out of the Fog");
        alMap.put("Grant Williams", "The Super Best Friends");

        alMap.forEach((owner, name) -> {
            Roster existingRoster = rosterRepository.findFirstByOwnerAndName(owner, name);
            if (null == existingRoster) {
                Roster roster = Roster.builder().owner(owner).name(name).league("al").build();
                rosterRepository.save(roster);
            }
        });
    }

    @Test
    public void alGrant() {
        saveToRoster(new Object[][] {
                {"Matt Olson", Team.ATHLETICS},
                {"Troy Tulowitzki", Team.YANKEES},
                {"Kendrys Morales", Team.ATHLETICS},
                {"Stephen Piscotty", Team.ATHLETICS},
                {"Mike Trout", Team.ANGELS},
                {"Gary Sanchez", Team.YANKEES},
                {"Jurickson Profar", Team.ATHLETICS},
                {"Matt Chapman", Team.ATHLETICS}
        }, new Object[][] {
                {"Trevor Bauer", Team.INDIANS},
                {"Shane Greene", Team.TIGERS},
                {"Corey Kluber", Team.INDIANS},
                {"Ty Buttrey", Team.ANGELS}
        }, "Grant Williams", "al");
    }

    @Test
    public void alCatanzano() {
        saveToRoster(new Object[][] {
                {"Yonder Alonso", Team.WHITE_SOX},
                {"Ryan O'Hearn", Team.ROYALS},
                {"Yolmer Sanchez", Team.WHITE_SOX},
        }, new Object[][] {
                {"Ryan Yarbrough", Team.RAYS},
                {"Gerrit Cole", Team.ASTROS},
                {"Justin Verlander", Team.ASTROS}
        }, "Dave Catanzano", "al");
    }

    @Test
    public void alRocca() {
        Roster roster = rosterRepository.findFirstByOwnerAndLeague("Matt Rocca", "al");
        Map<String, Team> hs = new HashMap<>();
        hs.put("Ronald Guzman", Team.RANGERS);
        hs.put("Rougned Odor", Team.RANGERS);
        hs.put("Chad Pinder", Team.ATHLETICS);
        hs.put("Trey Mancini", Team.ORIOLES);
        hs.put("Daniel Palka", Team.WHITE_SOX);

        saveHittersToRoster(hs, roster);

        Map<String, Team> ps = new HashMap<>();
        ps.put("Felix Pena", Team.ANGELS);
        ps.put("Zach Britton", Team.YANKEES);

        savePitchersToRoster(ps, roster);
    }

    @Test
    public void teamRon() {
        Map<String, Team> p = new HashMap<>();
        p.put("Christian Vazquez", Team.RED_SOX);
        p.put("Whit Merrifield", Team.ROYALS);
        p.put("Lourdes Gurriel", Team.BLUE_JAYS);
        p.put("Kole Calhoun", Team.ANGELS);
        p.put("Robbie Grossman", Team.ATHLETICS);
    }

    private Player find(String name, Team team) {
        String names[] = name.split("\\s", 2);
        return playerRepository.findFirstByFirstNameAndLastNameAndTeam(names[0], names[1], team);
    }

    private void saveToRoster(Object[][] hitter2dArray, Object[][] pitcher2dArray, String owner, String league) {
        Roster roster = rosterRepository.findFirstByOwnerAndLeague(owner, league);

        Stream.of(hitter2dArray).forEach(data -> {
            Hitter h = (Hitter) find((String) data[0], (Team) data[1]);
            assertNotNull(h);
            h.setRoster(roster);
            playerRepository.save(h);
        });

        Stream.of(pitcher2dArray).forEach(data -> {
            Pitcher p = (Pitcher) find((String) data[0], (Team) data[1]);
            assertNotNull(p);
            p.setRoster(roster);
            playerRepository.save(p);
        });
    }

    private void saveHittersToRoster(Map<String, Team> map, Roster roster) {
        map.forEach((name, team) -> {
            Hitter h = (Hitter) find(name, team);
            assertNotNull(h);
            h.setRoster(roster);
            playerRepository.save(h);
        });
    }

    private void savePitchersToRoster(Map<String, Team> map, Roster roster) {
        map.forEach((name, team) -> {
            Pitcher p = (Pitcher) find(name, team);
            assertNotNull(p);
            p.setRoster(roster);
            playerRepository.save(p);
        });
    }
}
