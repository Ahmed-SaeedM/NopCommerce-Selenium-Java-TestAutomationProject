package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    private final By quantity = By.cssSelector("div.product-quantity>input");
    private final By continueShoppingBTN=By.name("continueshopping");
    private final By removeBTN =By.name("updatecart");
    private final By productsTable = By.cssSelector("div.table-wrapper");
    private final By cartMessage = By.className("no-data");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public String  getQuantity(){
        return driver.findElement(quantity).getAttribute("value");
    }
    public SearchPage clickContinueBTN(){
        driver.findElement(continueShoppingBTN).click();
        return new SearchPage(driver);
    }
    public void clickRemoveBTN(){
        driver.findElement(removeBTN).click();
    }
    public boolean isProductTableDisplayed(){
        return driver.findElement(productsTable).isDisplayed();
    }
    public String getCartMessage(){
        return driver.findElement(cartMessage).getText();
    }
}
