package com.cobaltroad.fbauction.listener;

import com.cobaltroad.fbauction.database.HitterProjectionRepository;
import com.cobaltroad.fbauction.database.LeagueStatRepository;
import com.cobaltroad.fbauction.model.HitterProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
public class HitterProjectionListener {

    @PersistenceContext
    private EntityManager em;

    static private LeagueStatRepository leagueStatRepository;
    static private HitterProjectionRepository hitterProjectionRepository;

    @Autowired
    @Qualifier("hitterProjectionRepository")
    public void setHitterProjectionRepository(HitterProjectionRepository hitterProjectionRepository) {
        this.hitterProjectionRepository = hitterProjectionRepository;
    }

    @Autowired
    @Qualifier("leagueStatRepository")
    public void setLeagueStatRepository(LeagueStatRepository leagueStatRepository) {
        this.leagueStatRepository = leagueStatRepository;
    }

    @PostPersist
    public void preHitterProjectionPersist(HitterProjection projection) {
        System.out.println("hits -- " + projection.getHits());

        String league = projection.getLeague();
        Query query = em.createNativeQuery("SELECT AVG(CAST(hits AS FLOAT)) FROM hitter_projection WHERE league = " + league);
        double avgHits = (double) query.getSingleResult();
        saveAsLeagueStat(league + ":average:hits", avgHits);

//        double stddevHits = null == hitterProjectionRepository.stddevOfAllHits(league) ? 0.0 : hitterProjectionRepository.stddevOfAllHits(league);
//        saveAsLeagueStat(league + ":stddev:hits", stddevHits);
    }

    private void saveAsLeagueStat(String key, double value) {
        LeagueStat ls = leagueStatRepository.findFirstByKey(key);
        System.out.println(key + " -- " + value);
        if (null == ls) {
            leagueStatRepository.save(new LeagueStat(key, value));
        } else {
            ls.setValue(value);
        }
    }
}
