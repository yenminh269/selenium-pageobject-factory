package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractComponent {
  WebDriver driver;

  public OrderPage (WebDriver _driver )
  {
    super(_driver);
    driver = _driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(css=".ng-star-inserted td:nth-child(3)")
  List<WebElement> orders;

  public boolean verifyProductOrder(String product){
    return orders.stream().anyMatch(e -> e.getText().equalsIgnoreCase(product));
  }
}
