package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import pageobjects.*;

public class CartStepDefinitions {

    private WebDriver driver;
    private HomePage homePage;
    private StrollerShopPage strollerShopPage;
    private ProductDetailPage productDetailPage;

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        driver = Hooks.driver;
        driver.get("https://www.bugaboo.com/us-en");
        homePage = new HomePage(driver);
        homePage.closeCookiePopupIfPresent();
    }

    @And("I navigate to the strollers shop page")
    public void iNavigateToTheStrollersShopPage() {
        driver.get("https://www.bugaboo.com/us-en/strollers/shop-strollers/");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
        homePage.closeCookiePopupIfPresent();
        strollerShopPage = new StrollerShopPage(driver);
    }

    @When("I sort the strollers by most popular")
    public void iSortByMostPopular() {
        strollerShopPage.sortByMostPopular();
    }

    @Then("I should see most popular option on dropdown")
    public void iShouldSeeMostPopularOption() {
        Assert.assertTrue("Most Popular option not selected in dropdown", strollerShopPage.isMostPopularOptionSelected());
    }

    // Scenario: Add an in-stock stroller by viewing more and verify cart modal
    @When("I check if the first stroller is in stock and click {string}")
    public void iCheckIfTheFirstStrollerIsInStockAndClickViewMore(String viewMoreText) {
        strollerShopPage.checkFirstStrollerInStockAndClickViewMore();
    }

    @And("I should be on the product detail page")
    public void iShouldBeOnTheProductDetailPage() {
        productDetailPage = new ProductDetailPage(driver);
        Assert.assertTrue("Not on the product detail page", productDetailPage.isOnProductDetailPage());
    }

    @And("I scroll down and click {string}")
    public void iScrollDownAndClickAddToCart(String addToCartText) {
        productDetailPage.scrollDownToAddToCartButton();
        productDetailPage.clickAddToCart();
    }

    @Then("I should see {string} in the modal")
    public void iShouldSeeInTheModal(String expectedModalText) {
        Assert.assertTrue("Expected modal text not found", productDetailPage.isTextInCartModal(expectedModalText));
    }

    // Scenario: Close popup and click on cart
    @When("I click on the cart")
    public void iClickOnTheCart() {
        homePage.clickCart();
    }

    @Then("I should see the cart page")
    public void iShouldSeeTheCartPage() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue("Cart page should be visible", cartPage.isCartPageVisible());
    }
}
