package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.enumeration.Position;
import com.cobaltroad.fbauction.model.Hitter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlayerRepositoryTest {
    @Autowired
    PlayerRepository repository;

    @Test
    public void hitterCanBeCreated() {
        Hitter expected = new Hitter("foo", "bar");
        repository.save(expected);
        Hitter actual = (Hitter) repository.findByName("foo", "bar");
        assertEquals(expected.getName(), actual.getName());
    }

    @Test
    public void hitterPositionsAreSaved() {
        Position[] positions = { Position.CATCHER, Position.FIRST_BASEMAN };
        Hitter expected = new Hitter("foo", "bar");
        expected.setPositions(positions);
        repository.save(expected);
        Hitter actual = (Hitter) repository.findByName("foo", "bar");
        assertEquals(expected.getPositions(), actual.getPositions());
    }
}
