package listeners.testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestNGMethod;

import java.text.MessageFormat;

public class SuiteListenerValidateTestMethodsName implements ISuiteListener {

    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    public void onStart(ISuite suite) {
        logger.info("Validate/Set test method priority according to method name");
        for (ITestNGMethod method : suite.getAllMethods()) {
            assert  (method.getMethodName().matches("test[1-9][0-9]*")) : MessageFormat.format("TestNG test method format error [actual: {0}, expected format: test<integer> e.g. test1 ]", method.getMethodName());
            method.setPriority(Integer.parseInt(method.getMethodName().substring(4)));
        }
    }
}
