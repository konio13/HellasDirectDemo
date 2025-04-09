package listeners.testng;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;

import java.util.List;

public class AlterSuiteListener implements IAlterSuiteListener {

    @Override
    public void alter(List<XmlSuite> suites) {
        // Modify the TestNG suite at runtime for dynamic parallel execution
        for (XmlSuite suite: suites) {
            suite.setParallel(XmlSuite.ParallelMode.TESTS);
            suite.setThreadCount(3);
            suite.setName("HellasDirect:" + suite.getName());
        }
    }
}
