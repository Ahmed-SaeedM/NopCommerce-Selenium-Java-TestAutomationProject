package cart;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {
    @Test(groups = "E2E")
    public void testRemovingProductFromCartSuccessfully() {
        homePage.setSearchBoxText("htc");
        var search = homePage.clickSearchBTN();
        search.clickAddToCartFirstItem();
        search.clickCloseBTN();
        var cart = search.clickMiniCart();
        Assert.assertTrue(cart.isProductTableDisplayed());
        cart.clickRemoveBTN();
        Assert.assertEquals(cart.getCartMessage(),"Your Shopping Cart is empty!");
    }
}
