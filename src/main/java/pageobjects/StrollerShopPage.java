package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class StrollerShopPage {
    private WebDriver driver;

    // Locators
    private By productCards = By.cssSelector("div.product-tile__body");
    private By outOfStockIndicator = By.xpath(".//span[contains(@class, 'c-badge product-tile__tag') and contains(text(), 'Out of stock')]");
    private By viewMoreButton = By.cssSelector("span.product-tile__view-more");
    private By sortByDropdown = By.cssSelector("select#sort-order.select__input");
    private By sortByPopular = By.cssSelector("option[data-id*='popularity']");
    private By toasterMessage = By.xpath("//div[@class='c-toast c-toast--default']");

    public StrollerShopPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().window().maximize();
    }

    // **Method to sort by "Most Popular"**
    public void sortByMostPopular() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement sortDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(sortByDropdown));
        sortDropdownElement.click();

        WebElement sortByMostPopularOption = wait.until(ExpectedConditions.elementToBeClickable(sortByPopular));
        sortByMostPopularOption.click();
    }

    // **Method to verify "Most Popular" option is selected**
    public boolean isMostPopularOptionSelected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement selectedOption = wait.until(ExpectedConditions.visibilityOfElementLocated(sortByDropdown));
        return selectedOption.getAttribute("value").contains("popularity");
    }

    // **Method to find the first in-stock stroller and click 'View More'**
    public void checkFirstStrollerInStockAndClickViewMore() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<WebElement> productCardsList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productCards));

        for (WebElement productCard : productCardsList) {
            try {
                // **Check if the product is in stock**
                List<WebElement> outOfStockElements = productCard.findElements(outOfStockIndicator);
                if (outOfStockElements.isEmpty()) {  // ✅ Product is in stock

                    // **Check if "View More" button exists**
                    List<WebElement> viewMoreElements = productCard.findElements(viewMoreButton);
                    if (!viewMoreElements.isEmpty()) {  // ✅ "View More" button found
                        WebElement viewMore = viewMoreElements.get(0);

                        // **Scroll into view and click**
                        ((JavascriptExecutor) driver).executeScript(
                                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", viewMore);

                        // ✅ Handle sleep properly
                        try {
                            Thread.sleep(500); // Allow rendering
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("⚠️ Sleep interrupted: " + e.getMessage());
                        }

                        wait.until(ExpectedConditions.elementToBeClickable(viewMore)).click();
                        System.out.println("✅ Clicked 'View More' on an in-stock stroller.");
                        return;  // 🎯 Stop checking after finding a valid product
                    }
                }
            } catch (NoSuchElementException | ElementClickInterceptedException e) {
                System.out.println("⚠️ Issue checking product, moving to the next...");
            }
        }

        throw new RuntimeException("❌ No in-stock stroller with a 'View More' button found.");
    }
}
