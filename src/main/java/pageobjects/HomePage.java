package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;

    private By cookiePopup = By.id("CybotCookiebotDialog");
    private By acceptFunctionalCookieButton = By.id("CybotCookiebotDialogBodyButtonDecline");
    private By cartDiv = By.cssSelector("div.c-minicart");
    private By searchButton = By.xpath("//button[@class='header__right-search-icon']");
    private By searchField = By.xpath("//input[@id='navigation-search']");
    private By searchSuggestionsTitle = By.xpath("//h4[@class='search-suggestions__title']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Close cookie popup if present
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

    // Click on the cart icon and navigate to the cart page
    public void clickCart() {
        closeCookiePopupIfPresent();  // Ensure cookies are handled before clicking cart
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartDiv));
        cart.click();
        System.out.println("Cart clicked.");
    }

    // Click on search icon
    public void clickSearch() {
        closeCookiePopupIfPresent();  // Ensure cookies are handled before clicking search
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        search.click();
        System.out.println("Search clicked.");
    }

    // Enter search query
    public void enterSearchQuery(String query) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(searchField));
        searchInput.sendKeys(query);
        System.out.println("Entered search query: " + query);
    }

    // Get all search suggestion titles
    public List<WebElement> getSearchSuggestionsTitles() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Wait until at least one result appears
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(searchSuggestionsTitle));
    }

    // Verify if a specific search result title is displayed
    public boolean isSearchResultTitleDisplayed(String expectedText) {
        List<WebElement> titles = getSearchSuggestionsTitles();

        for (WebElement title : titles) {
            String actualText = title.getText().trim().toLowerCase();
            if (actualText.contains(expectedText.toLowerCase())) {
                System.out.println("Found search suggestion: " + actualText);
                return true;
            }
        }

        System.out.println("Expected search suggestion not found: " + expectedText);
        return false;
    }

    // Getter for search field (used in step definitions)
    public WebElement getSearchField() {
        return driver.findElement(searchField);
    }
}
