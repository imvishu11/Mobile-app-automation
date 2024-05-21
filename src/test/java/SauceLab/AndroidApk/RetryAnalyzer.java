package SauceLab.AndroidApk;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * The class {@link RetryAnalyzer} holds method for re-run tests on failure.
 */
public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final int maxRetryCount = 0; // Set your retry count here

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            retryCount++;
            return true;
        }
        return false;
    }
}