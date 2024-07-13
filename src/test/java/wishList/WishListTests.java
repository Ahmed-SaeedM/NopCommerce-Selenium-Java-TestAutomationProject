package wishList;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishListTests extends BaseTest {
    @Test
    public void testAddingItemTpWishListSuccessfully() {
       homePage.setSearchBoxText("htc");
       var search = homePage.clickSearchBTN();
       search.clickAddToWishListFirstItem();
       search.clickCloseBTN();
       var wishList = search.clickWishListLink();
        Assert.assertTrue(wishList.isTableDisplayed());
    }
}
