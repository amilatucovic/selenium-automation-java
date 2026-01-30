package PageObjects;

import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css=".totalRow button")
    WebElement checkoutElement;

    @FindBy(css=".cartSection h3")
    private List<WebElement> productTitles;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    By cartSection = By.cssSelector(".cartSection");

    public Boolean VerifyProductDisplay(String productName) {
        waitForElementToAppear(cartSection);
        Boolean match = productTitles.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage goToCheckout() {
        checkoutElement.click();
        return new CheckoutPage(driver);
    }
}
