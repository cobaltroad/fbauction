package com.cobaltroad.fbauction.controller;

import com.cobaltroad.fbauction.model.Hitter;
import com.cobaltroad.fbauction.model.Roster;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class RosterResponse {
    List<PresentedRoster> rosters;

    public RosterResponse(List<Roster> rosterList) {
        List<PresentedRoster> presentedRosterList = new ArrayList<>();

        rosterList.forEach(roster -> {
            PresentedRoster presentedRoster = new PresentedRoster();
            presentedRoster.setRosterId(roster.getId());
            presentedRoster.setOwner(roster.getOwner());
            presentedRoster.setLeague(roster.getLeague());

            List<PresentedPlayer> presentedPlayerList = new ArrayList<>();
            roster.getPlayers().forEach(player -> {
                PresentedPlayer presentedPlayer = new PresentedPlayer();
                presentedPlayer.setName(player.getName());
                String team = player.getTeam() == null ? "" : player.getTeam().toString();
                presentedPlayer.setTeam(team);
                String positions = player instanceof Hitter ? ((Hitter) player).positionsString() : "PITCHER";
                presentedPlayer.setPositions(positions);

                presentedPlayerList.add(presentedPlayer);
            });
            presentedRoster.setPlayers(presentedPlayerList);

            presentedRosterList.add(presentedRoster);
        });

        this.rosters = presentedRosterList;
    }

    @Data
    @NoArgsConstructor
    @Setter
    private class PresentedRoster {
        private int rosterId;
        private String owner;
        private String league;

        private List<PresentedPlayer> players;
    }

    @Data
    @NoArgsConstructor
    @Setter
    private class PresentedPlayer {
        private String name;
        private String team;
        private String positions;
    }
}
