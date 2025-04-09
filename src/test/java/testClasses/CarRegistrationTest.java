package testClasses;

import com.google.inject.Inject;
import data.CarRegistrationData;
import io.qameta.allure.*;
import org.hd.objects.businessObject.GoogleBusinessObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//@Epic("Epic-Google Web interface")
//@Feature("Feature-Google Search")
//@Story("Story-User can search for something on Google")
//@Owner("John Vavouras")
//@Link(name = "Confluence feature info", url = "https://dev.example.com/")
public class CarRegistrationTest extends TestBase {

    @Inject private GoogleBusinessObject googleBO;

    @BeforeClass
    public void visitGoogle() {
        googleBO.navigateToCarRegistrationForm();
        googleBO.doNothing();
    }


    @Test(description="This is the first step")
    public void test1() {
        LOG.info("It's all right!!!");
        CarRegistrationData data = new CarRegistrationData();
        LOG.info(data.getPlateNumber());
        LOG.info(data.getPlateYear());
//        assert false;

    }

//    @Test(description="Login with surname")
//    public void test2() {
//        googleBO.visitGoogle();
//        googleBO.doNothing();
//        googleBO.searchFor("Life has no meanings");
//    }



}
