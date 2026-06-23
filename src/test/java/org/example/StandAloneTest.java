package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("taesicbadao6@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("11626Oak!");
        driver.findElement(By.id("login")).click();

        String item = "Zara Coat 3";

       List<WebElement> elements = driver.findElements(By.className("card-body"));

       WebElement selectedElement = elements.stream()
                .filter(e -> e.findElement(By.cssSelector("h5 b")).getText()
                        .equalsIgnoreCase(item))
                .findFirst().orElse(null);

       selectedElement.findElement(By.cssSelector("button:last-of-type")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();

        List<WebElement> cart = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean isAdded = cart.stream().anyMatch(e -> e.getText().equalsIgnoreCase(item));
        Assert.assertTrue(isAdded);

        driver.findElement(By.cssSelector(".totalRow button")).click();

        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("United");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));

        Thread.sleep(7000);

        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[4]")).click();

        driver.findElement(By.cssSelector("a[class*='action__submit']")).click();

        System.out.println(driver.findElement(By.tagName("h1")).getText());

        driver.quit();
    }
}
