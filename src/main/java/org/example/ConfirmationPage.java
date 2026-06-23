package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends AbstractComponent{
  WebDriver driver;
  public ConfirmationPage( WebDriver _driver )
  {
    super(_driver);
    driver = _driver;
    PageFactory.initElements(driver, this);
  }

  @FindBy(tagName = "h1")
  WebElement heading;

  public String getHeader(){
    return heading.getText();
  }
}
