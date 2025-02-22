package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private By cartSummary = By.cssSelector(".cart-summary");
    private By checkoutButton = By.cssSelector(".checkout-button");
    private By emptyCartMessage = By.xpath("//*[contains(text(), 'Your shopping cart is empty')]");
    private By exploreStrollersButton = By.xpath("//span[@class='button_label' and text()='Explore strollers']");
    private By cartItemTitle = By.cssSelector(".cart-item-title");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isCartPageVisible() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(cartSummary),
                    ExpectedConditions.visibilityOfElementLocated(checkoutButton),
                    ExpectedConditions.visibilityOfElementLocated(emptyCartMessage),
                    ExpectedConditions.visibilityOfElementLocated(exploreStrollersButton)
            ));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductPresent(String productName) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(cartItemTitle));
            List<WebElement> items = driver.findElements(cartItemTitle);
            for (WebElement item : items) {
                if (item.getText().contains(productName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
