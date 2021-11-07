package service.distancecalculator;

import org.apache.commons.codec.EncoderException;

public interface StringDistanceCalculator {

    Integer getDistance(String source, String target) throws EncoderException;

}
