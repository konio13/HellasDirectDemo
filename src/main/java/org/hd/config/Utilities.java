package org.hd.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import java.io.File;
import java.util.Random;


public class Utilities {

    public static void failTestCase(String message){
        String msgTemplate = "\n\n" +
                "----- Fail Test Case -----\n" +
                message +
                "\n--------------------------\n\n";
        Assert.fail(msgTemplate);
    }

    public static String convertFilePathToUrl(String filePathName) {
        String url = "file:///" + new File(filePathName).getAbsolutePath();
        return System.getProperty("os.name").contains("Windows") ? url.replace("\\", "/") : url;
    }

    public static String getRandomString(int length, boolean letters, boolean numbers){
        return RandomStringUtils.random(length, letters, numbers);
    }

    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }

    public static int getRandomIntegerFromSequence(int start, int end){
        return new Random().nextInt(start, end + 1);
    }


}
