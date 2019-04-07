package com.cobaltroad.fbauction.controller;

import com.cobaltroad.fbauction.service.RosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rosters")
public class RosterController {
    @Autowired
    RosterService service;

    @GetMapping(value = "/{league}", produces = "application/json")
    public RosterResponse view(@PathVariable String league, @RequestParam("owner") Optional<String> owner) {
        return service.view(league, owner);
    }

    @PostMapping(value = "/add/{playerId}/to/{rosterId}", produces = "application/json")
    public RosterResponse add(@PathVariable String playerId, @PathVariable String rosterId) {
        return service.add(Integer.valueOf(rosterId), Integer.valueOf(playerId));
    }
}
