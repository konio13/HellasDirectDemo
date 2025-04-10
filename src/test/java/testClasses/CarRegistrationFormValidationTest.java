package testClasses;

import com.google.inject.Inject;
import io.qameta.allure.*;
import org.hd.data.CarRegistrationData;
import org.hd.objects.businessObject.CarRegistrationBusinessObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Car Insurances")
@Feature("Car Registration")
@Story("Car Registration Form validation")
@Owner("Nicolas")
@Link(name = "Hellas Direct Confluence", url = "https://www.hellasdirect.gr/")
public class CarRegistrationFormValidationTest extends TestBase {

    @Inject private CarRegistrationBusinessObject carRegistrationBusinessObject;

    @BeforeClass
    public void given() {
        carRegistrationBusinessObject.navigateToCarRegistrationHtmlForm();
    }


    @Test(description="Verify car plate field placeholder")
    public void test1() {
        carRegistrationBusinessObject.verifyCarPlateFieldPlaceholder();
    }

    @Test(description="Verify car plate default value")
    public void test2() {
        carRegistrationBusinessObject.verifyCarPlateDefaultValue();
    }

    @Test(description="Verify car date dropbox default value")
    public void test3() {
        carRegistrationBusinessObject.verifyCarDateDefaultDropboxValue();
    }



}
