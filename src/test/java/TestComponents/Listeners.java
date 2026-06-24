package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static resources.ExtentReporterNG.getReportObject;

public class Listeners extends SetUp implements ITestListener {
  ExtentReports  extent  = getReportObject();;
  ExtentTest test;
  ThreadLocal<ExtentTest> extentThreadLocal = new ThreadLocal<ExtentTest>(); //thread safe

  @Override
  public void onTestStart(ITestResult result) {
    test = extent.createTest(result.getMethod().getMethodName());
    extentThreadLocal.set(test); //1 unique thread id -> 1 test obj
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    extentThreadLocal.get().log(Status.PASS, "Test Passed");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    extentThreadLocal.get().fail(result.getThrowable());
    String testName = result.getMethod().getMethodName();

    try {
      driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
              .get(result.getInstance());
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      String path = getScreenshot(testName, driver);
      extentThreadLocal.get().addScreenCaptureFromPath(path, testName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void onFinish(ITestContext result) {
    extent.flush();
  }

}
