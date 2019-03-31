package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.enumeration.Team;
import com.cobaltroad.fbauction.model.Hitter;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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
    public void teamRocca() {
        Map<String, Team> p = new HashMap<>();
        p.put("Ronald Guzman", Team.RANGERS);
        p.put("Rougned Odor", Team.RANGERS);
        p.put("Chad Pinder", Team.ATHLETICS);
        p.put("Trey Mancini", Team.ORIOLES);
        p.put("Daniel Palka", Team.WHITE_SOX);

        p.forEach((name, team) -> {
            Hitter h = (Hitter) find(name, team);
            assertNotNull(h);
        });

//        Hitter hitter = (Hitter) playerRepository.findByName("Bryce", "Harper");
//        Roster roster = Roster.builder().owner("Ron").build();
//        rosterRepository.save(roster);
//
//        hitter.setRoster(roster);
//        playerRepository.save(hitter);
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
}
