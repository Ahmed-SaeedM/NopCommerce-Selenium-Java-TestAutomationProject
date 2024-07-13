package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class UserRegistrationPage {

    private final WebDriver driver;

    private final By dayPicker = By.name("DateOfBirthDay");
    private final By monthPicker = By.name("DateOfBirthMonth");
    private final By yearPicker = By.name("DateOfBirthYear");
    private final By passwordValidationField = By.className("field-validation-error");
    private final By registrationResultMessage = By.cssSelector("div.message-error.validation-summary-errors li");
    private final By afterRegisterMessage = By.cssSelector("div.message-error.validation-summary-errors >ul>li");
    private final By logOutBTN = By.linkText("Log out");

    public UserRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickMaleBTN() {
        driver.findElement(idLocator("gender-male")).click();
    }

    public void clickFemaleBTN() {
        driver.findElement(idLocator("gender-female")).click();
    }

    public void setFirstNameField(String fName) {
        driver.findElement(idLocator("FirstName")).sendKeys(fName);
    }

    public void setEmailFieldField(String email) {
        driver.findElement(idLocator("Email")).sendKeys(email);
    }

    public void setCompanyNameField(String comName) {
        driver.findElement(idLocator("Company")).sendKeys(comName);
    }

    public void setLastNameField(String lName) {
        driver.findElement(idLocator("LastName")).sendKeys(lName);
    }

    public void clickNewsLetterOption() {
        driver.findElement(idLocator("Newsletter")).click();
    }

    public void setPasswordField(String password) {
        driver.findElement(idLocator("Password")).sendKeys(password);
    }

    public void setConfirmPasswordField(String confirmPassword) {
        driver.findElement(idLocator("ConfirmPassword")).sendKeys(confirmPassword);
    }

    public RegistrationResult clickRegistrationBTN() {
        driver.findElement(idLocator("register-button")).click();
        return new RegistrationResult(driver);
    }

    public String getFirstNameValidationMessage() {
        return driver.findElement(idLocator("FirstName-error")).getText();
    }

    public String getLastNameValidationMessage() {
        return driver.findElement(idLocator("LastName-error")).getText();
    }

    public String getEmailValidationMessage() {
        return driver.findElement(idLocator("Email-error")).getText();
    }

    public String getConfirmPasswordValidationMessage() {
        return driver.findElement(idLocator("ConfirmPassword-error")).getText();
    }

    public String getPasswordValidationMessage() {
        return driver.findElement(passwordValidationField).getText();
    }

    public String getRegistrationResultMessage() {
        return driver.findElement(registrationResultMessage).getText();
    }

    public String getAfterRegisterMessage() {
        return driver.findElement(afterRegisterMessage).getText();
    }

    public void clickLogOutBTN() {
        driver.findElement(logOutBTN).click();
    }

    public void sendOptionsToDropDown(String input, String pickerOption) {
        switch (pickerOption.toLowerCase()) {
            case "day" -> selectDropDown(dayPicker).selectByVisibleText(input);
            case "month" -> selectDropDown(monthPicker).selectByVisibleText(input);
            case "year" -> selectDropDown(yearPicker).selectByVisibleText(input);
            default -> System.out.println("Please Enter A Valid Option Between day,month and year");
        }
    }

    private Select selectDropDown(By byLocator) {
        return new Select(driver.findElement(byLocator));
    }

    private By idLocator(String id) {
        return By.id(id);
    }
}
