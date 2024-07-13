package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By emailField = By.id("Email");
    private final By passwordField = By.id("Password");
    private final By rememberMeCheckBox = By.id("RememberMe");
    private final By forgetPasswordLink = By.linkText("Forgot password?");
    private final By loginBTN = By.cssSelector("button.button-1.login-button");
    private final By emailErrorField = By.id("Email-error");
    private final By loginErrorField =By.cssSelector("div.message-error.validation-summary-errors");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRememberMe() {
        driver.findElement(rememberMeCheckBox).click();
    }

    public String getEmailFieldErrorMessage() {
        return driver.findElement(emailErrorField).getText();
    }
    public String getLoginErrorMessage(){
        wait(loginErrorField);
        return driver.findElement(loginErrorField).getText();
    }

    public HomePage clickLoginBTN() {
        driver.findElement(loginBTN).click();
        return new HomePage(driver);
    }

    public PasswordRecovery clickForgetPasswordLink() {
        driver.findElement(forgetPasswordLink).click();
        return new PasswordRecovery(driver);
    }
private void wait(By locator){
    Wait<WebDriver> wait =new WebDriverWait(driver, Duration.ofSeconds(3));
    wait.until(d->driver.findElement(locator).isDisplayed());
}

}
