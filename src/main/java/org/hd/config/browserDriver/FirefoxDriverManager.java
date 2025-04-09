package org.hd.config.browserDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements BrowserDriver {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(getFirefoxBrowserOptions());
    }

    private static FirefoxOptions getFirefoxBrowserOptions () {
        FirefoxOptions options = new FirefoxOptions();
        return options;
    }

}
