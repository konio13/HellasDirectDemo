package org.hd.objects.businessObject;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hd.config.browserDriver.DriverManager;
import org.openqa.selenium.WebDriver;

public abstract class BusinessObjectBase {

    protected Logger LOG = LogManager.getLogger(getClass());

    protected WebDriver getDriver() {
        return DriverManager.get().getDriver();
    }

    @Step
    public void navigateTo(String url) {
        getDriver().get(url);
    }

    @Step
    public void refreshPage() {
        getDriver().navigate().refresh();
    }

}