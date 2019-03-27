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

    public void aggregateRuns(String league) {
        double mu = hitterProjectionRepository.averageOfAllRuns(league);
        double sigma = hitterProjectionRepository.stddevOfAllRuns(league);

        saveLeagueStat(league + ":average:runs", mu);
        saveLeagueStat(league + ":stddev:runs", sigma);
    }

    public void aggregateRBIs(String league) {
        double mu = hitterProjectionRepository.averageOfAllRBIs(league);
        double sigma = hitterProjectionRepository.stddevOfAllRBIs(league);

        saveLeagueStat(league + ":average:rbi", mu);
        saveLeagueStat(league + ":stddev:rbi", sigma);
    }

    public void aggregateHRs(String league) {
        double mu = hitterProjectionRepository.averageOfAllHRs(league);
        double sigma = hitterProjectionRepository.stddevOfAllHRs(league);

        saveLeagueStat(league + ":average:hr", mu);
        saveLeagueStat(league + ":stddev:hr", sigma);
    }

    public void aggregateSBs(String league) {
        double mu = hitterProjectionRepository.averageOfAllSBs(league);
        double sigma = hitterProjectionRepository.stddevOfAllSBs(league);

        saveLeagueStat(league + ":average:sb", mu);
        saveLeagueStat(league + ":stddev:sb", sigma);
    }

    public void updateRatings() {
        for (HitterProjection projection : hitterProjectionRepository.findAll()) {
            String league = projection.getLeague();

            double hitsRating = ratingFor(league, "hits", projection.getHits());
            double atBatsRating = ratingFor(league, "atbats", projection.getAtBats());
            projection.setBattingAverageRating(hitsRating - atBatsRating);
            projection.setRunsRating(ratingFor(league, "runs", projection.getRuns()));
            projection.setRbiRating(ratingFor(league, "rbi", projection.getRunsBattedIn()));
            projection.setHomerunRating(ratingFor(league, "hr", projection.getHomeruns()));
            projection.setStolenBaseRating(ratingFor(league, "sb", projection.getStolenBases()));

            hitterProjectionRepository.save(projection);
        }
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

    private double getLeagueStat(String key) {
        LeagueStat ls = leagueStatRepository.findFirstByKey(key);
        return null == ls ? 0.0 : ls.getValue();
    }

    private double ratingFor(String league, String statKey, int projectedStat) {
        double mu = getLeagueStat(league + ":average:" + statKey);
        double sigma = getLeagueStat(league + ":stddev:" + statKey);

        return (projectedStat - mu) / sigma;
    }
}
