package org.hd.config.browserDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager {

    private static final Logger LOG = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> THREAD_LOCAL_DRIVER = new ThreadLocal<>();
    private static final DriverManager INSTANCE = new DriverManager();
    public static String browserType = "Unknown";
    public static String browserVersion = "Unknown";

    private DriverManager() {
        LOG.info("DriverManager instance created.");
    }

    public static DriverManager get() {
        return INSTANCE;
    }

    public WebDriver getDriver(){
        return THREAD_LOCAL_DRIVER.get();
    }

    public void closeDriver() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
        THREAD_LOCAL_DRIVER.remove();
        LOG.info("WebDriver instance is closed and removed from ThreadLocal.");
    }

    public void setDriver(BrowserType browserType) {
        BrowserDriver browserDriver;
        switch (browserType) {
            case CHROME -> browserDriver = new ChromeDriverManager();
            case FIREFOX -> browserDriver = new FirefoxDriverManager();
            default -> throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        THREAD_LOCAL_DRIVER.set(browserDriver.createDriver());
        DriverManager.browserType = browserType.name();
        DriverManager.browserVersion = getDriverVersion();
        LOG.info("WebDriver instance ( {} : {} ) created and set in ThreadLocal.", browserType, browserVersion);
    }

    private String getDriverVersion() {
        WebDriver driver = getDriver();
        if (driver != null) {
            return ((RemoteWebDriver) getDriver()).getCapabilities().getBrowserVersion();
        } else {
            LOG.warn("Driver is null. Cannot get browser version.");
            return "unknown";
        }

    }


}
