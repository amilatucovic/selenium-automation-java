package Tests;
import PageObjects.*;
import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

public class SubmitOrderE2ETest extends BaseTest {

    String productName = "ZARA COAT 3";
      @Test
      public void submitOrder()  throws IOException, InterruptedException  {

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

      @Test(dependsOnMethods = {"submitOrder"})
      public void OrderHistoryTest() {
          ProductCatalog productCatalog = landingPage.loginApplication("amysantiago@brooklyn99.com", "AmySantiago123#");
          OrderPage orderPage = productCatalog.goToOrdersPage();
          Assert.assertTrue(orderPage.VerifyOrderIsDisplayed(productName));

      }





}
