package Tests;

import PageObjects.CartPage;
import PageObjects.ProductCatalog;
import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class ErrorValidationsTest extends BaseTest {
      @Test(groups = {"ErrorHandling"})
      public void LoginErrorValidation()  throws IOException, InterruptedException  {
          landingPage.loginApplication("amysantiago@brooklyn99.com", "AmySantia123#");
          Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
      }

    @Test
    public void ProductErrorValidation()  throws IOException, InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalog productCatalog = landingPage.loginApplication("janeeeedoe@gmail.com", "JaneDoe123");
        productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 333");
        Assert.assertFalse(match);
    }

   }
