package logintests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PasswordRecovery;

public class LoginTests extends BaseTest {
    @Test(groups = "E2E")
    public void testLoginSuccessfully() {
        LoginPage login = homePage.clickLoginLink();
        login.setEmailField(testData.getTestData("Email"));
        login.setPasswordField(testData.getTestData("Password"));
        login.clickLoginBTN();
        String text = homePage.getLogOutText();
        Assert.assertEquals(text,"Log out");
        homePage.clickLogOutBTN();
    }

    @Test
    public void testValidRecoveryMessage() {
        LoginPage login = homePage.clickLoginLink();
        PasswordRecovery recovery = login.clickForgetPasswordLink();
        recovery.setRecoveryEmailField(testData.getTestData("Email"));
        recovery.clickRecoveryBTN();
        Assert.assertEquals(recovery.getNotificationMessage(),
                "Email with instructions has been sent to you.");
    }

    @Test
    public void testUserLoginDisabilityWithoutValidCredentials() {
        var login   =homePage.clickLoginLink();
        login.setEmailField(testData.getTestData("InvalidEmail"));
        login.clickLoginBTN();
        String loginErrorMessage = login.getLoginErrorMessage();
        Assert.assertEquals(loginErrorMessage,"Login was unsuccessful. Please correct the errors and try again.\n" +
                "No customer account found");
    }

    @Test
    public void testValidErrorMessageForEmptyCredentialFields() {
        var login = homePage.clickLoginLink();
        login.clickLoginBTN();
        String emailErrorMessage=login.getEmailFieldErrorMessage();
        Assert.assertEquals(emailErrorMessage,"Please enter your email");
    }
}
