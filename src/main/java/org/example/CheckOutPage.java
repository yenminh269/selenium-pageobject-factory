package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponent{
  WebDriver driver;
  public CheckOutPage( WebDriver _driver )
  {
    super(_driver);
    driver = _driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(css = "input[placeholder='Select Country']")
  WebElement countrySelect;

  @FindBy(xpath = "(//button[contains(@class,'ta-item')])[4]")
  WebElement selectedCountry;

  @FindBy(css = "a[class*='action__submit']")
  WebElement submitButton;

  By result = By.cssSelector(".ta-results");

  public void pickCountry(String country) throws InterruptedException {
    countrySelect.sendKeys(country);
    waitForElementToAppear(result);
    Thread.sleep(9000);
    selectedCountry.click();
  }

  public ConfirmationPage submitOrder(){
    submitButton.click();
    return new ConfirmationPage(driver);
  }
}
