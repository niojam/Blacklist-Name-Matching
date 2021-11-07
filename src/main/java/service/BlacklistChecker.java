package service;

import exception.BusinessLogicException;
import org.apache.commons.codec.EncoderException;
import service.distancecalculator.DamerauLevenshteinDistanceCalculator;
import service.distancecalculator.SoundexDistanceCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static util.Constants.NAME_DELIMITER;
import static util.Constants.NAME_SPLIT_REGEX;
import static util.FileReaderUtil.getFileAsIOStream;
import static util.FileReaderUtil.getFileContentAsList;

public class BlacklistChecker {

    private final DamerauLevenshteinDistanceCalculator damerauLevenshteinDistanceCalculator;
    private final SoundexDistanceCalculator soundexDistanceCalculator;

    public BlacklistChecker() {
        damerauLevenshteinDistanceCalculator = new DamerauLevenshteinDistanceCalculator();
        soundexDistanceCalculator = new SoundexDistanceCalculator();
    }

    public boolean isNameInBlackList(String name, String blacklistFileName, String noiseWordsFileName) {
        if (name == null || name.isBlank()
                || blacklistFileName == null || blacklistFileName.isBlank()
                || noiseWordsFileName == null || noiseWordsFileName.isBlank()) {
            throw new IllegalArgumentException();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                getFileAsIOStream(blacklistFileName), StandardCharsets.UTF_8))) {

            name = sortNameAndFilterNoise(name, noiseWordsFileName);
            String line;
            while ((line = br.readLine()) != null) {
                String blackListName = Arrays.stream(line.toLowerCase().split(NAME_SPLIT_REGEX))
                        .sorted()
                        .collect(Collectors.joining(NAME_DELIMITER));
                if (name.equals(blackListName) || (soundexDistanceCalculator.getDistance(blackListName, name) >= 3
                        && damerauLevenshteinDistanceCalculator.getDistance(blackListName, name) <= 3))
                    return true;
            }
        } catch (IOException | URISyntaxException | EncoderException e) {
            throw new BusinessLogicException("Impossible to validate name");
        }
        return false;
    }


    private String sortNameAndFilterNoise(String name, String noiseWordsFileName) throws IOException, URISyntaxException {
        List<String> noiseWords = getFileContentAsList(noiseWordsFileName)
                .stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        List<String> nameListToLower = Arrays.asList(name.toLowerCase().split(NAME_SPLIT_REGEX));

        return nameListToLower
                .stream()
                .filter(namePart -> !noiseWords.contains(namePart))
                .sorted()
                .collect(Collectors.joining(NAME_DELIMITER));
    }

}
