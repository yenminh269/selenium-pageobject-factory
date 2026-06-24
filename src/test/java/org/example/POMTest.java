package org.example;

import TestComponents.Retry;
import TestComponents.SetUp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static data.DataReader.getJsonData;

public class POMTest extends SetUp {
  LandingPage landingPage;
  String item = "Zara Coat 3";

  @Test(dataProvider = "getData", retryAnalyzer = Retry.class )
  public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
    landingPage = launchApplication();

    ProductCatalog productCatalog = landingPage.logIn(input.get("user"),input.get("password"));

    productCatalog.getElements();
    productCatalog.getElementByName(input.get("product"));
    productCatalog.addProductToCart(input.get("product"));

    CartPage cartPage = productCatalog.goToCartPage();
    boolean isAdded = cartPage.isProductInCart(input.get("product"));
    Assert.assertTrue(isAdded);

    CheckOutPage checkOutPage = cartPage.placeOrder();
    checkOutPage.pickCountry("United");

    ConfirmationPage confirmationPage = checkOutPage.submitOrder();

    System.out.println(confirmationPage.getHeader());
  }

  @Test(dependsOnMethods = {"submitOrder"})
  public void orderHistory() {
    ProductCatalog productCatalog = landingPage.logIn("taesicbadao6@gmail.com","11626Oak!");
    OrderPage orderPage = productCatalog.goToOrderPage();
    Assert.assertTrue(orderPage.verifyProductOrder(item));
  }

  @DataProvider
  public Object[][] getData() throws IOException {
//    HashMap<String, String> data = new HashMap<>();
//    data.put("user", "taesicbadao6@gmail.com");
//    data.put("password", "11626Oak!");
//    data.put("product",  "Zara Coat 3");
//
//    HashMap<String, String> data2 = new HashMap<>();
//    data2.put("user", "taesicbadao6@gmail.com");
//    data2.put("password", "11626Oak!");
//    data2.put("product",  "Adidas Original");

    List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"//src//test//java//data//purchaseOrder.json");

    return new Object[][] {{data.get(0)}, {data.get(1)}};
  }
}
