package service.distancecalculator;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.language.Soundex;

public class SoundexDistanceCalculator implements StringDistanceCalculator {

    private final Soundex soundexEng;

    public SoundexDistanceCalculator() {
        soundexEng = Soundex.US_ENGLISH;
    }

    @Override
    public Integer getDistance(String source, String target) throws EncoderException {
        return soundexEng.difference(source, target);

    }
}
