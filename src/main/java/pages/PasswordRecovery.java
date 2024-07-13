package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class PasswordRecovery {
    private final WebDriver driver;
    private final By recoveryMessage = By.className("tooltip");
    private final By recoveryEmailField = By.id("Email");
    private final By recoveryBTN = By.name("send-email");
    private final By notificationBar = By.cssSelector("p.content");

    public void setRecoveryEmailField(String email) {
        driver.findElement(recoveryEmailField).sendKeys(email);
    }

    public void clickRecoveryBTN() {
        driver.findElement(recoveryBTN).click();
    }

    public String getNotificationMessage() {

        return driver.findElement(notificationBar).getText();
    }

    public PasswordRecovery(WebDriver driver) {
        this.driver = driver;
    }
}
