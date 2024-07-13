package searchTests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests extends BaseTest {
    @Test
    public void testSearchResultsAndSearchTermRelationship() {
        String searchTerm = "Nike";
        homePage.setSearchBoxText(searchTerm);
        var searchPage = homePage.clickSearchBTN();
        List<WebElement> productItems = searchPage.searchItemsResult();
        for (WebElement result : productItems) {
            String productName = result.getAttribute("title");
            System.out.println(productName);
            Assert.assertTrue(productName.contains(searchTerm));
        }
    }
    @Test
    public void testSearchResultsAddedToCart() {
        String searchTerm = "Nike";
        homePage.setSearchBoxText(searchTerm);
        var searchPage = homePage.clickSearchBTN();
        List<WebElement> productItems = searchPage.searchItemsAddBTNResult();
        for (WebElement result : productItems) {
//      the click action has a wierd behaviour try it manually :D
          result.click();

        }
    }

    @Test(groups = "E2E")
    public void testAddingProductToCartSuccessfully() {
        String searchTerm = "htc";
        homePage.setSearchBoxText(searchTerm);
        var searchPage = homePage.clickSearchBTN();
        searchPage.clickAddToCartFirstItem();
        searchPage.clickCloseBTN();
        searchPage.hoverMiniCart();
        var cart = searchPage.clickMiniCart();
        Assert.assertEquals(cart.getQuantity(),"1");
    }
    @Test
    public void testAddingProductToCartTwiceSuccessfully() {
        String searchTerm = "htc";
        homePage.setSearchBoxText(searchTerm);
        var searchPage = homePage.clickSearchBTN();
        searchPage.clickAddToCartFirstItem();
        searchPage.clickCloseBTN();
        var cart = searchPage.clickMiniCart();
        Assert.assertEquals(cart.getQuantity(),"1");
        cart.clickContinueBTN();
        searchPage.clickAddToCartFirstItem();
        searchPage.clickCloseBTN();
        searchPage.clickMiniCart();
        Assert.assertEquals(cart.getQuantity(),"2");

    }
}
