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
        double muHits = getLeagueStat(league + ":average:hits");
        double sigmaHits = getLeagueStat(league + ":stddev:hits");

        int projectionHits = projection.getHits();
        double devsFromMeanHits = (projectionHits - muHits) / sigmaHits;

        double muAtBats = getLeagueStat(league + ":average:atbats");
        double sigmaAtBats = getLeagueStat(league + ":stddev:atbats");

        int projectionAtBats = projection.getAtBats();
        double devsFromMeanAtBats = (projectionAtBats - muAtBats) / sigmaAtBats;

        return devsFromMeanHits - devsFromMeanAtBats;
    }

    public void aggregateRuns(String league) {
        double mu = hitterProjectionRepository.averageOfAllRuns(league);
        double sigma = hitterProjectionRepository.stddevOfAllRuns(league);

        saveLeagueStat(league + ":average:runs", mu);
        saveLeagueStat(league + ":stddev:runs", sigma);
    }

    public double runsRatingFor(HitterProjection projection) {
        String league = projection.getLeague();
        double mu = getLeagueStat(league + ":average:runs");
        double sigma = getLeagueStat(league + ":stddev:runs");

        int projectedStat = projection.getRuns();
        return (projectedStat - mu) / sigma;
    }

    public void aggregateRBIs(String league) {
        double mu = hitterProjectionRepository.averageOfAllRBIs(league);
        double sigma = hitterProjectionRepository.stddevOfAllRBIs(league);

        saveLeagueStat(league + ":average:rbi", mu);
        saveLeagueStat(league + ":stddev:rbi", sigma);
    }

    public double rbiRatingFor(HitterProjection projection) {
        String league = projection.getLeague();
        double mu = getLeagueStat(league + ":average:rbi");
        double sigma = getLeagueStat(league + ":stddev:rbi");

        int projectedStat = projection.getRunsBattedIn();
        return (projectedStat - mu) / sigma;
    }

    public void aggregateHRs(String league) {
        double mu = hitterProjectionRepository.averageOfAllHRs(league);
        double sigma = hitterProjectionRepository.stddevOfAllHRs(league);

        saveLeagueStat(league + ":average:hr", mu);
        saveLeagueStat(league + ":stddev:hr", sigma);
    }

    public double homerunRatingFor(HitterProjection projection) {
        String league = projection.getLeague();
        double mu = getLeagueStat(league + ":average:hr");
        double sigma = getLeagueStat(league + ":stddev:hr");

        int projectedStat = projection.getHomeruns();
        return (projectedStat - mu) / sigma;
    }

    public void aggregateSBs(String league) {
        double mu = hitterProjectionRepository.averageOfAllSBs(league);
        double sigma = hitterProjectionRepository.stddevOfAllSBs(league);

        saveLeagueStat(league + ":average:sb", mu);
        saveLeagueStat(league + ":stddev:sb", sigma);
    }

    public double stolenBaseRatingFor(HitterProjection projection) {
        String league = projection.getLeague();
        double mu = getLeagueStat(league + ":average:sb");
        double sigma = getLeagueStat(league + ":stddev:sb");

        int projectedStat = projection.getStolenBases();
        return (projectedStat - mu) / sigma;
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
        return ls.getValue();
    }

}
