package registration;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationResult;
import pages.UserRegistrationPage;

import java.util.List;

public class RegistrationTests extends BaseTest {
    @Description("Verifying That a User Can Register Successfully With Valid Data + Passing All Fields")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = "E2E")
    public void testUserCanRegisterSuccessfully() {
        UserRegistrationPage user = homePage.clickRegistrationLink();
        user.clickFemaleBTN();
        user.setFirstNameField(testData.getTestData("FirstName"));
        user.setLastNameField(testData.getTestData("LastName"));
        user.sendOptionsToDropDown(testData.getTestData("Day"), "day");
        user.sendOptionsToDropDown(testData.getTestData("Month"), "month");
        user.sendOptionsToDropDown(testData.getTestData("Year"), "year");
        user.clickNewsLetterOption();
        user.setEmailFieldField(testData.getTestData("Email"));
        user.setCompanyNameField(testData.getTestData("CompanyName"));
        user.setPasswordField(testData.getTestData("Password"));
        user.setConfirmPasswordField(testData.getTestData("ConfirmPassword"));
        RegistrationResult resultPage = user.clickRegistrationBTN();
        Assert.assertEquals(resultPage.getRegistrationResultMessage(), "Your registration completed",
                "Incorrect Result Message");
        resultPage.clickContinueBTN();
        user.clickLogOutBTN();
    }

    @Description("Testing The Right Validation Message For Using and Existing Email")
    @Severity(SeverityLevel.NORMAL)
    @Test
    public void testUsingExistingEmail() {
        UserRegistrationPage user = homePage.clickRegistrationLink();
        user.clickMaleBTN();
        user.setFirstNameField(testData.getTestData("FirstName"));
        user.setLastNameField(testData.getTestData("LastName"));
        user.sendOptionsToDropDown(testData.getTestData("Day"), "day");
        user.sendOptionsToDropDown(testData.getTestData("Month"), "month");
        user.sendOptionsToDropDown(testData.getTestData("Year"), "year");
        user.setEmailFieldField(testData.getTestData("Email"));
        user.setCompanyNameField(testData.getTestData("CompanyName"));
        user.setPasswordField(testData.getTestData("Password"));
        user.setConfirmPasswordField(testData.getTestData("ConfirmPassword"));
        user.clickRegistrationBTN();
        Assert.assertEquals(user.getAfterRegisterMessage(), "The specified email already exists");
    }

    @Description("Testing The Validation Message Appearance For Required Fields")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testRequiredFields() {
        UserRegistrationPage user = homePage.clickRegistrationLink();
        user.clickRegistrationBTN();
        Assert.assertEquals(user.getFirstNameValidationMessage(), "First name is required.");
        Assert.assertEquals(user.getLastNameValidationMessage(), "Last name is required.");
        Assert.assertEquals(user.getEmailValidationMessage(), "Email is required.");
        Assert.assertEquals(user.getConfirmPasswordValidationMessage(), "Password is required.");
    }
    //TODO Implement @Description @Severity for all TestCases
    @Test
    public void testLinksStatusCode200() {
         homePage.clickRegistrationLink();
       List<String> links = util.getAllPageLinks();
       util.verifyLinks(links);
    }
    @Test
    public void testImagesStatusCode200() {
        var registration = homePage.clickRegistrationLink();
        List<WebElement> images=util.getAllPageImages();
        for (WebElement image : images){
            if (image != null) util.verifyImages(image);
        }
    }
}
