package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNgListener implements ISuiteListener, ITestListener, IInvokedMethodListener {
    @Override
    public void onStart(ISuite suite) {
        System.out.println("Hello from Giza Systems Interns 2025\n" + "we are starting our excution from here....");
        PropertiesReader.loadProperties();
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Excution is finished....Thank you");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("[INFO] Starting test: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("[PASS] Test passed: " + result.getName());
    }

/*    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("[FAIL] Test failed: " + result.getName());
        try {
            TakesScreenshot ts = (TakesScreenshot) DriverFactory.getDriver();
            File src = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String dest = "FailedTestsScreenshots/" + result.getName() + "_" + timestamp + ".png";
            Files.createDirectories(Paths.get("screenshots"));
            Files.copy(src.toPath(), Paths.get(dest));
            System.out.println("[INFO] Screenshot saved at: " + dest);
        } catch (IOException | ClassCastException e) {
            System.out.println("[ERROR] Failed to take screenshot: " + e.getMessage());
        }
    }*/

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        WebDriver driver = DriverFactory.getDriver();
        if (ITestResult.FAILURE == testResult.getStatus() && driver != null) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
        }
    }


    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("[SKIP] Test skipped: " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("[WARN] Test failed but within success percentage: " + result.getName());
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        System.out.println("[TIMEOUT] Test failed due to timeout: " + result.getName());
        onTestFailure(result);
    }
}


