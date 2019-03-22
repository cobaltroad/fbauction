package com.cobaltroad.fbauction.enumeration;

import com.cobaltroad.fbauction.model.Hitter;
import org.junit.jupiter.api.Test;

import static com.cobaltroad.fbauction.enumeration.Position.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {

    @Test
    public void firstBasemanHasCornerEligibility() {
        Position[] positions = { FIRST_BASEMAN };
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(FIRST_BASEMAN));
        assertFalse(hitter.isA(THIRD_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertFalse(hitter.isA(MIDDLE_INFIELDER));
    }

    @Test
    public void secondBasemanHasMiddleEligibility() {
        Position[] positions = { SECOND_BASEMAN };
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(SECOND_BASEMAN));
        assertFalse(hitter.isA(SHORTSTOP));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));
        assertFalse(hitter.isA(CORNER_INFIELDER));
    }

    @Test
    public void thirdBasemanHasCornerEligibility() {
        Position[] positions = { THIRD_BASEMAN };
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(THIRD_BASEMAN));
        assertFalse(hitter.isA(FIRST_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertFalse(hitter.isA(MIDDLE_INFIELDER));
    }

    @Test
    public void shortstopHasMiddleEligibility() {
        Position[] positions = { SHORTSTOP };
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(SHORTSTOP));
        assertFalse(hitter.isA(SECOND_BASEMAN));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));
        assertFalse(hitter.isA(CORNER_INFIELDER));
    }

    @Test
    public void firstAndThirdHasCornerEligibility() {
        Position[] positions = { FIRST_BASEMAN, THIRD_BASEMAN };
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(FIRST_BASEMAN));
        assertTrue(hitter.isA(THIRD_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertFalse(hitter.isA(MIDDLE_INFIELDER));
    }

    @Test
    public void secondAndShortHasMiddleEligibility() {
        Position[] positions = { SECOND_BASEMAN, SHORTSTOP };
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(SECOND_BASEMAN));
        assertTrue(hitter.isA(SHORTSTOP));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));
        assertFalse(hitter.isA(CORNER_INFIELDER));
    }

    @Test
    public void firstAndSecondEligibleEverywhere() {
        Position[] positions = { FIRST_BASEMAN, SECOND_BASEMAN };
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(FIRST_BASEMAN));
        assertTrue(hitter.isA(SECOND_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));
    }

    @Test
    public void shortAndThirdEligibleEverywhere() {
        Position[] positions = { SHORTSTOP, THIRD_BASEMAN };
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(SHORTSTOP));
        assertTrue(hitter.isA(THIRD_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));

    }
}
