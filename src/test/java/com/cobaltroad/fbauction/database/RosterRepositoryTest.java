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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cobaltroad.fbauction.enumeration.Team.*;
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
                {"Matt Olson", ATHLETICS},
                {"Troy Tulowitzki", YANKEES},
                {"Kendrys Morales", ATHLETICS},
                {"Stephen Piscotty", ATHLETICS},
                {"Mike Trout", ANGELS},
                {"Gary Sanchez", YANKEES},
                {"Jurickson Profar", ATHLETICS},
                {"Matt Chapman", ATHLETICS},
                {"Bo Bichette", BLUE_JAYS},
                {"Vladimir Guerrero Jr.", BLUE_JAYS},
                {"Brandon Marsh", ANGELS}
        }, new Object[][] {
                {"Trevor Bauer", INDIANS},
                {"Shane Greene", TIGERS},
                {"Corey Kluber", INDIANS},
                {"Ty Buttrey", ANGELS}
        }, "Grant Williams", "al");
    }

    @Test
    public void alCatanzano() {
        saveToRoster(new Object[][] {
                {"Yonder Alonso", WHITE_SOX},
                {"Ryan O'Hearn", ROYALS},
                {"Yolmer Sanchez", WHITE_SOX},
                // {"Griffin Conine", BLUE_JAYS},
                {"Anthony Alford", BLUE_JAYS}
        }, new Object[][] {
                {"Ryan Yarbrough", RAYS},
                {"Gerrit Cole", ASTROS},
                {"Justin Verlander", ASTROS}
        }, "Dave Catanzano", "al");
    }

    @Test
    public void alFez() {
        saveToRoster(new Object[][] {
                {"Mitch Moreland", RED_SOX},
                {"Ehire Adrianza", TWINS},
                {"Jonathan Villar", ORIOLES},
                {"Jake Marisnick", ASTROS},
                {"Jackie Bradley Jr.", RED_SOX},
                {"Adam Engel", WHITE_SOX},
                {"Jeimer Candelario", TIGERS},
                {"Alex Bregman", ASTROS},
                {"Nolan Jones", INDIANS},
                {"Daz Cameron", TIGERS}
        }, new Object[][] {
                {"Reynaldo Lopez", WHITE_SOX},
                {"Addison Reed", TWINS},
                {"Jaime Barria", ANGELS},
                {"Mike Clevinger", INDIANS},
                {"Brad Keller", ROYALS},
                {"Justus Sheffield", MARINERS}
        }, "Craig Fez", "al");
    }

    @Test
    public void alJon() {
        saveToRoster(new Object[][] {
                {"Steve Pearce", RED_SOX},
                {"Joey Wendle", RAYS},
                {"Jose Altuve", ASTROS},
                {"Leury Garcia", WHITE_SOX},
                {"Michael Brantley", ASTROS},
                {"Willy Adames", RAYS},
                {"Jose Ramirez", INDIANS},
                {"Estevan Florial", YANKEES}
        }, new Object[][] {
                {"Tyler Glasnow", RAYS},
                {"Lucas Giolito", WHITE_SOX},
                {"Blake Parker", TWINS},
                {"Aaron Sanchez", BLUE_JAYS},
                {"Jose Berrios", TWINS},
                {"Jose Alvarado", RAYS},
                {"Brent Honeywell", RAYS}
        }, "Jon Chatalian", "al");
    }

    @Test
    public void alFiske() {
        saveToRoster(new Object[][] {
                {"Tyler White", ASTROS},
                {"Jorge Polanco", TWINS},
                {"Shohei Ohtani", ANGELS},
                {"Brett Gardner", YANKEES},
                {"Eddie Rosario", TWINS},
                {"Ramon Laureano", ATHLETICS},
                {"Shin-Soo Choo", RANGERS},
                {"C.J. Cron", TWINS},
                // {"Jordon Adell", ANGELS},
                {"Xander Bogaerts", RED_SOX}
        }, new Object[][] {
                {"Chris Sale", RED_SOX},
                {"Trevor May", TWINS},
                {"Yonny Chirinos", RAYS},
                {"Nathan Eovaldi", RED_SOX},
                {"Brad Peacock", ASTROS},
                {"Collin McHugh", ASTROS},
                {"Shohei Ohtani", ANGELS},
                {"Triston McKenzie", INDIANS}
        }, "Mike Fiske", "al");

    }

    @Test
    public void alNeal() {
        saveToRoster(new Object[][] {
                {"Brandon Lowe", RAYS},
                {"Niko Goodrum", TIGERS},
                {"Andrew Benintendi", RED_SOX},
                {"Mitch Haniger", MARINERS},
                {"Austin Meadows", RAYS},
                {"Teoscar Hernandez", BLUE_JAYS},
                {"Carlos Correa", ASTROS},
                {"Royce Lewis", TWINS},
                {"Kyle Tucker", ASTROS}
        }, new Object[][] {
                {"Shane Bieber", INDIANS},
                {"Marcus Stroman", BLUE_JAYS},
                {"Alex Colome", WHITE_SOX},
                {"Roberto Osuna", ASTROS},
                {"Blake Treinen", ATHLETICS},
                {"Forrest Whitley", ASTROS}
        }, "Neal Campbell", "al");
    }

    @Test
    public void alRandy() {
        saveToRoster(new Object[][] {

        }, new Object[][] {

        }, "Randy King", "al");

    }

    @Test
    public void alField() {
        saveToRoster(new Object[][] {

        }, new Object[][] {

        }, "Eric Field", "al");
    }

    @Test
    public void alPimentel() {
        saveToRoster(new Object[][] {

        }, new Object[][] {

        }, "Chris Pimentel", "al");
    }

    @Test
    public void alMattyJ() {
        saveToRoster(new Object[][] {

        }, new Object[][] {

        }, "Matt Johnson", "al");
    }

//    @Test
//    public void alRocca() {
//        Roster roster = rosterRepository.findFirstByOwnerAndLeague("Matt Rocca", "al");
//        Map<String, Team> hs = new HashMap<>();
//        hs.put("Ronald Guzman", Team.RANGERS);
//        hs.put("Rougned Odor", Team.RANGERS);
//        hs.put("Chad Pinder", Team.ATHLETICS);
//        hs.put("Trey Mancini", Team.ORIOLES);
//        hs.put("Daniel Palka", Team.WHITE_SOX);
//
//        saveHittersToRoster(hs, roster);
//
//        Map<String, Team> ps = new HashMap<>();
//        ps.put("Felix Pena", Team.ANGELS);
//        ps.put("Zach Britton", Team.YANKEES);
//
//        savePitchersToRoster(ps, roster);
//    }
//
//    @Test
//    public void teamRon() {
//        Map<String, Team> p = new HashMap<>();
//        p.put("Christian Vazquez", Team.RED_SOX);
//        p.put("Whit Merrifield", Team.ROYALS);
//        p.put("Lourdes Gurriel", Team.BLUE_JAYS);
//        p.put("Kole Calhoun", Team.ANGELS);
//        p.put("Robbie Grossman", Team.ATHLETICS);
//    }

    private Player find(String name, Team team, Class klass) {
        String names[] = name.split("\\s", 2);
        List<Player> players =  playerRepository.findByFirstNameAndLastNameAndTeam(names[0], names[1], team);
        return players.stream().filter(klass::isInstance).collect(Collectors.toList()).get(0);
    }

    private void saveToRoster(Object[][] hitter2dArray, Object[][] pitcher2dArray, String owner, String league) {
        Roster roster = rosterRepository.findFirstByOwnerAndLeague(owner, league);

        Stream.of(hitter2dArray).forEach(data -> {
            Hitter h = (Hitter) find((String) data[0], (Team) data[1], Hitter.class);
            System.out.println("HITTER: " + data[0]);
            assertNotNull(h);
            h.setRoster(roster);
            playerRepository.save(h);
        });

        Stream.of(pitcher2dArray).forEach(data -> {
            Pitcher p = (Pitcher) find((String) data[0], (Team) data[1], Pitcher.class);
            System.out.println("PITCHER: " + data[0]);
            assertNotNull(p);
            p.setRoster(roster);
            playerRepository.save(p);
        });
    }
}
