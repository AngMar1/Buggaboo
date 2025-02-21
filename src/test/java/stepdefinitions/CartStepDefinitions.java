package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageobjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CartStepDefinitions {
    WebDriver driver;
    HomePage homePage;

    @Given("I am on the homepage")
    public void iAmOnTheHomepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.bugaboo.com/us-en");
        homePage = new HomePage(driver);
        homePage.closeCookiePopupIfPresent(); // Close popup
    }

    @When("I click on the cart")
    public void iClickOnTheCart() {
        homePage.clickCart(); // Click the cart after handling the popup
        driver.quit(); // Close browser after clicking
    }
}
