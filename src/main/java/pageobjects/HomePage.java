package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private By cookiePopup = By.id("CybotCookiebotDialog");
    private By acceptFunctionalCookieButton = By.id("CybotCookiebotDialogBodyButtonDecline");
    private By cartDiv = By.cssSelector("div.c-minicart");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void closeCookiePopupIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
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

    public void clickCart() {
        closeCookiePopupIfPresent();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartDiv));
        cart.click();
        System.out.println("Cart clicked.");
    }
}
