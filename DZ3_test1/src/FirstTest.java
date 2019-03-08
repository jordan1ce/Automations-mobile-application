import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilitis = new DesiredCapabilities();

        capabilitis.setCapability("platformName", "Android");
        capabilitis.setCapability("deviceName", "AndroidTestDevice");
        capabilitis.setCapability("platformVersion", "6.0");
        capabilitis.setCapability("automationName", "Appium");
        capabilitis.setCapability("appPackage", "org.wikipedia");
        capabilitis.setCapability("appActivity", ".main.MainActivity");
        capabilitis.setCapability("app", "C:\\Users\\Dmitrii\\Desktop\\Моя\\Automations-mobile-application\\javaAppiumAutomation\\apks\\org.wikipedia.apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitis);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void saveTwoArticlesToMyList(){

        //Сохраняем первую статью
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Cannot find text 'Object-oriented programming language' on result screen",
                5);
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article 'Object-oriented programming language'",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find 'Got it' tip overlay",
                5
        );
        waitForElementAndCleare(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of articles folder",
                5
        );

        String name_of_folder = "Java is the best";

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        waitForElementAndCleare(
                By.xpath("//android.widget.Button[@text = 'OK']"),
                "Cannot press OK button",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article, cannot find X link",
                15
        );

        //Сохраняем вторую стать
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Java (software platform)']"),
                "Cannot find text 'Java (software platform)' on result screen",
                5);
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article 'Java (software platform)'",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "Cannot find button to open article options",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "Cannot find option to add article to reading list",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder + "']"),
                "Cannot find folder '" + name_of_folder + "'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc = 'Navigate up']"),
                "Cannot close article, cannot find X link",
                15
        );

        //Перехлдим в избранное (My List)
        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc = 'My lists']"),
                "Cannot find navigation button to My list",
                2
        );
        waitForElementAndClick(
                By.xpath("//*[@text = '" + name_of_folder + "']"),
                "Cannot find creator folder: " + name_of_folder,
                2
        );
        swipeElementToLeft(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot find save article 'Java (programming language)'"
        );
        waitForElementNotPresent(
                By.xpath("//*[@text = 'Java (programming language)']"),
                "Cannot delete article 'Java (programming language)'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@text = 'Java (software platform)']"),
                "Cannot find second article 'Java (software platform)'",
                15
        );
    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    private WebElement waitForElementAndCleare(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    private void swipeElementToLeft(By by, String error_message){

       WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y).waitAction(450)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}

