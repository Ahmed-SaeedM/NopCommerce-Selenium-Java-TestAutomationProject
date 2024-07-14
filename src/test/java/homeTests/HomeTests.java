package homeTests;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HomeTests extends BaseTest {
    @Test(groups = "E2E")
    public void testCurrencyChange() {
        homePage.setCurrency("US Dollar");
        if (homePage.getSelectedOption().equals("US Dollar")) {
            List<String> priceTags = homePage.getPriceTags();
            for (String price : priceTags) {
                Assert.assertTrue(price.contains("$"));
            }
        }
        homePage.setCurrency("Euro");
        if (homePage.getSelectedOption().equals("Euro")) {
            List<String> priceTags = homePage.getPriceTags();
            for (String price : priceTags) {
                Assert.assertTrue(price.contains("€"));
            }
        }
    }

    @Test
    public void testNumberOFProductPresent() {
        List<String> products = homePage.getPriceTags();
        Assert.assertEquals(products.size(), 4, "Incorrect Number of Products shown in Home Page");
    }

    @Test
    public void testVerifyTopicBodyMessage() {
        List<String> bodyTags = homePage.getTopicBodyMessage();
        Assert.assertEquals(bodyTags.get(0), "Online shopping is the process consumers go through to purchase" +
                " products or services over the Internet. You can edit this in the admin site.");
        Assert.assertEquals(bodyTags.get(1), "If you have questions, see the Documentation, or post in the " +
                "Forums at nopCommerce.com");
    }

    @Test
    public void testAlertAppearsSuccessfullyWhileEmptySearch() {
        homePage.clickEmptySearchBTN();
        getWindowManager().waitUntilAlertForSeconds(3);
        System.out.println(homePage.getAlertText());
        homePage.acceptAlert();
    }

    @Test
    public void testLinksStatusCode200() {
        List<String> links = util.getAllPageLinks();
        util.verifyLinks(links);
    }

    @Test
    public void testImagesStatusCode200() {
        List<WebElement> images = util.getAllPageImages();
        for (WebElement image : images) {
            if (image != null) util.verifyImages(image);
        }
    }

    @Test
    public void testSocialMedia() {
        homePage.clickFaceLink();
        getWindowManager().switchToWindow("NopCommerce | Facebook");
        Assert.assertEquals(homePage.getTitle(), "NopCommerce | Facebook");
        closeTab();
        getWindowManager().switchToWindow("nopCommerce demo store");
    }

    @Test
    public void testSuccessfullyVoteWhileRegister() {
        var login = homePage.clickLoginLink();
        login.setEmailField(testData.getTestData("Email"));
        login.setPasswordField(testData.getTestData("Password"));
        var home = login.clickLoginBTN();
        if (home.isVotePollOptionsDisplayed()) {
            home.chooseVoteButton("good");
            home.clickVoteBTN();
            Assert.assertTrue(home.isVotePollResultsDisplayed());
        } else {
            System.out.println("You Have Voted Successfully With This Account Before");
        }
        //you should be registered before running this tc
        home.clickLogOutBTN();
    }

    @Test
    public void testValidVoteErrorMessage() {
        homePage.chooseVoteButton("excellent");
        homePage.clickVoteBTN();
        String errorMessage = homePage.getVoteErrorMessage();
        Assert.assertEquals(errorMessage, "Only registered users can vote.");
    }
}
