package stepDefinitions;

import TestComponents.SetUp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.*;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends SetUp {
  public LandingPage landingPage;
  public ProductCatalog productCatalog;
  public ConfirmationPage confirmationPage;

  //static
  @Given("I landed on Ecommerce Page")
  public void I_landed_on_Ecommerce_Page() throws IOException {
    landingPage = launchApplication();
  }

  //regex
  @Given("^Logged in with username (.+) and password (.+)$")
  public void Logged_in_with_username_and_password(String username, String password) {
    productCatalog = landingPage.logIn(username, password);

  }

  @When("^I add product (.+) to Cart$")
  public void I_add_product_to_Cart(String productName) {
    productCatalog.addProductToCart(productName);
  }

  @When("^Checkout (.+) and submit the order")
  public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
    CartPage cartPage = productCatalog.goToCartPage();
    boolean isAdded = cartPage.isProductInCart(productName);
    Assert.assertTrue(isAdded);

    CheckOutPage checkOutPage = cartPage.placeOrder();
    checkOutPage.pickCountry("United");

    confirmationPage = checkOutPage.submitOrder();
  }

  @Then("{string} Verify the message")
  public void Verify_the_message(String message) {
    String confirmMsg = confirmationPage.getHeader();
    Assert.assertEquals(message, confirmMsg);

  }

  @Then("{string} message is displayed")
  public void message_is_displayed(String message) {
    Assert.assertEquals(message,landingPage.getErrorMsg());
    driver.close();
  }
}
