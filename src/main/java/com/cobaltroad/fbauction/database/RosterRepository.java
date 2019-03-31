package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.model.Roster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RosterRepository extends JpaRepository<Roster, Integer> {

    Roster findFirstByOwnerAndName(String owner, String name);
}
