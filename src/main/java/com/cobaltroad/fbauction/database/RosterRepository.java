package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.model.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RosterRepository extends JpaRepository<Roster, Integer> {

    Roster findFirstByOwnerAndName(String owner, String name);
    Roster findFirstByOwnerAndLeague(String owner, String league);

    List<Roster> findByLeagueAndOwnerContainsIgnoreCase(String league, String partialOwner);
    List<Roster> findByLeague(String league);
}
