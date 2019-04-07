package com.cobaltroad.fbauction.service;

import com.cobaltroad.fbauction.controller.RosterResponse;
import com.cobaltroad.fbauction.database.RosterRepository;
import com.cobaltroad.fbauction.model.Roster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RosterService {
    @Autowired
    RosterRepository repository;

    public RosterResponse view(String league, Optional<String> owner) {
        List<Roster> rosters;
        if (owner.isPresent()) {
            rosters = repository.findByLeagueAndOwnerContainsIgnoreCase(league, owner.get());
        } else {
            rosters = repository.findByLeague(league);
        }
        return new RosterResponse(rosters);
    }
}
