package testClasses;

import com.google.inject.Inject;
import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.hd.objects.businessObjects.CarRegistrationBusinessObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Car Insurances")
@Feature("Car Registration")
@Story("Car Registration Form validation")
@Owner("Nicolas")
@Link(name = "Hellas Direct Confluence", url = "https://www.hellasdirect.gr/")
@Tag("TCF03")
public class CarRegistrationFormValidationTest extends TestBase {

    @Inject private CarRegistrationBusinessObject carRegistrationBusinessObject;

    @BeforeClass
    public void given() {
        carRegistrationBusinessObject.navigateToCarRegistrationHtmlForm();
    }

    @Test(description="TCF03 - Verify car plate field placeholder")
    public void test1() {
        carRegistrationBusinessObject.verifyCarPlateFieldPlaceholder();
    }

    @Test(description="TCF03 - Verify car plate default value")
    public void test2() {
        carRegistrationBusinessObject.verifyCarPlateDefaultValue();
    }

    @Test(description="TCF03 - Verify car date dropbox default value")
    public void test3() {
        carRegistrationBusinessObject.verifyCarDateDefaultDropboxValue();
    }

}
