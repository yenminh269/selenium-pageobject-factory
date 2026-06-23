package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
  WebDriver driver;
  WebDriverWait wait;

  public AbstractComponent(WebDriver _driver) {
    driver = _driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    PageFactory.initElements(driver, this);
  }

  @FindBy(css="button[routerlink='/dashboard/cart']")
  WebElement cartButton;

  @FindBy(css="button[routerlink='/dashboard/myorders']")
  WebElement ordersButton;

  public void waitForElementToAppear(By locator) {
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(locator)));
  }

  public void waitForElementToDisAppear(By locator) {
    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator)));
  }

  public CartPage goToCartPage(){
   cartButton.click();
    return new CartPage(driver);
  }

  public OrderPage goToOrderPage(){
    ordersButton.click();
    return new OrderPage(driver);
  }
}
