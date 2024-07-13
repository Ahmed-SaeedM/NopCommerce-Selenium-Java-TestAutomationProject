package uilities;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class WindowManager {
    private final WebDriver driver;
    private final WebDriver.Navigation navigate;

    public WindowManager(WebDriver driver) {
        this.driver = driver;
        navigate =driver.navigate();
    }
    public void goBack(){
        navigate.back();
    }
    public void goForward(){
        navigate.forward();
    }
    public void refresh(){
        navigate.refresh();
    }
    public void goTo(String url){
        navigate.to(url);
    }
    public void waitUntilAlertForSeconds(int duration){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            System.out.println("Alert Didn't Show For Unknown Reason");
        }
    }

    public void switchToWindow(String windowTitle){
        Set<String> windows= driver.getWindowHandles();
        System.out.println("The Number of Tabs : " + windows.size());
        System.out.println("Window Handles");
        windows.forEach(System.out::println);
        for(String window : windows){
            System.out.println("Switching To Window: " + window);
            driver.switchTo().window(window);
            System.out.println("current Window title: " + driver.getTitle());
            if (windowTitle.equals(driver.getTitle()))
                break;
        }
    }
}
