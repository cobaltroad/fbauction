package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.HitterProjectionRepository;
import com.cobaltroad.fbauction.database.LeagueStatRepository;
import com.cobaltroad.fbauction.model.HitterProjection;
import com.cobaltroad.fbauction.model.LeagueStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueStatService {

    @Autowired
    HitterProjectionRepository hitterProjectionRepository;

    @Autowired
    LeagueStatRepository leagueStatRepository;

    public void aggregateHits(String league) {
        double mu = hitterProjectionRepository.averageOfAllHits(league);
        double sigma = hitterProjectionRepository.stddevOfAllHits(league);

        saveLeagueStat(league + ":average:hits", mu);
        saveLeagueStat(league + ":stddev:hits", sigma);
    }

    public void aggregateAtBats(String league) {
        double mu = hitterProjectionRepository.averageOfAllABs(league);
        double sigma = hitterProjectionRepository.stddevOfAllABs(league);

        saveLeagueStat(league + ":average:atbats", mu);
        saveLeagueStat(league + ":stddev:atbats", sigma);
    }

    public double battingAverageRatingFor(HitterProjection projection) {
        String league = projection.getLeague();
        double muHits = hitterProjectionRepository.averageOfAllHits(league);
        double sigmaHits = hitterProjectionRepository.stddevOfAllHits(league);

        int projectionHits = projection.getHits();
        double devsFromMeanHits = (projectionHits - muHits) / sigmaHits;
        System.out.println("Deviations From Mean (Hits): " + devsFromMeanHits);

        double muAtBats = hitterProjectionRepository.averageOfAllABs(league);
        double sigmaAtBats = hitterProjectionRepository.stddevOfAllABs(league);

        int projectionAtBats = projection.getAtBats();
        double devsFromMeanAtBats = (projectionAtBats - muAtBats) / sigmaAtBats;
        System.out.println("Deviations From Mean (ABs): " + devsFromMeanAtBats);

        return devsFromMeanHits - devsFromMeanAtBats;
    }

    private void saveLeagueStat(String key, double value) {
        LeagueStat ls = leagueStatRepository.findFirstByKey(key);
        if (null == ls) {
            leagueStatRepository.save(new LeagueStat(key, value));
        } else {
            ls.setValue(value);
            leagueStatRepository.save(ls);
        }
    }

}
