package org.hd.config.browserDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Arrays;

public class ChromeDriverManager implements BrowserDriver {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getChromeBrowserOptions());
    }

    private static ChromeOptions getChromeBrowserOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(Arrays.asList(
                "--start-maximized",
                "--test-type",
                "--ignore-certificate-errors",
                "--disable-popup-blocking",
                "--allow-running-insecure-content",
                "--disable-translate",
                "--always-authorize-plugins",
                "--disable-gpu"));
        return options;
    }

}
