package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.HomePage;

import java.time.Duration;

public class SearchStepDefinitions {

    private WebDriver driver;
    private HomePage homePage;

    public SearchStepDefinitions() {
        this.driver = Hooks.driver; // Using shared WebDriver instance
        this.homePage = new HomePage(driver);
    }

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        driver.get("https://www.bugaboo.com/us-en");
        homePage.closeCookiePopupIfPresent();
    }

    @When("I click on the search icon")
    public void iClickOnTheSearchIcon() {
        homePage.clickSearch();
    }

    @Then("the search field should become enabled")
    public void theSearchFieldShouldBecomeEnabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Wait until search field is clickable (visible & enabled)
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(homePage.getSearchField()));

        Assert.assertTrue("Search field should be visible", searchInput.isDisplayed());
        Assert.assertTrue("Search field should be enabled", searchInput.isEnabled());

        System.out.println("✅ Search field is enabled and visible.");
    }

    @When("I type {string} in the search field")
    public void iTypeInTheSearchField(String searchQuery) {
        homePage.enterSearchQuery(searchQuery);
    }

    @Then("I should see search results related to {string}")
    public void iShouldSeeSearchResultsRelatedTo(String expectedText) {
        Assert.assertTrue("Expected search result not found!", homePage.isSearchResultTitleDisplayed(expectedText));
    }
}
