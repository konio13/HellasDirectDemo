package org.hd.objects.businessObject;

import com.google.inject.Inject;
import org.hd.config.ConfigManager;
import org.hd.objects.pageObjects.GooglePageObject;
import org.hd.config.Utilities;
import io.qameta.allure.Step;

public class GoogleBusinessObject extends BusinessObjectBase {

    @Inject protected GooglePageObject googlePageObject;

    @Step
    public void visitGoogle() {
        googlePageObject.getDriver().get("https://www.google.com");
    }

    @Step
    public void visitGoogle(boolean acceptCookies) {
        visitGoogle();
        if (acceptCookies) {
            googlePageObject.acceptCookies();
        } else {
            googlePageObject.rejectCookies();
        }
    }

    @Step
    public void searchFor(String text) {
        googlePageObject
            .inputSearchText(text)
            .pressSearchButton();
        Utilities.sleep(3);
    }

    @Step
    public void doNothing() {
        LOG.info("Doing Nothing");
    }

    @Step
    public void navigateToCarRegistrationForm() {
        String url = Utilities.convertFilePathToUrl(ConfigManager.getConfigurationProperty("TEST_SAMPLE_HTML_FILE_URL"));
        googlePageObject.getDriver().get(url);
        Utilities.sleep(3);
    }
}
