package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.enumeration.Team;
import com.cobaltroad.fbauction.model.Player;
import com.cobaltroad.fbauction.model.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Stream;

import static com.cobaltroad.fbauction.enumeration.Team.alTeams;
import static com.cobaltroad.fbauction.enumeration.Team.nlTeams;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    List<Player> findByTeam(Team team);
    List<Player> findByTeamIn(EnumSet<Team> teams);

    List<Player> findByFirstNameAndLastNameAndTeam(String firstName, String lastName, Team team);

    default List<Player> findByNationalLeague() {
        return findByTeamIn(nlTeams);
    }

    default List<Player> findByAmericanLeague() {
        return findByTeamIn(alTeams);
    }

    @Query(value = "SELECT * FROM player WHERE roster_id IS NULL AND (LOWER(first_name) LIKE :name OR LOWER(last_name) like :name)", nativeQuery = true)
    List<Player> findAvailablePlayerByName(@Param("name") String name);

    default List<Player> findByLeague(String league) {
        if (league.toLowerCase().equals("nl")) {
            return findByTeamIn(nlTeams);
        } else if (league.toLowerCase().equals("al")) {
            return findByTeamIn(alTeams);
        } else return Collections.emptyList();
    }

    default List<Player> findByLeagueAndName(String league, String name) {
        if (league.toLowerCase().equals("nl")) {
            return findByTeamInAndFirstNameContainsIgnoreCaseOrTeamInAndLastNameContainsIgnoreCase(nlTeams, name, nlTeams, name);
        } else if (league.toLowerCase().equals("al")) {
            return findByTeamInAndFirstNameContainsIgnoreCaseOrTeamInAndLastNameContainsIgnoreCase(alTeams, name, alTeams, name);
        } else
            return Collections.emptyList();
    }

    List<Player> findByTeamInAndFirstNameContainsIgnoreCaseOrTeamInAndLastNameContainsIgnoreCase(EnumSet<Team> firstTeams, String partialFirstName, EnumSet<Team> lastTeams, String partialLastName);

    default List<Player> findByName(String partialFirstName, String partialLastName) {
        return findByFirstNameContainingOrLastNameContainingAllIgnoreCase(partialFirstName, partialLastName);
    }

    List<Player> findByFirstNameContainingOrLastNameContainingAllIgnoreCase(String partialFirstName, String partialLastName);

    Stream<Player> findByRosterAndTeamIn(Roster roster, EnumSet<Team> teams);
}
