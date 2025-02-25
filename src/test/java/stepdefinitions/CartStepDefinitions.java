package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageobjects.HomePage;
import pageobjects.CartPage;
import pageobjects.StrollerShopPage;
import pageobjects.ProductDetailPage;

public class CartStepDefinitions {

    // No need to create a new driver here – we'll use the one from Hooks
    private WebDriver driver;
    private HomePage homePage;
    private StrollerShopPage strollerShopPage;
    private ProductDetailPage productDetailPage;

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        // Use the driver created in Hooks
        driver = Hooks.driver;
        driver.get("https://www.bugaboo.com/us-en");
        homePage = new HomePage(driver);
        homePage.closeCookiePopupIfPresent();
    }

    @When("I click on the cart")
    public void iClickOnTheCart() {
        homePage.clickCart();
    }

    @Then("I should see the cart page")
    public void iShouldSeeTheCartPage() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue("Cart page should be visible", cartPage.isCartPageVisible());
        // No driver.quit() here – it will be handled by the @After hook in Hooks.java
    }

    @And("I navigate to the strollers shop page")
    public void iNavigateToTheStrollersShopPage() {
        driver.get("https://www.bugaboo.com/us-en/strollers/shop-strollers/");
        // Close the cookie popup again if needed
        homePage.closeCookiePopupIfPresent();
        strollerShopPage = new StrollerShopPage(driver);
    }

    @When("I check if the first stroller is in stock and click {string}")
    public void iCheckIfTheFirstStrollerIsInStockAndClickViewMore(String viewMoreText) {
        // This method handles scrolling, checking stock, and clicking the View More button
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
}
