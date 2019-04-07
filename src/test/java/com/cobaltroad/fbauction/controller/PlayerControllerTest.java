package com.cobaltroad.fbauction.controller;

import com.cobaltroad.fbauction.database.*;
import com.cobaltroad.fbauction.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {
    @MockBean
    PlayerRepository playerRepository;

    @MockBean
    HitterProjectionRepository hitterProjectionRepository;

    @MockBean
    PitcherProjectionRepository pitcherProjectionRepository;

    @MockBean
    LeagueStatRepository leagueStatRepository;

    @MockBean
    RosterRepository rosterRepository;

    @InjectMocks
    PlayerController controller;

    @BeforeEach
    public void setup() {
        List<Player> mockList = Arrays.asList(mock(Player.class));
        when(playerRepository.findAvailablePlayerByName(any(String.class))).thenReturn(mockList);
    }

    @Test
    public void noSearchString() {
        PlayerResponse players = controller.index(null);
        System.out.println(players);
    }
}
