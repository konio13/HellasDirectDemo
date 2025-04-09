package org.hd.config;

import org.hd.config.browserDriver.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static final String CONFIGURATION_PROPERTY_FILE = "src/test/resources/conf.properties";
    private static final Properties properties = createProperties();


    private static Properties createProperties(){
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(CONFIGURATION_PROPERTY_FILE));
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return properties;
    }

    public static String getConfigurationProperty(String key) {
        return (System.getProperty(key) != null) ? System.getProperty(key) : properties.getProperty(key);
    }

    public static BrowserType getBrowserType(){
        String testBrowser = getConfigurationProperty("TEST_BROWSER").toUpperCase();
        return switch (testBrowser) {
            case "CHROME" -> BrowserType.CHROME;
            case "FIREFOX" -> BrowserType.FIREFOX;
            default -> throw new IllegalArgumentException("Unknown TEST_BROWSER: " + testBrowser);
        };
    }

    public static String getEnvironmentURL(){
        return getConfigurationProperty("TEST_ENVIRONMENT_URL").toUpperCase();
    }

}
