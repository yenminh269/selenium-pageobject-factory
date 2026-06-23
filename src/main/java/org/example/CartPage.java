package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent{
  WebDriver driver;

  public CartPage (WebDriver _driver )
  {
    super(_driver);
    driver = _driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(css=".cartSection h3")
  List<WebElement> cart;

  @FindBy(css=".totalRow button")
  WebElement placeOrderButton;

  public boolean isProductInCart(String item){
    return cart.stream().anyMatch(e -> e.getText().equalsIgnoreCase(item));
  }

  public CheckOutPage placeOrder(){
    placeOrderButton.click();
    return new CheckOutPage(driver);
  }

}
