package TestComponents;

import org.apache.commons.io.FileUtils;
import org.example.LandingPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class SetUp {
  protected WebDriver driver;
  public WebDriver initalizeDriver() throws IOException {
    Properties prop = new Properties();
    FileInputStream input = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//data.properties");
    prop.load(input);

    String browser = (System.getProperty("browser")) != null ? System.getProperty("browser") : prop.getProperty("browser");
    browser = browser.trim().toLowerCase();

    System.out.println("Browser value = " + browser);

    if (browser.equalsIgnoreCase("chrome")) {
      driver = new ChromeDriver();
    }
    else if (browser.equalsIgnoreCase("chromeheadless")) {
      ChromeOptions options = new ChromeOptions();

      options.addArguments("--headless=new");
      driver = new ChromeDriver(options);

    }else if (browser.equalsIgnoreCase("firefox")) {
      driver = new FirefoxDriver();
    }else if (browser.equalsIgnoreCase("edge")) {
      driver = new EdgeDriver();
    }

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(9));
    driver.manage().window().maximize();

    return driver;
  }


  public String getScreenshot(String itemName,WebDriver _driver) throws IOException {
    TakesScreenshot ts = (TakesScreenshot) _driver;
    File src = ts.getScreenshotAs(OutputType.FILE);
    String path = System.getProperty("user.dir")+"//report//" + itemName + ".png";
    File dest = new File(path);
    FileUtils.copyFile(src, dest);
    return path;
  }

  @BeforeTest(alwaysRun = true)
  public LandingPage launchApplication() throws IOException {
    driver = initalizeDriver();
    LandingPage landingPage = new LandingPage(driver);
    landingPage.goTo("https://rahulshettyacademy.com/client");
    return landingPage;
  }

  @AfterTest(alwaysRun = true)
  public void closeApplication() {
    driver.quit();
  }
}
