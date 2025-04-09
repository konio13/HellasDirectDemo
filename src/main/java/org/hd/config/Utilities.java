package org.hd.config;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;


public class Utilities {

    public static void failTestCase(String message){
        String msgTemplate = "\n\n" +
                "----- Fail Test Case -----\n" +
                message +
                "\n--------------------------\n\n";
        Assert.fail(msgTemplate);
    }

    public static void sleep(int seconds){
        try {
            Thread.sleep(1000L * seconds);
        } catch (InterruptedException e){
            System.out.println(e);
        }
    }

    public static String convertFilePathToUrl(String filePathName) {
        String url = "file:///" + new File(filePathName).getAbsolutePath();
        return System.getProperty("os.name").contains("Windows") ? url.replace("\\", "/") : url;
    }

    public static String getRandomString(int length, boolean letters, boolean numbers){
        return RandomStringUtils.random(length, letters, numbers);
    }

    public static String getRandomIntegerFromSequence(int start, int end){
        List<String> listOfIntegers = IntStream
                .rangeClosed(start, end)
                .mapToObj(String::valueOf)
                .toList();
        return listOfIntegers.get(new Random().nextInt(listOfIntegers.size()));
    }


}
