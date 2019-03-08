import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
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
    //Ответ на вопрос: 1) У нас есть параметры настройки запуска теста: @Before и  @After. В @Before, перед каждым запуском
    // мы создаем отдельный объект "driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilitis);"
    // В @After, мы из него выходим(т.е. закрываем и больше не работаем) driver.quit();. При запуске последующих тестов будет
    //создан новый объект, в которм уже задано дефолтное состояние экрана: driver.rotate(ScreenOrientation.PORTRAIT);
    // 2 ой вариант, в  @After или @Before (Без разницы) добавить необходимое расположение экрана, например: driver.rotate(ScreenOrientation.PORTRAIT);
    //И при каждом запуске нового теста или его окончании, апимум будет менять положение экрана.

    public void tearDown() {
        driver.quit();
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Test
    public void testChangeOrientationOnSearchResults(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search Wikipedia input",
                5);

        String search_line = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                search_line,
                "Cannot find search input",
                5);
        waitForElementAndClick(
                By.xpath("//*[@resource-id = 'org.wikipedia:id/page_list_item_container']//*[@text = 'Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' toping searching by " + search_line,
                5);
        String title_before_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Article title have been change after screen rotation",
                title_before_rotation,
                title_after_rotation
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
}

