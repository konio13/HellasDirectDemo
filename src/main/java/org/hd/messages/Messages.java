package org.hd.messages;

public class Messages {

    public static String CAR_REGISTRATION_SUCCESS = "Success! Selected %s with year %s";
    public static String CAR_REGISTRATION_ERROR = "There was an error!";



    public static String getMessageWithParams(String messageTemplate, Object... params) {
        return String.format(messageTemplate, params);
    }

}


