package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private final WebDriver driver;
    private final By wishList = By.cssSelector("span.wishlist-label");
    private final By customerCurrency = By.name("customerCurrency");
    private final By priceTag = By.className("prices");
    private final By searchBox = By.name("q");
    private final By searchBTN = By.className("search-box-button");
    private final By faceSocialBTN = By.linkText("Facebook");
    private final By topicMessageBody = By.cssSelector("div[class='topic-block'] p");
    private final By voteErrorMessage = By.id("block-poll-vote-error-1");
    private final By pollResult = By.cssSelector("ul.poll-results");
    private final By pollOptions = By.cssSelector("ul.poll-options");
    private final By logOutBTN = By.linkText("Log out");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getTopicBodyMessage() {
        List<WebElement> topicPTags = driver.findElements(topicMessageBody);
        return topicPTags.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickFaceLink() {
        driver.findElement(faceSocialBTN).click();
    }

    public void setCurrency(String option) {
        selectCurrencyDropDown().selectByVisibleText(option);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getSelectedOption() {
        WebElement selectedOption = selectCurrencyDropDown().getFirstSelectedOption();
        return selectedOption.getText();
    }

    public List<String> getPriceTags() {
        List<WebElement> priceTags = driver.findElements(priceTag);
        return priceTags.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void setSearchBoxText(String searchTerm) {
        driver.findElement(searchBox).sendKeys(searchTerm);
    }

    public void clickEmptySearchBTN() {
        driver.findElement(searchBTN).click();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public String getAlertText() {
        return driver.switchTo().alert().getText();
    }

    private void click(String link) {
        driver.findElement(By.linkText(link)).click();
    }

    private Select selectCurrencyDropDown() {
        return new Select(driver.findElement(customerCurrency));
    }

    public void chooseVoteButton(String inputLabel) {
        switch (inputLabel.toLowerCase()) {
            case "excellent" -> clickElementById("pollanswers-1");
            case "good" -> clickElementById("pollanswers-2");
            case "poor" -> clickElementById("pollanswers-3");
            case "very bad" -> clickElementById("pollanswers-4");
            default -> System.out.println("Invalid Choice");
        }
    }

    public void clickVoteBTN() {
        clickElementById("vote-poll-1");
    }

    public String getVoteErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(voteErrorMessage)));
        return driver.findElement(voteErrorMessage).getText();
    }

    public boolean isVotePollResultsDisplayed() {
        Wait<WebDriver> wait =
                new FluentWait<>(driver).withTimeout(Duration.ofSeconds(5))
                        .pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class)
                        .ignoring(TimeoutException.class);
        wait.until(
                d -> {
                    driver.findElement(pollResult).isDisplayed();
                    return true;
                });
        return driver.findElement(pollResult).isDisplayed();
    }

    public boolean isVotePollOptionsDisplayed() {
        boolean displayed = false;
        try {
            displayed = driver.findElement(pollOptions).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Poll Options Is Not Displayed As");
        }
        return displayed;
    }

    private void clickElementById(String text) {
        driver.findElement(By.id(text)).click();
    }

    public LoginPage clickLoginLink() {
        click("Log in");
        return new LoginPage(driver);
    }
    public void clickLogOutBTN() {
        driver.findElement(logOutBTN).click();
    }
    public String getLogOutText(){
        return driver.findElement(logOutBTN).getText();
    }

    public wishListPage clickWishListLink() {
        driver.findElement(wishList).click();
        return new wishListPage(driver);
    }

    public SearchPage clickSearchBTN() {
        driver.findElement(searchBTN).click();
        return new SearchPage(driver);
    }

    public UserRegistrationPage clickRegistrationLink() {
        click("Register");
        return new UserRegistrationPage(driver);
    }
}
