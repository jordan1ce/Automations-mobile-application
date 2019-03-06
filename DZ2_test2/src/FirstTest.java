import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
    public void testFindSeveralElements() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5);
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find search input",
                5);

        //Считаю кол-во элементов на странице
        List<WebElement> prodsCount = driver.findElementsById("org.wikipedia:id/page_list_item_container");

        //Определяю, если элементов несколько, выполняется условие отмены поиска и сам факт отмены
        if (prodsCount.size() > 1) {
            waitForElementAndClick(
                    By.id("org.wikipedia:id/search_close_btn"),
                    "Cannot find X cancel search",
                    7);
            waitForElementNotPresent(
                    By.id("org.wikipedia:id/page_list_item_container"),
                    "result of search is present on the page",
                    1);
        }
        else {
            Assert.fail("We see one or less element on the page");
        }
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
}

