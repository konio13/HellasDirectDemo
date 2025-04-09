package listeners.testng;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.hd.config.googleGuice.GuiceModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestNGMethod;


public class SuiteListenerInjectDependenciesTestMethods implements ISuiteListener {

    private final Logger logger = LogManager.getLogger(getClass());
    private static final Injector injector = Guice.createInjector(new GuiceModule());

    @Override
    public void onStart(ISuite suite) {

        logger.info("Inject dependencies into tests ( Google Guice )");
        for (ITestNGMethod test : suite.getAllMethods()) {
            injector.injectMembers(test.getInstance());
        }
    }
}
