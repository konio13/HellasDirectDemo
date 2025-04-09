package testClasses;

import com.google.inject.Inject;
import io.qameta.allure.*;
import org.hd.objects.businessObject.GoogleBusinessObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Epic("Epic-Google Web interface")
@Feature("Feature-Google Search")
@Story("Story-User can search for something on Google")
@Owner("John Vavouras")
@Link(name = "Confluence feature info", url = "https://dev.example.com/")
public class GoogleTest extends TestBase {

    @Inject private GoogleBusinessObject googleBO;

    @BeforeClass
    public void visitGoogle() {
        googleBO.visitGoogle(true);
    }


    @Test(description="This test attempts to log into the website using a login and a password.")
    public void test1() {
        googleBO.searchFor("What is the meaning of life?");
        googleBO.doNothing();
    }

    @Test(description="Login with surname")
    public void test2() {
        googleBO.visitGoogle();
        googleBO.doNothing();
        googleBO.searchFor("Life has no meanings");
    }



}
