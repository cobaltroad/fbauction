package com.cobaltroad.fbauction.database;

import com.cobaltroad.fbauction.model.HitterProjection;
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
public class HitterProjectionRepositoryTest {

    @Autowired
    HitterProjectionRepository repository;

    @Test
    public void sumOfAllHits() {
        HitterProjection projection1 = HitterProjection.builder().league("nl").hits(1).atBats(2).build();
        repository.save(projection1);

        HitterProjection projection2 = HitterProjection.builder().league("nl").hits(2).atBats(4).build();
        repository.save(projection2);

        assertEquals(1.5, (double) repository.averageOfAllHits("nl"));
        assertEquals(0.71, repository.stddevOfAllHits("nl"), 0.01);
    }
}
