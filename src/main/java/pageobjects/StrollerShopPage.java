package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class StrollerShopPage {
    private WebDriver driver;

    // Updated selectors based on your screenshots:
    // Product card container
    private By productCards = By.cssSelector("div.product-tile__body");
    // Product title within the card
    private By productTitle = By.cssSelector("h2.product-tile__title");
    // Out-of-stock indicator within a product card (if any element inside shows "Out of stock")
    private By outOfStockIndicator = By.xpath(".//*[contains(text(), 'Out of stock')]");
    // "View more" button inside the product card (the badge)
    private By viewMoreButton = By.cssSelector("span.product-tile__view-more");

    public StrollerShopPage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Scrolls the first product card into view using centered alignment.
     * Checks if that card is in stock (i.e. no out-of-stock indicator is found).
     * If in stock, finds and clicks the "View more" button within that card.
     */
    public void checkFirstStrollerInStockAndClickViewMore() {
        // Wait until at least one product card is visible.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstCard = wait.until(ExpectedConditions.visibilityOfElementLocated(productCards));

        // Scroll the first product card into view (centered)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", firstCard);
        try {
            Thread.sleep(500); // Allow brief time for the full card to render
        } catch (InterruptedException e) {
            // Ignore interruption
        }

        // Check if the first product card contains any out-of-stock indicator.
        List<WebElement> outOfStockElements = firstCard.findElements(outOfStockIndicator);
        if (!outOfStockElements.isEmpty()) {
            throw new RuntimeException("The first product is out of stock.");
        }

        // If in stock, click the "View more" button inside the card.
        WebElement viewMore = firstCard.findElement(viewMoreButton);
        viewMore.click();
        System.out.println("Clicked on 'View more' for the first in-stock product.");
    }
}