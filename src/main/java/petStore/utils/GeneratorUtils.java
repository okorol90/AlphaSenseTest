package petStore.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigInteger;

public class GeneratorUtils {
    public static BigInteger generateNumber(){
        return NumberUtils.createBigInteger(RandomStringUtils.randomNumeric(20));
    }

    public static String generateName(){
        return RandomStringUtils.randomAlphabetic(25);
    }

    public static String generatePhotoUrl(){
        return "https://"+RandomStringUtils.randomAlphabetic(30)+".com";
    }
}
