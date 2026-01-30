package Tests;
import PageObjects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class SubmitOrderE2ETest {

    public static void main(String[] args) {

        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        LandingPage landingPage = new LandingPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        landingPage.goTo();
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
        driver.close();



    }

}
