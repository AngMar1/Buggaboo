package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.HomePage;
import pageobjects.CartPage;
import pageobjects.StrollerShopPage;
import pageobjects.ProductDetailPage;

public class CartStepDefinitions {
    private WebDriver driver;
    private HomePage homePage;
    private StrollerShopPage strollerShopPage;
    private ProductDetailPage productDetailPage;

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        driver = new ChromeDriver();
        // Set to a desktop viewport to ensure the desktop layout loads
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
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
        driver.quit();
    }

    // New scenario steps:

    @And("I navigate to the strollers shop page")
    public void iNavigateToTheStrollersShopPage() {
        // Navigate to the strollers shop page
        driver.get("https://www.bugaboo.com/us-en/strollers/shop-strollers/");
        // First, close the cookie popup on the strollers page
        homePage.closeCookiePopupIfPresent();
        strollerShopPage = new StrollerShopPage(driver);
    }

    @When("I check if the first stroller is in stock and click {string}")
    public void iCheckIfTheFirstStrollerIsInStockAndClickViewMore(String viewMoreText) {
        // This method scrolls the first product card into view, checks if it's in stock,
        // and if so clicks the "View more" button within that card.
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
        Assert.assertTrue("Expected modal text not found",
                productDetailPage.isTextInCartModal(expectedModalText));
        driver.quit();
    }
}
