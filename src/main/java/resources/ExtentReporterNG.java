package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

  public static ExtentReports getReportObject(){
    String path = System.getProperty("user.dir") + "//report//index.html";

    //use this obj to make config to your report
    ExtentSparkReporter reporter = new ExtentSparkReporter(path);
    reporter.config().setReportName("Web Automation Report");
    reporter.config().setDocumentTitle("Test Results");

    //this report consolidate test execution
    ExtentReports extent = new  ExtentReports();
    extent.attachReporter(reporter);
    extent.setSystemInfo("Tester", "Minh");
    extent.createTest(path);

    return extent;
  }
}
