package com.cobaltroad.fbauction.controller;

import com.cobaltroad.fbauction.model.QueryResponse;
import com.cobaltroad.fbauction.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("players")
public class PlayerController {

    @Autowired
    PlayerService service;

    @GetMapping(value = "/{league}", produces = "application/json")
    public QueryResponse index(@PathVariable String league, @RequestParam("name") Optional<String> name) {
        return service.index(league, name);
    }

    @GetMapping(value = "/available/{league}", produces = "application/json")
    @Transactional(readOnly = true)
    public QueryResponse available(@PathVariable String league, @RequestParam("pos") Optional<String> pos) {
        return service.available(league, pos);
    }
}
