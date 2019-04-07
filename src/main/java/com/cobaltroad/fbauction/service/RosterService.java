package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.controller.RosterResponse;
import com.cobaltroad.fbauction.database.PlayerRepository;
import com.cobaltroad.fbauction.database.RosterRepository;
import com.cobaltroad.fbauction.model.Player;
import com.cobaltroad.fbauction.model.Roster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RosterService {
    @Autowired
    RosterRepository repository;

    @Autowired
    PlayerRepository playerRepository;

    public RosterResponse view(String league, Optional<String> owner) {
        List<Roster> rosters;
        if (owner.isPresent()) {
            rosters = repository.findByLeagueAndOwnerContainsIgnoreCase(league, owner.get());
        } else {
            rosters = repository.findByLeague(league);
        }
        return new RosterResponse(rosters);
    }

    public RosterResponse add(int rosterId, int playerId) {
        List<Roster> rosterList = new ArrayList<>();
        Optional<Roster> foundRoster = repository.findById(rosterId);
        if (foundRoster.isPresent()) {
            Roster roster = foundRoster.get();
            Optional<Player> foundPlayer = playerRepository.findById(playerId);

            if (foundPlayer.isPresent()) {
                Player player = foundPlayer.get();

                if (player.getRoster() == null) {
                    roster.addPlayer(player);
                    rosterList.add(roster);
                } else {
                    throw new RuntimeException("Player " + playerId + " (" + player.getName() + ") is already on a roster owned by " + player.getRoster().getOwner());
                }
            } else {
                throw new RuntimeException("Player  " + playerId + " is not found");
            }
        } else {
            throw new RuntimeException("Roster  " + rosterId + " is not found");
        }

        return new RosterResponse(rosterList);
    }

    public RosterResponse drop(Integer rosterId, int playerId) {
        List<Roster> rosterList = new ArrayList<>();
        Optional<Roster> foundRoster = repository.findById(rosterId);
        if (foundRoster.isPresent()) {
            Roster roster = foundRoster.get();
            Optional<Player> foundPlayer = playerRepository.findById(playerId);

            if (foundPlayer.isPresent()) {
                Player player = foundPlayer.get();

                if (player.getRoster() != null) {
                    roster.dropPlayer(player);
                    rosterList.add(roster);
                } else {
                    throw new RuntimeException("Player " + playerId + " (" + player.getName() + ") is not on a roster");
                }

            } else {
                throw new RuntimeException("Player  " + playerId + " is not found");
            }
        } else {
            throw new RuntimeException("Roster  " + rosterId + " is not found");
        }

        return new RosterResponse(rosterList);
    }
}
