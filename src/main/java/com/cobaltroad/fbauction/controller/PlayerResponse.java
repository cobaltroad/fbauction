package com.cobaltroad.fbauction.controller;

import com.cobaltroad.fbauction.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class PlayerResponse {
    List<PresentedPlayer> players;

    public PlayerResponse(List<Player> playerList) {
        List<PresentedPlayer> presentedPlayerList = new ArrayList<>();
        playerList.forEach(player -> {
            String positions;
            Projection projection = new Projection();
            Double totalRating;
            if (player instanceof Hitter) {
                Hitter h = (Hitter) player;
                positions = h.positionsString();

                HitterProjection hp = h.getProjection();
                projection.setBattingAverage(hp.getBattingAverageRating());
                projection.setRuns(hp.getRunsRating());
                projection.setRunsBattedIn(hp.getRbiRating());
                projection.setHomeruns(hp.getHomerunRating());
                projection.setStolenBases(hp.getStolenBaseRating());
                totalRating = hp.getBattingAverageRating() +
                              hp.getRunsRating() +
                              hp.getRbiRating() +
                              hp.getHomerunRating() +
                              hp.getStolenBaseRating();
            } else {
                Pitcher p = (Pitcher) player;
                positions = "PITCHER";

                PitcherProjection pp = p.getProjection();
                projection.setEarnedRunAverage(pp.getEarnedRunAverageRating());
                projection.setWins(pp.getWinsRating());
                projection.setStrikeouts(pp.getStrikeoutsRating());
                projection.setWhip(pp.getWhipRating());
                projection.setSaves(pp.getSavesRating());
                totalRating = pp.getEarnedRunAverageRating() +
                              pp.getWinsRating() +
                              pp.getStrikeoutsRating() +
                              pp.getWhipRating() +
                              pp.getSavesRating();
            }
            String team = player.getTeam() == null ? "" : player.getTeam().toString();
            String owner = player.getRoster() == null ? "" : player.getRoster().getOwner();
            PresentedPlayer presentedPlayer = new PresentedPlayer(
                    player.getName(),
                    team,
                    positions,
                    totalRating,
                    owner,
                    projection
            );
            presentedPlayerList.add(presentedPlayer);
        });
        this.players = presentedPlayerList;
    }

    @AllArgsConstructor
    @Data
    private class PresentedPlayer {
        private String name;
        private String team;
        private String positions;
        private Double totalRating;
        private String owner;
        private Projection projection;
    }

    @Setter
    @NoArgsConstructor
    @Data
    private class Projection {
        private double battingAverage;
        private double runs;
        private double runsBattedIn;
        private double homeruns;
        private double stolenBases;

        private double earnedRunAverage;
        private double wins;
        private double strikeouts;
        private double whip;
        private double saves;
    }
}
