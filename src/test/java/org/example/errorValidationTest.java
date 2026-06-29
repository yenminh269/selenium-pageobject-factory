package org.example;

import TestComponents.SetUp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class errorValidationTest extends SetUp {
  @Test(groups={"ErrorHandling"})
  public void errorValidate() throws IOException {
    LandingPage landingPage = launchApplication();

    landingPage.logIn("taesic@gmail.com","11626Oak!");

    Assert.assertEquals(landingPage.getErrorMsg(), "Incorrect email or password.");
  }

  @Test
  public void productValidation() throws IOException {
    LandingPage landingPage = launchApplication();
    ProductCatalog productCatalog = landingPage.logIn("taesicbadao6@gmail.com","11626Oak!");

    String item = "Zara Coat 3";

    productCatalog.getElements();
    productCatalog.getElementByName(item);
    productCatalog.addProductToCart(item);

    CartPage cartPage = productCatalog.goToCartPage();
    boolean isAdded = cartPage.isProductInCart(item);
    Assert.assertTrue(isAdded);
  }

}
