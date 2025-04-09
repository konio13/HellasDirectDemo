package testClasses;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hd.config.ConfigManager;
import org.hd.config.browserDriver.DriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class TestBase {

    protected Logger LOG = LogManager.getLogger(getClass());


    @BeforeTest
    public void openBrowser(){
        DriverManager.get().setDriver(ConfigManager.getBrowserType());
    }


    @AfterTest
    public void closeBrowser() {
        DriverManager.get().closeDriver();
    }

}
