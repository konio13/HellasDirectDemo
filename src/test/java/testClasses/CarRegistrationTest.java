package testClasses;

import com.google.inject.Inject;
import io.qameta.allure.*;
import org.hd.data.CarRegistrationData;
import org.hd.objects.businessObjects.CarRegistrationBusinessObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Car Insurances")
@Feature("Car Registration")
@Story("User can register car information")
@Owner("Nicolas")
@Link(name = "Hellas Direct Confluence", url = "https://www.hellasdirect.gr/")
public class CarRegistrationTest extends TestBase {

    @Inject private CarRegistrationBusinessObject carRegistrationBusinessObject;

    CarRegistrationData carData;

    @BeforeClass
    public void given() {
        carRegistrationBusinessObject.navigateToCarRegistrationHtmlForm();
        carData = new CarRegistrationData();
    }


    @Test(description="TCF01 - Verify user can submit car registration successfully")
    public void test1() {
        carRegistrationBusinessObject.submitCarRegistration(carData);
    }

    @Test(description="TCF01 - Verify car registration success message")
    public void test2() {
        carRegistrationBusinessObject.verifyCarRegistrationSubmissionSuccessMessage(carData);
    }

    @Test(description="TCF02 - Verify user is informed about failure in car registration - wrong formula")
    public void test3() {
        carData.setPlateNumber("ABCD1111");
        carRegistrationBusinessObject.submitCarRegistration(carData);
        carRegistrationBusinessObject.verifyCarRegistrationSubmissionFailureMessage();
    }

    @Test(description="TCF02 - Verify user is informed about failure in car registration - empty field")
    public void test4() {
        carData.setPlateNumber("");
        carRegistrationBusinessObject.submitCarRegistration(carData);
        carRegistrationBusinessObject.verifyCarRegistrationSubmissionFailureMessage();
    }

    @Issue("JIRA-0001-LimitCarPlateLength")
    @Test(description="TCF02 - Verify user is informed about failure in car registration - maximum length")
    public void test5() {
        carData.setPlateNumber("ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");
        carRegistrationBusinessObject.submitCarRegistration(carData);
        carRegistrationBusinessObject.verifyCarRegistrationSubmissionFailureMessage();
    }

    @Test(description="TCF02 - Verify user is informed about failure in car registration - no date selected")
    public void test6() {
        carRegistrationBusinessObject.refreshPage();
        carRegistrationBusinessObject.carRegistrationPageObject
                .inputCarPlate("ABC1234")
                .pressSubmitBtn();
        carRegistrationBusinessObject.verifyCarRegistrationSubmissionFailureMessage();
    }

}
