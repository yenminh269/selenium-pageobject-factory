package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent
{
    WebDriver driver;
    public LandingPage( WebDriver _driver )
    {
        super(_driver);
        driver = _driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement login;

    @FindBy(id="toast-container")
    WebElement errorMsg;

    public ProductCatalog logIn(String _userEmail, String _userPassword){
        userEmail.sendKeys(_userEmail);
        userPassword.sendKeys(_userPassword);
        login.click();
        return new ProductCatalog(driver);
    }

    public void goTo(String url){
        driver.get(url);
    }

    public String getErrorMsg(){
        waitForElementToAppear(By.id("toast-container"));
        return errorMsg.getText();
    }
}
