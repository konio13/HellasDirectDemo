package org.hd.objects.businessObject;

import com.google.inject.Inject;
import io.qameta.allure.Step;
import org.hd.config.ConfigManager;
import org.hd.config.Utilities;
import org.hd.data.CarRegistrationData;
import org.hd.messages.Messages;
import org.hd.objects.pageObjects.CarRegistrationPageObject;
import org.junit.Assert;

public class CarRegistrationBusinessObject extends BusinessObjectBase {

    @Inject public CarRegistrationPageObject carRegistrationPageObject;

    @Step
    public void navigateToCarRegistrationHtmlForm() {
        String url = Utilities.convertFilePathToUrl(
                ConfigManager.getConfigurationProperty("TEST_SAMPLE_HTML_FILE_URL")
        );
        navigateTo(url);
    }

    @Step
    public void submitCarRegistration(CarRegistrationData carData) {
        validateCarPlateNumber(carData.getPlateNumber(), false);
        validateCarPlateYear(carData.getPlateYear());
        carRegistrationPageObject
                .inputCarPlate(carData.getPlateNumber())
                .selectCarPlateYear(carData.getPlateYear())
                .pressSubmitBtn();
    }

    public void validateCarPlateNumber(String carPlateNumber, boolean failOnError) {
        String regexPattern = "^([A-Z]{3})([0-9]{4})$";
        if (!carPlateNumber.matches(regexPattern)) {
            String msg = "carPlateNumber [%s] is not aligned to pattern \"[%s]\"".formatted(carPlateNumber, regexPattern);
            LOG.warn(msg);
            if (failOnError) {
                Utilities.failTestCase(msg);
            }
        }
    }

    public void validateCarPlateYear(int carPlateYear) {
        // CarPlateYear drop down menu supports only 2015-2017
        if (!Utilities.isInRange(carPlateYear, 2015, 2017)) {
            String msg = "CarPlateYear [%s] is not valid".formatted(carPlateYear);
            Utilities.failTestCase(msg);
        }
    }

    @Step
    public void verifyCarRegistrationSubmissionResultMessage(String expectedText) {
        Assert.assertEquals(expectedText, carRegistrationPageObject.getSubmissionResultMessageText());
    }

    @Step
    public void verifyCarRegistrationSubmissionSuccessMessage(CarRegistrationData carData) {
        verifyCarRegistrationSubmissionResultMessage(
                Messages.getMessageWithParams(Messages.CAR_REGISTRATION_SUCCESS, carData.getPlateNumber(), carData.getPlateYear())
        );
    }

    @Step
    public void verifyCarRegistrationSubmissionFailureMessage() {
        verifyCarRegistrationSubmissionResultMessage(Messages.CAR_REGISTRATION_ERROR);
    }



}
