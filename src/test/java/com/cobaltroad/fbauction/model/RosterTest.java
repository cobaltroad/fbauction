package com.cobaltroad.fbauction.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RosterTest {

    @Test
    public void rosterAddsPlayers() {
        Hitter hitter = new Hitter("foo", "bar");
        Roster roster = Roster.builder().owner("Ron").build();
        roster.addPlayer(hitter);
        List<Player> players = roster.getPlayers();
        assertTrue(players.contains(hitter));
    }
}
