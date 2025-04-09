package org.hd.objects.pageObjects;


import org.openqa.selenium.By;

public class CarRegistrationPageObject extends PageObjectBase {


    public enum Locators {
        INPUT_CAR_PLATE(By.id("input-number-plates")),
        SELECTOR_YEAR(By.id("select-year")),
        SUBMIT_BTN(By.id("btn-submit")),
        RESULT_MSG(By.cssSelector("[role=alert][style='display: block;']"));


        private final By locator;

        Locators(By locator) {
            this.locator = locator;
        }

        public By getLocator() {
            return locator;
        }
    }


    public CarRegistrationPageObject inputCarPlate(String carPlate) {
        writeTextToElement(Locators.INPUT_CAR_PLATE.getLocator(), carPlate);
        return this;
    }

    public CarRegistrationPageObject selectCarPlateYear(Integer carPlateYear) {
        selectByValue(Locators.SELECTOR_YEAR.getLocator(), carPlateYear.toString());
        return this;
    }

    public void pressSubmitBtn() {
        findAndWaitElementToBeClickable(Locators.SUBMIT_BTN.getLocator()).click();
    }

    public String getSubmissionResultMessageText() {
        return getElementInnerHTML(findAndWaitElementToBePresent(Locators.RESULT_MSG.getLocator())).trim();
    }
}
