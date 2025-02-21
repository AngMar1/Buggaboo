package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    WebDriver driver;

    // Cookie popup locators
    private By cookiePopup = By.id("CybotCookiebotDialog"); // Popup container
    private By acceptFunctionalCookieButton = By.id("CybotCookiebotDialogBodyButtonDecline"); // "Accept only functional"

    // Cart locator
    private By cartDiv = By.cssSelector("div.c-minicart");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Close the cookie popup by clicking "Accept only functional"
    public void closeCookiePopupIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(cookiePopup));

            if (popup.isDisplayed()) {
                WebElement acceptButton = driver.findElement(acceptFunctionalCookieButton);
                acceptButton.click();
                System.out.println("Cookie popup closed.");
            }
        } catch (Exception e) {
            System.out.println("Cookie popup not found or already closed.");
        }
    }

    // Click the cart after handling the popup
    public void clickCart() {
        closeCookiePopupIfPresent(); // Ensure popup is handled first

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartDiv));
        cart.click();
        System.out.println("Cart clicked.");
    }
}
