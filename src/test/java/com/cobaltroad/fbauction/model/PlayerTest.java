package com.cobaltroad.fbauction.model;

import com.cobaltroad.fbauction.database.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlayerTest {

    @Autowired
    PlayerRepository repository;

    @Test
    public void playerCanBeCreated() {
        Player p = new Player("foo", "bar");
        repository.save(p);
    }
}
