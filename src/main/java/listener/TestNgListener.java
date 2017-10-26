package listener;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestNgListener implements ITestListener {
    private final static Logger log = Logger.getLogger(TestNgListener.class);
    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("Test is started: "+iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info("Test success: "+iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("Test failure: "+iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info("Test skipped: "+iTestResult.getTestName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        log.info("Test failed with success percentage: "+iTestResult.getTestName());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("Test on start: "+iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("Test on finish: "+iTestContext.getName());
    }
}
