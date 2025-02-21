package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    WebDriver driver;

    // Locators
    private By cartSummary = By.cssSelector(".cart-summary");
    private By checkoutButton = By.cssSelector(".checkout-button");
    private By emptyCartMessage = By.xpath("//*[contains(text(), 'Your shopping cart is empty')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Check if the cart is empty
    public boolean isCartEmpty() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement emptyMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
            return emptyMessage.isDisplayed();
        } catch (Exception e) {
            return false; // If the message is not found, cart is not empty
        }
    }

    // Wait for cart summary and check if displayed
    public boolean isCartSummaryDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement summary = wait.until(ExpectedConditions.visibilityOfElementLocated(cartSummary));
        return summary.isDisplayed();
    }

    // Wait for checkout button and check if displayed
    public boolean isCheckoutButtonDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));
        return button.isDisplayed();
    }
}
