package org.hd.config.browserDriver;

import org.hd.config.Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverUtilities {

    protected Logger LOG = LogManager.getLogger(getClass());
    private static final int EXPECTED_CONDITIONS_TIMEOUT_IN_SECONDS = 10;


    public WebDriver getDriver() {
        return DriverManager.get().getDriver();
    }

    public void waitUrlToMatch(String url){
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(EXPECTED_CONDITIONS_TIMEOUT_IN_SECONDS)).
                    until(ExpectedConditions.urlToBe(url));
        } catch (TimeoutException e) {
            Utilities.failTestCase(
                    String.format("Wait for page URL failed ( current:%s, expected:%s )", getDriver().getCurrentUrl(), url)
            );
        }
    }

    public void waitUrlToContain(String expectedUrlPart){
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(EXPECTED_CONDITIONS_TIMEOUT_IN_SECONDS)).
                    until(ExpectedConditions.urlContains(expectedUrlPart));
        } catch (TimeoutException e) {
            Utilities.failTestCase(
                    String.format("Wait for page URL failed ( current:%s, expected:%s )", getDriver().getCurrentUrl(), expectedUrlPart)
            );
        }
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

    public String runJavascript(String command, Object... args){
        return (String) ((JavascriptExecutor) getDriver()).executeScript(command, args);
    }

    public void scrollElementIntoViewWithAlignment(By locator) {
        WebElement element = findAndWaitElementToBePresent(locator);
        runJavascript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollElementIntoCenterView(By locator) {
        WebElement element = findAndWaitElementToBePresent(locator);
        runJavascript("var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));", element);
    }


}
