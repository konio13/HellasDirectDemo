package org.hd.objects.pageObjects;


import org.openqa.selenium.By;

public class GooglePageObject extends PageObjectBase {


    public enum Locators {
        ACCEPT_COOKIES_BTN(By.id("L2AGLb")),
        REJECT_COOKIES_BTN(By.id("W0wltc")),
        SEARCH_TEXT_INPUT(By.id("APjFqb")),
        SEARCH_BTN(By.name("btnK"));

        private final By locator;

        Locators(By locator) {
            this.locator = locator;
        }

        public By getLocator() {
            return locator;
        }
    }


    public void acceptCookies() {
        findAndWaitElementToBeClickable(Locators.ACCEPT_COOKIES_BTN.getLocator()).click();
    }

    public void rejectCookies() {
        findAndWaitElementToBeClickable(Locators.REJECT_COOKIES_BTN.getLocator()).click();
    }

    public GooglePageObject inputSearchText(String text) {
        writeTextToElement(Locators.SEARCH_TEXT_INPUT.getLocator(), text);
        return this;
    }

    public void pressSearchButton() {
        findAndWaitElementToBeClickable(Locators.SEARCH_BTN.getLocator()).click();
    }
}
