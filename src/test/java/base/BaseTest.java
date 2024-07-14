package base;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import uilities.JsonFileManager;
import uilities.UtilityMethods;
import uilities.WindowManager;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    private WebDriver driver;
    protected HomePage homePage;
    protected JsonFileManager testData;
    protected UtilityMethods util;

    @BeforeClass(groups = "E2E")
    public void setUp() {
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        goHome();
        testData = new JsonFileManager("src/test/java/data/testData.json");
        util = new UtilityMethods(driver);
    }

    @BeforeMethod(groups = "E2E")
    public void goHome() {
        driver.get("https://demo.nopcommerce.com/");
        homePage = new HomePage(driver);
    }

    @AfterMethod(groups = "E2E")
    public void takeScreenShotUponFailure(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus() || ITestResult.SKIP == result.getStatus()) {
            TakesScreenshot camera = (TakesScreenshot) driver;
            File screenShot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenShot, new File("resources/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
    }

    @AfterClass(groups = "E2E")
    public void tearDown() {
        driver.quit();
    }

    public WindowManager getWindowManager() {
        return new WindowManager(driver);
    }

    public void closeTab() {
        driver.close();
    }
}
