package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.model.LeagueStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueStatRepository extends JpaRepository<LeagueStat, Integer> {

    LeagueStat findFirstByKey(String key);

    default double valueOf(String key) {
        LeagueStat ls = findFirstByKey(key);
        return ls.getValue();
    }
}
