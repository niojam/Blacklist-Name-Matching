package service.distancecalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.BlacklistChecker;

import static org.junit.jupiter.api.Assertions.*;

class DamerauLevenshteinDistanceCalculatorTest {

    private static DamerauLevenshteinDistanceCalculator damerauLevenshteinDistanceCalculator;

    @BeforeAll
    static void setUp() {
        damerauLevenshteinDistanceCalculator = new DamerauLevenshteinDistanceCalculator();
    }

    @Test
    void getDistance() {
        assertEquals(4, damerauLevenshteinDistanceCalculator.getDistance("", "test"));
        assertEquals(1, damerauLevenshteinDistanceCalculator.getDistance("test", "testy"));
        assertEquals(1, damerauLevenshteinDistanceCalculator.getDistance("testy", "test"));
        assertEquals(3, damerauLevenshteinDistanceCalculator.getDistance("test", "testing"));
        assertEquals(1, damerauLevenshteinDistanceCalculator.getDistance("test", "tets"));
        assertEquals(2, damerauLevenshteinDistanceCalculator.getDistance("test", "tsety"));
        assertEquals(3, damerauLevenshteinDistanceCalculator.getDistance("Test", "tsety"));
        assertEquals(1, damerauLevenshteinDistanceCalculator.getDistance("test", "test "));
        assertEquals(4, damerauLevenshteinDistanceCalculator.getDistance("file", "test"));
        assertEquals(5, damerauLevenshteinDistanceCalculator.getDistance("file", "testy"));
    }

    @Test
    void getDistanceNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> damerauLevenshteinDistanceCalculator.getDistance(null, "test"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> damerauLevenshteinDistanceCalculator.getDistance("test", null));
    }
}