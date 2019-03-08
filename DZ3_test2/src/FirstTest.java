import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

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
    public void testPresentOfTitle(){
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
                "Cannot find text 'Object-oriented programming language'",
                5);
        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find title article"
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

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeOutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
        return element.getAttribute(attribute);
    }

    private void assertElementPresent(By by, String error_message){
        String text_of_article = waitForElementAndGetAttribute(by, "text", error_message, 5);
        //задание понял так, если у элемента отсутствует text, т.е будет получено пустое значение "",
        // то мы можем считать, что заголовок статьи отсутствует.
        if (text_of_article.equals("")) {
            throw new AssertionError("Test is closed." + " " + error_message);
        }
    }
}

