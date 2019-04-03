package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.enumeration.Team;
import com.cobaltroad.fbauction.model.Hitter;
import com.cobaltroad.fbauction.model.Pitcher;
import com.cobaltroad.fbauction.model.Player;
import com.cobaltroad.fbauction.model.Roster;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cobaltroad.fbauction.enumeration.Team.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RosterRepositoryTest {
    final static Logger logger = LoggerFactory.getLogger(RosterRepositoryTest.class);

    @Autowired
    RosterRepository rosterRepository;

    @Autowired
    PlayerRepository playerRepository;

    @BeforeEach
    public void setup() {
//        Map<String, String> alMap = new HashMap<>();
//        alMap.put("Matt Rocca", "Rodney Kings");
//        alMap.put("Ron Dollete", "Michigan Left");
//        alMap.put("Matt Johnson", "Blue Crush");
//        alMap.put("Chris Pimentel", "SeaLab 2022");
//        alMap.put("Eric Field", "Field and Son");
//        alMap.put("Randy King", "Blame Buckner");
//        alMap.put("Neal Campbell", "Needle Free");
//        alMap.put("Mike Fiske", "The PerfectPlex");
//        alMap.put("Jon Chatalian", "Pythonic Pitufos");
//        alMap.put("Craig Fez", "Yabos");
//        alMap.put("Dave Catanzano", "Out of the Fog");
//        alMap.put("Grant Williams", "The Super Best Friends");
//
//        alMap.forEach((owner, name) -> {
//            Roster existingRoster = rosterRepository.findFirstByOwnerAndName(owner, name);
//            if (null == existingRoster) {
//                Roster roster = Roster.builder().owner(owner).name(name).league("al").build();
//                rosterRepository.save(roster);
//            }
//        });

    }

    @Test
    @Disabled
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
    @Disabled
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
    @Disabled
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
    @Disabled
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
    @Disabled
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
    @Disabled
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
    @Disabled
    public void alRandy() {
        saveToRoster(new Object[][] {
                {"Yoan Moncada", WHITE_SOX},
                {"Jorge Soler", ROYALS},
                {"Aaron Judge", YANKEES},
                {"Eloy Jimenez", WHITE_SOX},
                {"John Hicks", TIGERS},
                {"Blake Swihart", RED_SOX},
                {"Rafael Devers", RED_SOX},
                {"Nick Gordon", TWINS}
        }, new Object[][] {
                {"Brad Hand", INDIANS},
                {"David Price", RED_SOX},
                {"Blake Snell", RAYS}
        }, "Randy King", "al");
    }

    @Test
    @Disabled
    public void alField() {
        saveToRoster(new Object[][] {
                {"Gleyber Torres", YANKEES},
                {"J.D. Martinez", RED_SOX},
                {"Miguel Andujar", YANKEES},
                {"Nick Madrigal", WHITE_SOX}
        }, new Object[][] {
                {"C.C. Sabathia", YANKEES},
                {"A.J. Puk", ATHLETICS},
                {"Kyle Gibson", TWINS},
                // {"Casey Mize", TIGERS},
                {"Marco Gonzales", MARINERS}
                // {"Brady Singer", ROYALS},
                // {"Brendan McKay", RAYS}
        }, "Eric Field", "al");
    }

    @Test
    @Disabled
    public void alPimentel() {
        saveToRoster(new Object[][] {
                {"Jose Abreu", WHITE_SOX},
                {"Isiah Kiner-Falefa", RANGERS},
                {"Cedric Mullins II", ORIOLES},
                {"Mallex Smith", MARINERS},
                {"Nicholas Castellanos", TIGERS},
                {"Mike Zunino", RAYS},
                {"Danny Jansen", BLUE_JAYS},
                {"Adalberto Mondesi", ROYALS}
        }, new Object[][] {
                {"Jakob Junis", ROYALS},
                {"Mike Minor", RANGERS},
                {"Lance Lynn", RANGERS}
        }, "Chris Pimentel", "al");
    }

    @Test
    @Disabled
    public void alMattyJ() {
        saveToRoster(new Object[][] {
                {"Justin Smoak", BLUE_JAYS},
                {"Brian Goodwin", ANGELS},
                {"Christin Stewart", TIGERS},
                {"Brandon Drury", BLUE_JAYS}
        }, new Object[][] {
                {"Charlie Morton", RAYS},
                {"James Paxton", YANKEES},
                {"Tyler Skaggs", ANGELS},
                {"Luis Severino", YANKEES}
                // {"Hans Crouse", RANGERS}
        }, "Matt Johnson", "al");
    }

    @Test
    @Disabled
    public void alRon() {
        saveToRoster(new Object[][] {
                {"Whit Merrifield", ROYALS},
                {"Kole Calhoun", ANGELS},
                {"Robbie Grossman", ATHLETICS},
                {"Christian Vazquez", RED_SOX},
                {"Lourdes Gurriel Jr.", BLUE_JAYS},
                {"Zack Granite", RANGERS}
        }, new Object[][] {
                {"Chad Green", YANKEES},
                {"Matthew Boyd", TIGERS},
                {"Masahiro Tanaka", YANKEES},
                {"Domingo German", YANKEES},
                {"Trevor Hildenberger", TWINS},
                {"Eduardo Rodriguez", RED_SOX},
                {"Matt Manning", TIGERS}
        }, "Ron Dollete", "al");
    }

    @Test
    @Disabled
    public void alRocca() {
        saveToRoster(new Object[][] {
                {"Ronald Guzman", RANGERS},
                {"Rougned Odor", RANGERS},
                {"Daniel Palka", WHITE_SOX},
                {"Chad Pinder", ATHLETICS},
                {"Trey Mancini", ORIOLES},
                {"Bobby Bradley", INDIANS},
                {"Yordan Alvarez", ASTROS}
        }, new Object[][] {
                {"Felix Pena", ANGELS},
                {"Zach Britton", YANKEES},
                {"Hunter Harvey", ORIOLES}
        }, "Matt Rocca", "al");
    }

    private Player find(String name, Team team, Class klass) throws Exception {
        String names[] = name.split("\\s", 2);
        List<Player> players =  playerRepository.findByFirstNameAndLastNameAndTeam(names[0], names[1], team);
        try {
            return players.stream().filter(klass::isInstance).collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            throw new Exception("Could not find " + name + " (" + team.toString() + ")");
        }
    }

    private void saveToRoster(Object[][] hitter2dArray, Object[][] pitcher2dArray, String owner, String league) {
        Roster roster = rosterRepository.findFirstByOwnerAndLeague(owner, league);

        Stream.of(hitter2dArray).forEach(data -> {
            try {
                Hitter h = (Hitter) find((String) data[0], (Team) data[1], Hitter.class);
                h.setRoster(roster);
                playerRepository.save(h);
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        });

        Stream.of(pitcher2dArray).forEach(data -> {
            try {
                Pitcher p = (Pitcher) find((String) data[0], (Team) data[1], Pitcher.class);
                p.setRoster(roster);
                playerRepository.save(p);
            } catch (Exception e) {
                logger.warn(e.getMessage());
            }
        });
    }
}
