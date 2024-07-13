package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private final WebDriver driver;
    private final By productItems = By.cssSelector("div.picture>a");
    private final By productsAddToCartBTN = By.cssSelector("button.product-box-add-to-cart-button");
    private final By productsAddToWishListBTN = By.cssSelector("button.button-2.add-to-wishlist-button");
    private final By cartLabel = By.cssSelector("span.cart-label");
    private final By miniCartBTN = By.cssSelector("a.ico-cart");
    private final By notificationCloseBTN = By.id("bar-notification");
    private final By closeBTN = By.xpath("/html/body/div[5]/div/span");
    private final By wishList=By.cssSelector("span.wishlist-label");


    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> searchItemsResult() {
        return driver.findElements(productItems);
//                items.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public List<WebElement> searchItemsAddBTNResult() {
        return driver.findElements(productsAddToCartBTN);
//                items.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    // فى مشكله فى add to cart
    public void clickAddToCartFirstItem() {
        driver.findElements(productsAddToCartBTN).get(0).click();
    }
    public void clickAddToWishListFirstItem() {
        driver.findElements(productsAddToWishListBTN).getFirst().click();
    }

    public void hoverMiniCart() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(cartLabel)).build().perform();
    }

    public CartPage clickMiniCart() {
        driver.findElement(miniCartBTN).click();
        return new CartPage(driver);
    }

    public void clickCloseBTN() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(notificationCloseBTN)));
        driver.findElement(closeBTN).click();

    }
    public wishListPage clickWishListLink() {
        driver.findElement(wishList).click();
        return new wishListPage(driver);
    }
}
