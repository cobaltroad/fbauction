package com.cobaltroad.fbauction.enumeration;

import com.cobaltroad.fbauction.model.Hitter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.cobaltroad.fbauction.enumeration.Position.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PositionTest {

    @Test
    public void firstBasemanHasCornerEligibility() {
        List<Position> positions = Arrays.asList(FIRST_BASEMAN);
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(FIRST_BASEMAN));
        assertFalse(hitter.isA(THIRD_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertFalse(hitter.isA(MIDDLE_INFIELDER));
    }

    @Test
    public void secondBasemanHasMiddleEligibility() {
        List<Position> positions = Arrays.asList(SECOND_BASEMAN);
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(SECOND_BASEMAN));
        assertFalse(hitter.isA(SHORTSTOP));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));
        assertFalse(hitter.isA(CORNER_INFIELDER));
    }

    @Test
    public void shortstopHasMiddleEligibility() {
        List<Position> positions = Arrays.asList(SHORTSTOP);
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(SHORTSTOP));
        assertFalse(hitter.isA(SECOND_BASEMAN));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));
        assertFalse(hitter.isA(CORNER_INFIELDER));
    }

    @Test
    public void thirdBasemanHasCornerEligibility() {
        List<Position> positions = Arrays.asList(THIRD_BASEMAN);
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(THIRD_BASEMAN));
        assertFalse(hitter.isA(FIRST_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertFalse(hitter.isA(MIDDLE_INFIELDER));
    }

    @Test
    public void firstAndThirdHasCornerEligibility() {
        List<Position> positions = Arrays.asList(FIRST_BASEMAN, THIRD_BASEMAN);
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(FIRST_BASEMAN));
        assertTrue(hitter.isA(THIRD_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertFalse(hitter.isA(MIDDLE_INFIELDER));
    }

    @Test
    public void secondAndShortHasMiddleEligibility() {
        List<Position> positions = Arrays.asList(SECOND_BASEMAN, SHORTSTOP);
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(SECOND_BASEMAN));
        assertTrue(hitter.isA(SHORTSTOP));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));
        assertFalse(hitter.isA(CORNER_INFIELDER));
    }

    @Test
    public void firstAndSecondEligibleEverywhere() {
        List<Position> positions = Arrays.asList(FIRST_BASEMAN, SECOND_BASEMAN);
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(FIRST_BASEMAN));
        assertTrue(hitter.isA(SECOND_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));
    }

    @Test
    public void shortAndThirdEligibleEverywhere() {
        List<Position> positions = Arrays.asList(SHORTSTOP, THIRD_BASEMAN);
        Hitter hitter = new Hitter("foo", "bar");
        hitter.setPositions(positions);
        assertTrue(hitter.isA(SHORTSTOP));
        assertTrue(hitter.isA(THIRD_BASEMAN));
        assertTrue(hitter.isA(CORNER_INFIELDER));
        assertTrue(hitter.isA(MIDDLE_INFIELDER));

    }
}
