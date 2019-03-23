package com.cobaltroad.fbauction.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HitterProjectionTest {

    private HitterProjection projection;

    @BeforeEach
    public void setup() {
        projection = HitterProjection.builder()
                .source("foo")
                .gamesPlayed(145)
                .plateAppearances(628)
                .atBats(495)
                .hits(144)
                .doubles(26)
                .triples(4)
                .homeruns(38)
                .runs(105)
                .runsBattedIn(92)
                .walks(118)
                .strikeouts(134)
                .hitByPitch(10)
                .stolenBases(25)
                .caughtStealing(5)
                .build();
    }

    @Test
    public void calculatesBattingAverage() {
        assertEquals(0.291, projection.battingAverage(), 0.001);
    }

    @Test
    public void calculatesOnBaseAverage() {
        assertEquals(0.433, projection.onBaseAverage(), 0.001);
    }

    @Test
    public void calculatesSluggingPercentage() {
        assertEquals(0.590, projection.sluggingPercentage(), 0.001);
    }

    @Test
    public void calculatesOnBasePlusSlugging() {
        assertEquals(1.023, projection.onBasePlusSlugging(), 0.001);
    }

    @Test
    @Disabled // missing IBB + SF data
    public void calculatesWeightedOnBaseAverage() {
        assertEquals(0.424, projection.weightedOnBaseAverage(), 0.001);
    }
}
