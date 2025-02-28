package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WishlistPage {
    private WebDriver driver;

    // Locator for the wishlist item
    private By wishlistItem = By.xpath("//div[@class='c-product-tile']");
    private By wishlistPageHeader = By.xpath("//h1[contains(text(),'Wishlist')]"); // Assuming this is part of the wishlist page

    public WishlistPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Navigate to the wishlist page.
     */
    public void goToWishlistPage() {
        driver.get("https://www.bugaboo.com/us-en/wishlist/"); // URL for the wishlist page
    }

    /**
     * Verifies if an item is present in the wishlist.
     * Waits for the wishlist item to become visible before checking.
     *
     * @return true if the item is found and visible, false otherwise.
     */
    public boolean isItemInWishlist() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Wait for the wishlist item to become visible
            WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistItem));

            // Check if the item is visible
            return item != null && item.isDisplayed();
        } catch (Exception e) {
            // If the item isn't found or not visible, return false
            return false;
        }
    }

    /**
     * Verify if we're on the wishlist page.
     */
    public boolean isOnWishlistPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(wishlistPageHeader));
            return header != null && header.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
