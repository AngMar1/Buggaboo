package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class StrollerShopPage {
    private WebDriver driver;
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

    // Method to sort by "Most Popular"
    public void sortByMostPopular() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement sortDropdownElement = wait.until(ExpectedConditions.elementToBeClickable(sortByDropdown));
        sortDropdownElement.click();

        WebElement sortByMostPopularOption = wait.until(ExpectedConditions.elementToBeClickable(sortByPopular));
        sortByMostPopularOption.click();
    }

    // Method to verify "Most Popular" option is selected
    public boolean isMostPopularOptionSelected() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement selectedOption = wait.until(ExpectedConditions.visibilityOfElementLocated(sortByDropdown));
        return selectedOption.getAttribute("value").contains("popularity");
    }

    // Method to check if the first stroller is in stock and click 'View More'
    public void checkFirstStrollerInStockAndClickViewMore() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstCard = wait.until(ExpectedConditions.visibilityOfElementLocated(productCards));

        // Scroll the first product card into view (centered)
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", firstCard);

        try {
            Thread.sleep(500); // Allow time for rendering
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> outOfStockElements = firstCard.findElements(outOfStockIndicator);
        if (!outOfStockElements.isEmpty()) {
            throw new RuntimeException("The first product is out of stock.");
        }

        WebElement viewMore = firstCard.findElement(viewMoreButton);
        wait.until(ExpectedConditions.elementToBeClickable(viewMore)).click();
        System.out.println("Clicked on 'View more' for the first in-stock product.");
    }
}

