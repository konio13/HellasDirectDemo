package listeners.testng;

import org.hd.config.ConfigManager;
import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class AlterSuiteListener implements IAlterSuiteListener {

    @Override
    public void alter(List<XmlSuite> suites) {
        // Modify the TestNG suite at runtime for dynamic parallel execution
        for (XmlSuite suite: suites) {
            suite.setParallel(XmlSuite.ParallelMode.TESTS);
            suite.setThreadCount(Integer.parseInt(ConfigManager.getConfigurationProperty("PARALLEL_THREAD_COUNT")));
            suite.setName(ConfigManager.getConfigurationProperty("TEST_SUITE_NAME_PREFIX") + suite.getName());
        }
    }
}
