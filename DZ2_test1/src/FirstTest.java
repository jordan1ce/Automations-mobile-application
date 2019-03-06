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
    public void foundText() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        waitForElementPresent(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Cannot find text 'Search…'",
                15);
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
}

