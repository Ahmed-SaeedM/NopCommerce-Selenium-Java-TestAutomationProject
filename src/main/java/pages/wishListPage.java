package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class wishListPage {
    private final WebDriver driver;
    private final By tableTag= By.tagName("tbody");
    public wishListPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isTableDisplayed(){
        return driver.findElement(tableTag).isDisplayed();
    }

}
