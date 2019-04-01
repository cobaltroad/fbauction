package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.HitterProjectionRepository;
import com.cobaltroad.fbauction.database.LeagueStatRepository;
import com.cobaltroad.fbauction.database.PitcherProjectionRepository;
import com.cobaltroad.fbauction.model.HitterProjection;
import com.cobaltroad.fbauction.model.LeagueStat;
import com.cobaltroad.fbauction.model.PitcherProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LeagueStatService {

    @Autowired
    HitterProjectionRepository hitterProjectionRepository;

    @Autowired
    PitcherProjectionRepository pitcherProjectionRepository;

    @Autowired
    LeagueStatRepository leagueStatRepository;

    Map<String, Double> leagueStatCache = new HashMap<>();

    public void aggregateStatsAndRatings() {
        aggregateStats("al");
        aggregateStats("nl");
        aggregateStats("fa");
        leagueStatCache = new HashMap<>();
        updateRatings();
    }

    private void aggregateStats(String league) {
        aggregateHits(league);
        aggregateAtBats(league);
        aggregateRuns(league);
        aggregateRBIs(league);
        aggregateHRs(league);
        aggregateSBs(league);

        aggregateEarnedRuns(league);
        aggregateInningsPitched(league);
        aggregateWins(league);
        aggregateStrikeouts(league);
        aggregateWalks(league);
        aggregateHitsAllowed(league);
        aggregateSaves(league);
    }

    public void aggregateEarnedRuns(String league) {
        double mu = pitcherProjectionRepository.averageOfAllERs(league);
        double sigma = pitcherProjectionRepository.stddevOfAllERs(league);

        saveLeagueStat(league + ":average:er", mu);
        saveLeagueStat(league + ":stddev:er", sigma);
    }

    public void aggregateInningsPitched(String league) {
        double mu = pitcherProjectionRepository.averageOfAllIPs(league);
        double sigma = pitcherProjectionRepository.stddevOfAllIPs(league);

        saveLeagueStat(league + ":average:ip", mu);
        saveLeagueStat(league + ":stddev:ip", sigma);
    }

    public void aggregateWins(String league) {
        double mu = pitcherProjectionRepository.averageOfAllWins(league);
        double sigma = pitcherProjectionRepository.stddevOfAllWins(league);

        saveLeagueStat(league + ":average:wins", mu);
        saveLeagueStat(league + ":stddev:wins", sigma);
    }

    public void aggregateStrikeouts(String league) {
        double mu = pitcherProjectionRepository.averageOfAllKs(league);
        double sigma = pitcherProjectionRepository.stddevOfAllKs(league);

        saveLeagueStat(league + ":average:k", mu);
        saveLeagueStat(league + ":stddev:k", sigma);
    }

    public void aggregateWalks(String league) {
        double mu = pitcherProjectionRepository.averageOfAllWalks(league);
        double sigma = pitcherProjectionRepository.stddevOfAllWalks(league);

        saveLeagueStat(league + ":average:walks", mu);
        saveLeagueStat(league + ":stddev:walks", sigma);
    }

    public void aggregateHitsAllowed(String league) {
        double mu = pitcherProjectionRepository.averageOfAllHits(league);
        double sigma = pitcherProjectionRepository.stddevOfAllHits(league);

        saveLeagueStat(league + ":average:hitsallowed", mu);
        saveLeagueStat(league + ":stddev:hitsallowed", sigma);
    }

    public void aggregateSaves(String league) {
        double mu = pitcherProjectionRepository.averageOfAllSaves(league);
        double sigma = pitcherProjectionRepository.stddevOfAllSaves(league);

        saveLeagueStat(league + ":average:saves", mu);
        saveLeagueStat(league + ":stddev:saves", sigma);
    }

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

            double totalRating = projection.getBattingAverageRating() +
                                 projection.getRunsRating() +
                                 projection.getRbiRating() +
                                 projection.getHomerunRating() +
                                 projection.getStolenBaseRating();
            projection.setTotalRating(totalRating);

//            hitterProjectionRepository.save(projection);
        }

        for (PitcherProjection projection : pitcherProjectionRepository.findAll()) {
            String league = projection.getLeague();

            double earnedRunsRating = ratingFor(league, "er", projection.getEarnedRuns());
            double inningsPitchedRating = ratingFor(league, "ip", (int) Math.round(projection.getInningsPitched()));
            projection.setEarnedRunAverageRating(inningsPitchedRating - earnedRunsRating);
            projection.setWinsRating(ratingFor(league, "wins", projection.getWins()));
            projection.setStrikeoutsRating(ratingFor(league, "k", projection.getStrikeouts()));
            double walksRating = ratingFor(league, "walks", projection.getWalks());
            double hitsAllowedRating = ratingFor(league, "hitsallowed", projection.getHits());
            projection.setWhipRating(inningsPitchedRating - walksRating - hitsAllowedRating);
            projection.setSavesRating(ratingFor(league, "saves", projection.getSaves()));

            double totalRating = projection.getEarnedRunAverageRating() +
                                 projection.getWinsRating() +
                                 projection.getStrikeoutsRating() +
                                 projection.getWhipRating() +
                                 projection.getSavesRating();
            projection.setTotalRating(totalRating);

            pitcherProjectionRepository.save(projection);
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
        double cachedLeagueStat;
        if (null == leagueStatCache.get(key)) {
            LeagueStat ls = leagueStatRepository.findFirstByKey(key);
            cachedLeagueStat = null == ls ? 0.0 : ls.getValue();
            leagueStatCache.put(key, cachedLeagueStat);
        } else {
            cachedLeagueStat = leagueStatCache.get(key);
        }
        return cachedLeagueStat;
    }

    private double ratingFor(String league, String statKey, int projectedStat) {
        double mu = getLeagueStat(league + ":average:" + statKey);
        double sigma = getLeagueStat(league + ":stddev:" + statKey);

        double rating = (projectedStat - mu) / sigma;
        return (Double.isNaN(rating)) ? 0.0 : rating;
    }

}
