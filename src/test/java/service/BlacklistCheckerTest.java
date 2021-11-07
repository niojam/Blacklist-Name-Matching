package service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlacklistCheckerTest {

    private static BlacklistChecker blacklistChecker;

    @BeforeAll
    static void setUp() {
        blacklistChecker = new BlacklistChecker();
    }

    @Test
    void isNameInBlackList() {
        assertTrue(blacklistChecker.isNameInBlackList("Osama Bin Laden", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("Joe Luis Webb", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("George Bush", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("Neale Armstrong", "names.txt", "noise_words.txt"));
    }

    @Test
    void isNameInBlackListNotInBlackList() {
        assertFalse(blacklistChecker.isNameInBlackList("Nikita Ojamae", "names.txt", "noise_words.txt"));
        assertFalse(blacklistChecker.isNameInBlackList("John Bush", "names.txt", "noise_words.txt"));
        assertFalse(blacklistChecker.isNameInBlackList("Awad bin Laden", "names.txt", "noise_words.txt"));
        assertFalse(blacklistChecker.isNameInBlackList("Nethan Armstrong", "names.txt", "noise_words.txt"));
        assertFalse(blacklistChecker.isNameInBlackList("Neale Armsterdam", "names.txt", "noise_words.txt"));
    }

    @Test
    void isNameInBlackListNoiseWords() {
        assertTrue(blacklistChecker.isNameInBlackList("to the osama bin laden", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("osama and bin laden", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("I am George and Bush", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("There is George Bush", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("Neale and Armstrong", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("in the Neale and Armstrong", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("at Neale and on Armstrong", "names.txt", "noise_words.txt"));
    }

    @Test
    void isNameInBlackListTypos() {
        assertTrue(blacklistChecker.isNameInBlackList("Osam Bin Laiden", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("Jo Luis Webb", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("Georg Buch", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("Niile Armstrong", "names.txt", "noise_words.txt"));
    }

    @Test
    void isNameInBlackListTyposAndNoise() {
        assertTrue(blacklistChecker.isNameInBlackList("Osam and Bin the Laden", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("to a Jo Luis Webb", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("I am Georg  Buch", "names.txt", "noise_words.txt"));
        assertTrue(blacklistChecker.isNameInBlackList("Mr Niile Armstrong", "names.txt", "noise_words.txt"));
    }

}