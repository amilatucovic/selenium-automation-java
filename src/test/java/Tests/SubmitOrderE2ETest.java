package Tests;
import PageObjects.*;
import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class SubmitOrderE2ETest extends BaseTest {

      @Test
      public void submitOrder()  throws IOException  {
          String productName = "ZARA COAT 3";
          LandingPage landingPage = launchApplication();
          ProductCatalog productCatalog = landingPage.loginApplication("amysantiago@brooklyn99.com", "AmySantiago123#");
          List<WebElement> products = productCatalog.getProductList();
          productCatalog.addProductToCart(productName);
          CartPage cartPage = productCatalog.goToCartPage();
          Boolean match = cartPage.VerifyProductDisplay(productName);
          Assert.assertTrue(match);

          CheckoutPage checkoutPage = cartPage.goToCheckout();
          checkoutPage.SelectCountry("india");
          ConfirmationPage confirmationPage = checkoutPage.SubmitOrder();
          String confirmationMessage = confirmationPage.getConfirmationMessage();
          Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

      }






}
