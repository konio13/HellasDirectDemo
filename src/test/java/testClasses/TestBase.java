package testClasses;

import org.hd.config.ConfigManager;
import org.hd.config.browserDriver.BrowserType;
import org.hd.config.browserDriver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class TestBase {

    protected Logger LOG = LogManager.getLogger(getClass());


    @BeforeTest
    public void openBrowser(){
//        DriverManager.get().setDriver(BrowserType.CHROME);
        //DriverManager.get().setDriver(BrowserType.FIREFOX);
        DriverManager.get().setDriver(ConfigManager.getBrowserType());

    }


    @AfterTest
    public void closeBrowser() {
        DriverManager.get().closeDriver();
    }


}
