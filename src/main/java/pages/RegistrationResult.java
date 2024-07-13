package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationResult {
    private WebDriver driver;
    private By resultMessage = By.cssSelector("div.result");
    private By continueBTN = By.xpath(".//a[@class='button-1 register-continue-button']");

    public RegistrationResult(WebDriver driver) {
        this.driver = driver;
    }

    public String getRegistrationResultMessage() {
        return driver.findElement(resultMessage).getText();
    }

    public HomePage clickContinueBTN() {
        driver.findElement(continueBTN).click();
        return new HomePage(driver);
    }
}

