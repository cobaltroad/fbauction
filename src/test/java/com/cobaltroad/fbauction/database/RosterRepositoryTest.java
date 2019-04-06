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
        Map<String, String> nlMap = new HashMap<>();
        nlMap.put("Matt Rocca", "Western Religion");
        nlMap.put("Chris Pimentel", "Case O' Mondays");
        nlMap.put("Neal Campbell", "Mint Soup");
        nlMap.put("Jon Chatalian", "Delirium of Disorder");
        nlMap.put("Craig Fez", "Madison Muskies");
        nlMap.put("Matt Johnson", "Fushizzle Televizzle");
        nlMap.put("Randy King", "Comfortably Numb");
        nlMap.put("Grant Williams", "Los Chupanibres");
        nlMap.put("Eric Field", "Oddfellows Local 151");
        nlMap.put("Mike Fiske", "Army of Champions");
        nlMap.put("Dave Catanzano", "Run T.M.C.");
        nlMap.put("Ron Dollete", "Hungry Cats");

        nlMap.forEach((owner, name) -> {
            Roster existingRoster = rosterRepository.findFirstByOwnerAndName(owner, name);
            if (null == existingRoster) {
                Roster roster = Roster.builder().owner(owner).name(name).league("nl").build();
                rosterRepository.save(roster);
            }
        });
    }

    @Test
    @Disabled
    public void nlRocca() {
        saveToRoster(new Object[][] {
                {"Ketel Marte", DIAMONDBACKS},
                {"Marcell Ozuna", CARDINALS},
                {"Cody Bellinger", DODGERS},
                {"Kurt Suzuki", NATIONALS},
                {"Yasmani Grandal", BREWERS},
                {"Brandon Belt", GIANTS},
                {"Adam Frazier", PIRATES},
                {"Isan Diaz", MARLINS},
                {"Cristian Pache", BRAVES}
        }, new Object[][] {
                {"Pedro Strop", CUBS},
                {"Seranthony Dominguez", PHILLIES},
                {"Arodys Vizcaino", BRAVES},
                {"German Marquez", ROCKIES},
                {"Trevor Richards", MARLINS},
                {"Reyes Moronta", GIANTS},
                {"Mitch Keller", PIRATES}
        }, "Matt Rocca", "nl");
    }

    @Test
    @Disabled
    public void nlPimentel() {
        saveToRoster(new Object[][] {
                {"Eric Hosmer", PADRES},
                {"Wilmer Difo", NATIONALS},
                {"David Freese", DODGERS},
                {"Matt Kemp", REDS},
                {"Chris Iannetta", ROCKIES},
                {"Tyler Flowers", BRAVES},
                {"Matt Adams", NATIONALS},
                {"Jeff McNeil", METS}
        }, new Object[][] {
                {"Trevor Williams", PIRATES},
                {"Brandon Morrow", CUBS},
                {"Jeremy Hellickson", NATIONALS},
                {"Brock Stewart", DODGERS}
        }, "Chris Pimentel", "nl");
    }

    @Test
    @Disabled
    public void nlSoup() {
        saveToRoster(new Object[][] {
                {"Kris Bryant", CUBS},
                {"Juan Soto", NATIONALS},
                {"Ronald Acuna Jr.", BRAVES},
                {"Starling Marte", PIRATES},
                {"Victor Robles", NATIONALS},
                {"Wilson Ramos", METS},
                {"Willson Contreras", CUBS},
                {"Taylor Trammell", REDS}
        }, new Object[][] {
                {"Freddy Peralta", BREWERS},
                {"Sean Doolittle", NATIONALS},
                {"Julio Urias", DODGERS},
                {"Walker Buehler", DODGERS},
                {"Noah Syndergaard", METS}
        }, "Neal Campbell", "nl");
    }

    @Test
    @Disabled
    public void nlJon() {
        saveToRoster(new Object[][] {
                {"Brian Dozier", NATIONALS},
                {"J.T. Riddle", MARLINS},
                {"Jesse Winker", REDS},
                {"Nick Williams", PHILLIES},
                {"Nick Markakis", BRAVES},
                {"Scott Schebler", REDS},
                {"Francisco Cervelli", PIRATES},
                {"Keston Hiura", BREWERS},
                {"Roman Quinn", PHILLIES}
        }, new Object[][] {
                {"Anibal Sanchez", NATIONALS},
                {"Miles Mikolas", CARDINALS},
                {"Luke Weaver", DIAMONDBACKS},
                {"Archie Bradley", DIAMONDBACKS},
                {"Joe Musgrove", PIRATES},
                {"Zach Eflin", PHILLIES},
                {"Luiz Gohara", BRAVES}
        }, "Jon Chatalian", "nl");
    }

    @Test
    @Disabled
    public void nlFez() {
        saveToRoster(new Object[][] {
                {"Ozzie Albies", BRAVES},
                {"Maikel Franco", PHILLIES},
                {"Franmil Reyes", PADRES},
                {"Lorenzo Cain", BREWERS},
                {"Joc Pederson", DODGERS},
                {"Kolten Wong", CARDINALS},
                {"Lucas Erceg", BREWERS}
        }, new Object[][] {
                {"Kenley Jansen", DODGERS},
                {"Sean Newcomb", BRAVES},
                {"Jose Urena", MARLINS},
                {"Patrick Corbin", NATIONALS},
                {"Dereck Rodriguez", GIANTS},
                {"Zack Godley", DIAMONDBACKS}
        }, "Craig Fez", "nl");
    }

    @Test
    @Disabled
    public void nlMattyJ() {
        saveToRoster(new Object[][] {
                {"Cesar Hernandez", PHILLIES},
                {"Albert Almora Jr.", CUBS},
                {"Alex Verdugo", DODGERS},
                {"Christian Yelich", BREWERS},
                {"Brendan Rodgers", ROCKIES},
                {"Keibert Ruiz", DODGERS}
        }, new Object[][] {
                {"Felipe Vazquez", PIRATES},
                {"Ross Stripling", DODGERS},
                {"Drew Steckenrider", MARLINS},
                {"Aaron Nola", PHILLIES},
                {"Tyler Anderson", ROCKIES}
                // {"Hunter Greene", REDS}
        }, "Matt Johnson", "nl");
    }

    @Test
    @Disabled
    public void nlRandy() {
        saveToRoster(new Object[][] {
                {"Hunter Renfroe", PADRES},
                {"Austin Barnes", DODGERS},
                {"Corey Ray", BREWERS}
        }, new Object[][] {
                {"Wade Davis", ROCKIES},
                {"Max Fried", BRAVES},
                {"Steven Matz", METS},
                {"Julio Teheran", BRAVES},
                {"Zack Wheeler", METS},
                {"Tyler Beede", GIANTS}
        }, "Randy King", "nl");
    }

    @Test
    @Disabled
    public void nlGrant() {
        saveToRoster(new Object[][] {
                {"Max Muncy", DODGERS},
                {"Daniel Murphy", ROCKIES},
                {"Paul DeJong", CARDINALS},
                {"Johan Camargo", BRAVES},
                {"Rhys Hoskins", PHILLIES},
                {"Michael A. Taylor", NATIONALS},
                {"Yasiel Puig", REDS},
                {"Andrew Knapp", PHILLIES},
                {"Tucker Barnhart", REDS},
                {"Carter Kieboom", NATIONALS}
        }, new Object[][] {
                {"Josh Hader", BREWERS},
                {"Jack Flaherty", CARDINALS},
                {"Matt Strahm", PADRES}
        }, "Grant Williams", "nl");
    }

    @Test
    @Disabled
    public void nlField() {
        saveToRoster(new Object[][] {
                {"Matt Carpenter", CARDINALS},
                {"Corey Seager", DODGERS},
                {"Travis Shaw", BREWERS},
                {"Enrique Hernandez", DODGERS},
                {"Eugenio Suarez", REDS},
                {"Luis Urias", PADRES},
                {"Nick Senzel", REDS}
        }, new Object[][] {
                {"Jhoulys Chacin", BREWERS},
                {"Jimmy Nelson", BREWERS},
                {"Madison Bumgarner", GIANTS},
                {"Kyle Wright", BRAVES},
                {"Jon Lester", CUBS}
//                {"MacKenzie Gore", PADRES}
        }, "Eric Field", "nl");
    }

    @Test
    @Disabled
    public void nlFiske() {
        saveToRoster(new Object[][] {
                {"Javier Baez", CUBS},
                {"Trea Turner", NATIONALS},
                {"Anthony Rendon", NATIONALS},
                {"David Peralta", DIAMONDBACKS},
                {"Ben Zobrist", CUBS},
                {"Joe Panik", GIANTS}
        }, new Object[][] {
                {"Seth Lugo", METS},
                {"Rich Hill", DODGERS},
                {"Hyun-Jin Ryu", DODGERS},
                {"Kevin Gausman", BRAVES},
                {"Joey Lucchesi", PADRES},
                {"Max Scherzer", NATIONALS},
                {"Derek Holland", GIANTS}
        }, "Mike Fiske", "nl");
    }

    @Test
    @Disabled
    public void nlCatanzano() {
        saveToRoster(new Object[][] {
                {"Josh Bell", PIRATES},
                {"Nick Ahmed", DIAMONDBACKS},
                {"Harrison Bader", CARDINALS},
                {"Manuel Margot", PADRES},
                {"Michael Conforto", METS},
                {"Brandon Nimmo", METS},
                {"Jorge Alfaro", MARLINS},
                {"Jesus Aguilar", BREWERS},
                {"Jeren Kendall", DODGERS}
        }, new Object[][] {
                {"Jacob Barnes", BREWERS},
                {"Nick Pivetta", PHILLIES},
                {"Kirby Yates", PADRES},
                {"Vince Velasquez", PHILLIES}
                // {"Braxton Garrett", MARLINS}
        }, "Dave Catanzano", "nl");
    }

    @Test
    @Disabled
    public void nlRon() {
        saveToRoster(new Object[][] {
                {"Wilmer Flores", DIAMONDBACKS},
                {"Amed Rosario", METS},
                {"Nolan Arenado", ROCKIES},
                {"Jose Martinez", CARDINALS},
                {"Raimel Tapia", ROCKIES},
                {"Chris Taylor", DODGERS},
                {"David Dahl", ROCKIES},
                {"Dominic Smith", METS},
                {"Charlie Culberson", BRAVES}
                // {"Heliot Ramos", GIANTS}
        }, new Object[][] {
                {"Jordan Hicks", CARDINALS},
                {"Eric Lauer", PADRES},
                {"Carl Edwards Jr.", CUBS},
                {"Kyle Freeland", ROCKIES},
                {"Luis Castillo", REDS},
                {"Alex Wood", REDS},
                {"Cal Quantrill", PADRES},
                {"Yadier Alvarez", DODGERS}
        }, "Ron Dollete", "nl");
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
