package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailPage {
    private WebDriver driver;

    // Locator for the purchase button (span with data-cy-button attribute)
    private By purchaseButton = By.cssSelector("span[data-cy-button='addToCart']");

    // Updated modal locators:
    // The modal container that appears after clicking the purchase button.
    private By addedToCartModal = By.cssSelector("div.c-added-to-cart-modal");
    // The title inside the modal that should display the confirmation text.
    private By addedToCartModalTitle = By.cssSelector("div.c-added-to-cart-modal h5.modal__title.u-h5");

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Confirms that the product detail page is loaded by waiting for the purchase button to be visible.
     */
    public boolean isOnProductDetailPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(purchaseButton));
            return button.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Scrolls down until the purchase button is visible.
     * It scrolls the button into view (centered) then scrolls up slightly to ensure it isn’t obscured.
     */
    public void scrollDownToAddToCartButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(purchaseButton));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", button);
        // Scroll up a bit in case a fixed header is covering it
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");
        try {
            Thread.sleep(500); // Allow time for rendering/animations
        } catch (InterruptedException e) {
            // Ignore interruption
        }
    }

    /**
     * Clicks the purchase button.
     * First attempts a normal click; if that fails due to interception, it uses a JavaScript click.
     */
    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        try {
            button.click();
            System.out.println("Clicked on purchase button normally.");
        } catch (ElementClickInterceptedException e) {
            System.out.println("Click intercepted; attempting JavaScript click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        }
    }

    /**
     * Checks if the modal (which appears after clicking the purchase button) displays the expected text.
     * This method waits for the modal container to be visible and then retrieves the text of its title.
     */
    public boolean isTextInCartModal(String expectedText) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Wait until the modal appears
            WebElement modalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addedToCartModal));

            // Wait until the modal title is present
            WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(addedToCartModalTitle));

            // Get the modal title text
            String actualText = titleElement.getText().trim();
            System.out.println("Modal title text: " + actualText);

            // Return true if the expected text is contained in the modal text
            return actualText.toLowerCase().contains(expectedText.toLowerCase());
        } catch (Exception e) {
            System.out.println("Modal or expected text not found: " + e.getMessage());
            return false;
        }
    }

}
