package org.hd.config.browserDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hd.config.Utilities;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverUtilities {

    protected Logger LOG = LogManager.getLogger(getClass());
    private static final int EXPECTED_CONDITIONS_TIMEOUT_IN_SECONDS = 10;

    public WebDriver getDriver() {
        return DriverManager.get().getDriver();
    }

    public WebElement findAndWaitElementToBeClickable(By locator){
        WebElement element = null;
        try {
            element = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPECTED_CONDITIONS_TIMEOUT_IN_SECONDS)).
                    until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            Utilities.failTestCase(
                    String.format("Element is not clickable [%s]", locator.toString())
            );
        }
        return element;
    }

    public WebElement findAndWaitElementToBeVisible(By locator){
        WebElement element = null;
        try {
            element = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPECTED_CONDITIONS_TIMEOUT_IN_SECONDS)).
                    until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            Utilities.failTestCase(
                    String.format("Element is not visible [%s]", locator.toString())
            );
        }
        return element;
    }

    public WebElement findAndWaitElementToBePresent(By locator){
        WebElement element = null;
        try {
            element = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPECTED_CONDITIONS_TIMEOUT_IN_SECONDS)).
                    until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            Utilities.failTestCase(
                    String.format("Element is not present [%s]", locator.toString())
            );
        }
        return element;
    }

    public void waitForElementToContainAttributeWithValue(By locator, String attribute, String value) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(EXPECTED_CONDITIONS_TIMEOUT_IN_SECONDS)).
                    until(ExpectedConditions.attributeToBe(getDriver().findElement(locator),attribute,value));
        } catch (TimeoutException e) {
            Utilities.failTestCase(
                    String.format("Element [%s] does not contain attribute [%s] with value [%s]", locator.toString(), attribute, value)
            );
        }
    }

    public void writeTextToElement(By locator, String value){
        writeTextToElement(locator,value,true);
    }

    public void writeTextToElement(By locator, String value, boolean clearFirst){
        WebElement element = findAndWaitElementToBeClickable(locator);
        if(value != null) {
            if (clearFirst) {
                element.clear();
            }
            element.sendKeys(value);
            waitForElementToContainAttributeWithValue(locator,"value", value);
        }else {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
        }

    }

    public void selectByValue(By locator, String value){
        Select select;
        try {
            select = new Select(findAndWaitElementToBeVisible(locator));
            select.selectByValue(value);
        } catch (TimeoutException e) {
            Utilities.failTestCase(
                    String.format("Cannot locate option with selector locator [%s] and value [%s]", locator.toString(), value)
            );
        }
    }

    public String getElementInnerHTML(WebElement element){
        return element.getAttribute("innerHTML");
    }




}
