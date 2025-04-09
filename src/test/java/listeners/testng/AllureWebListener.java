package listeners.testng;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import org.hd.config.browserDriver.DriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.ByteArrayInputStream;

public class AllureWebListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    private static final ThreadLocal<Boolean> previousStepFailed = ThreadLocal.withInitial(() -> false);


    @Override
    public void onStart(ISuite suite) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onTestStart(ITestResult result) {

        if (previousStepFailed.get()) {
            result.setStatus(ITestResult.SKIP);
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {

        previousStepFailed.set(true);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onFinish(ITestContext context) {
        previousStepFailed.remove();
    }

    @Override
    public void onFinish(ISuite suite) {
        AllureEnvironmentWriter.allureEnvironmentWriter(ImmutableMap.<String, String>builder()
                .put("OS", System.getProperty("os.name"))
                .put("Browser", DriverManager.browserType)
                .put("Browser Version", DriverManager.browserVersion)
                .build()
        );
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod() && testResult.getStatus() == ITestResult.FAILURE) {
            takeScreenshotAndAddToAllureReport();
        }

    }

    private byte[] takeScreenshot() {
        return  ((TakesScreenshot) DriverManager.get().getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    private void takeScreenshotAndAddToAllureReport() {
        byte[] bytes = takeScreenshot();
        Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(bytes), "png");
    }

}