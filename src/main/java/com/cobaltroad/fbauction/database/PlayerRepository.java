package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.enumeration.Team;
import com.cobaltroad.fbauction.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.EnumSet;
import java.util.List;

import static com.cobaltroad.fbauction.enumeration.Team.alTeams;
import static com.cobaltroad.fbauction.enumeration.Team.nlTeams;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByFirstNameAndLastName(String firstName, String lastName);
    List<Player> findByTeam(Team team);
    List<Player> findByTeamIn(EnumSet<Team> teams);

    default Player findByName(String firstName, String lastName) {
        return findByFirstNameAndLastName(firstName, lastName).get(0);
    }

    default List<Player> findByNationalLeague() {
        return findByTeamIn(nlTeams);
    }

    default List<Player> findByAmericanLeague() {
        return findByTeamIn(alTeams);
    }
}
