package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    private By cookiePopup = By.id("CybotCookiebotDialog");
    private By acceptFunctionalCookieButton = By.id("CybotCookiebotDialogBodyButtonDecline");

    // Locators for login page
    private By emailField = By.id("loginEmail");
    private By passwordField = By.id("loginPassword");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By loginErrorMessage = By.xpath("//button[@type='submit']"); // Example for error message, update as per actual page structure

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


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

    // Method to enter email and password
    public void enterCredentials(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    // Method to click on login button
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    // Method to verify if login was successful (error message check)
    public boolean isLoginErrorDisplayed() {
        try {
            return driver.findElement(loginErrorMessage).isDisplayed();
        } catch (Exception e) {
            return false; // If error message is not found, login was successful
        }
    }

    // Method to login
    public void login(String email, String password) {
        enterCredentials(email, password);
        clickLoginButton();
    }
}
