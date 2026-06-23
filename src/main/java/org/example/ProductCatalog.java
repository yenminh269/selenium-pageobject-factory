package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {
  WebDriver driver;

  public ProductCatalog(WebDriver _driver )
  {
    super(_driver);
    driver = _driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(className="card-body")
  List<WebElement> elements;

  By addToCart = By.cssSelector("button:last-of-type");
  By toastMsg = By.id("toast-container");
  By loading = By.cssSelector(".ng-animating");

  public List<WebElement> getElements() {
    waitForElementToAppear(By.className("card-body"));
    return elements;
  }

  public WebElement getElementByName(String elementName) {
    return getElements().stream()
            .filter(e -> e.findElement(By.cssSelector("h5 b")).getText()
                    .equalsIgnoreCase(elementName))
            .findFirst().orElse(null);
  }

  public void addProductToCart(String elementName)
  {
    getElementByName(elementName).findElement(addToCart).click();
    waitForElementToAppear(toastMsg);
    waitForElementToDisAppear(loading);
  }

}
