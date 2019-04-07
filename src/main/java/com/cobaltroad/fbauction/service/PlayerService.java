package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.database.PlayerRepository;
import com.cobaltroad.fbauction.model.Player;
import com.cobaltroad.fbauction.controller.PlayerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.cobaltroad.fbauction.enumeration.Team.alTeams;
import static com.cobaltroad.fbauction.enumeration.Team.nlTeams;
import static com.cobaltroad.fbauction.model.Player.byTotalRating;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository repository;

    public PlayerResponse index(String league, Optional<String> name) {
        List<Player> players;
        if (name.isPresent()) {
            players = repository.findByLeagueAndName(league, name.get());
        } else {
            players = repository.findByLeague(league);
        }
        players.sort(byTotalRating);
        return new PlayerResponse(players);
    }

    public PlayerResponse available(String league, Optional<String> pos) {
        List<Player> players;

        try (Stream<Player> stream = availableStreamByLeague(league)) {
            if (pos.isPresent()) {
                players = stream.filter(player -> player.isEligibleAt(pos.get())).collect(Collectors.toList());
            } else {
                players = stream.collect(Collectors.toList());
            }
        }
        players.sort(byTotalRating);
        return new PlayerResponse(players);
    }

    private Stream<Player> availableStreamByLeague(String league) {
        if (league.equalsIgnoreCase("nl")) {
            return repository.findByRosterAndTeamIn(null, nlTeams);
        } else if (league.equalsIgnoreCase("al")) {
            return repository.findByRosterAndTeamIn(null, alTeams);
        } else
            return Stream.empty();
    }
}
