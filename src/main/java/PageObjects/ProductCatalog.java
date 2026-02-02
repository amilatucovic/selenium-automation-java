package PageObjects;
import AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css=".mb-3")
    List<WebElement> products;

    By productBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By animation = By.cssSelector(".ng-animating");
    By spinner = By.cssSelector(".ngx-spinner-overlay");

    public List<WebElement> getProductList() {
        waitForElementToAppear(productBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        WebElement prod = products.stream()
                .filter(product ->
                        product.findElement(By.cssSelector("b"))
                                .getText().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(productName+" is not found"));
        return prod;
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", prod);
        waitForElementToDisappear(animation);
        waitForElementToDisappear(spinner);

        WebElement addToCartButton = prod.findElement(addToCart);
        waitForElementToBeClickable(addToCartButton);

        try {
            addToCartButton.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].click();", addToCartButton);
        }

        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(animation);
        waitForElementToDisappear(spinner);
    }
}