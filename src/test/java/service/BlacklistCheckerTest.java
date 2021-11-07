package service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlacklistCheckerTest {

    public static final String TEST_BLACKLIST_FILE_NAME = "names.txt";
    public static final String TEST_NOISE_WORDS_FILE_NAME = "noise_words.txt";
    private static BlacklistChecker blacklistChecker;

    @BeforeAll
    static void setUp() {
        blacklistChecker = new BlacklistChecker();
    }

    @Test
    void isNameInBlackList() {
        assertTrue(blacklistChecker.isNameInBlackList("Osama Bin Laden", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("Joe Luis Webb", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("George Bush", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("Neale Armstrong", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
    }

    @Test
    void isNameInBlackListNotInBlackList() {
        assertFalse(blacklistChecker.isNameInBlackList("Nikita Ojamae", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertFalse(blacklistChecker.isNameInBlackList("John Bush", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertFalse(blacklistChecker.isNameInBlackList("Awad bin Laden", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertFalse(blacklistChecker.isNameInBlackList("Nethan Armstrong", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertFalse(blacklistChecker.isNameInBlackList("Neale Armsterdam", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
    }

    @Test
    void isNameInBlackListNoiseWords() {
        assertTrue(blacklistChecker.isNameInBlackList("to the osama bin laden", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("osama and bin laden", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("I am George and Bush", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("There is George Bush", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("Neale and Armstrong", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("in the Neale and Armstrong", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("at Neale and on Armstrong", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
    }

    @Test
    void isNameInBlackListTypos() {
        assertTrue(blacklistChecker.isNameInBlackList("Osam Bin Laiden", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("Jo Luis Webb", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("Georg Buch", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("Niile Armstrong", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
    }

    @Test
    void isNameInBlackListTyposAndNoise() {
        assertTrue(blacklistChecker.isNameInBlackList("Osam and Bin the Laden", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("to a Jo Luis Webb", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("I am Georg  Buch", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        assertTrue(blacklistChecker.isNameInBlackList("Mr Niile Armstrong", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
    }

    @Test
    void isNameInBlackListWrongArguments() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> blacklistChecker.isNameInBlackList("  ", TEST_BLACKLIST_FILE_NAME, TEST_NOISE_WORDS_FILE_NAME));
        Assertions.assertThrows(IllegalArgumentException.class, () -> blacklistChecker.isNameInBlackList("to a Jo Luis Webb", "  ", TEST_NOISE_WORDS_FILE_NAME));
        Assertions.assertThrows(IllegalArgumentException.class, () -> blacklistChecker.isNameInBlackList("Mr Niile Armstrong", TEST_BLACKLIST_FILE_NAME, null));
    }

}