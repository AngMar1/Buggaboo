package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.LoginPage;
import static org.junit.Assert.*;

public class LoginStepDefinitions {

    // Uses the driver from Hooks
    private WebDriver getDriver() {
        return Hooks.driver;
    }

    // Initializes page objects as needed in the steps (or within each step method)
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        WebDriver driver = getDriver();
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
        driver.get("https://www.bugaboo.com/us-en/login/");

        // Initializes page object with the current driver
        loginPage = new LoginPage(driver);
        loginPage.closeCookiePopupIfPresent();
    }

    @When("I enter my credentials")
    public void i_enter_my_credentials() {
        // Uses the getDriver() method to ensure using the driver from Hooks
        WebDriver driver = getDriver();
        loginPage.enterCredentials("a.markova145@gmail.com", "G7!QgC8BGD@");

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", loginButton);
        loginButton.click();

        wait.until(ExpectedConditions.urlContains("/accounts"));
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);
        assertTrue("User was not redirected to the account page", currentUrl.contains("/accounts"));
    }

    @Then("I should be logged in successfully")
    public void i_should_be_logged_in_successfully() {
        WebDriver driver = getDriver();
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login: " + currentUrl);
        assertTrue("User was not redirected to the account page", currentUrl.contains("/accounts"));

        WebElement profileText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='card__title']")));
        assertTrue("Profile text is not visible after login", profileText.isDisplayed());
    }

    // Remove the @After hook here since Hooks.java already handles driver teardown
}
