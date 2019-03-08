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
    public void firstTest() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find 'Search Wikipedia' input",
                5);

        String searchingWord = "Java";
        //Сделал доп. переменную для проверки поиска. Можно указать допустим "ipt" и посмотреть, в каких статья нет свопадений
        String wordInArticles = "Java";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                searchingWord,
                "Cannot find search input",
                5);

        // Определяем кол-во найденных элементов на страницы по айди: org.wikipedia:id/page_list_item_title
        List<WebElement> elements = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));

        //Создаем массив типа String, куда будем записывать все элементы параметра "text"
        String[] text = new String[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            text[i] = elements.get(i).getAttribute("text");
        }

        //Иследуем массив "text" на наличие слово "Java". Результат совпадений записываем в javaElements,
        // если есть совпадение. И в javaElementsNotFound, если их нет.
        int javaElements = 0;
        int javaElementsNotFound = 0;
        for (int i = 0; i < text.length; i++) {
            if (text[i].contains(wordInArticles)) {
                javaElements++;
            }
            else javaElementsNotFound++;
        }
        //Определяем, в каких статьях мы не нашли слово Java и записываем их в новый массив
        int position = 0;
        String [] textNotfound = new String[javaElementsNotFound];
        for (int i = 0; i < text.length; i++){
            if (text[i].contains(wordInArticles));
            else {
                textNotfound[position] = text[i];
                position++;
            }
        }

        //Создаю переменную, куда записываю строки без совпадений
        String strokaElementov = "";
        int positionOfArticles = 1;

        for (int i = 0; i < textNotfound.length; i++){
           if (i < textNotfound.length-1) strokaElementov = strokaElementov + positionOfArticles++ + " фраза: " + textNotfound[i] + ", ";
           else strokaElementov = strokaElementov + positionOfArticles++ + " фраза: " + textNotfound[i] + ".";
        }

        //Сравниваем кол-во найденных элементов (elements) с кол-во совпадений по Java (javaElements)
        Assert.assertFalse("The search world " + searchingWord + " " + "not find in articles: " + strokaElementov + "", elements.size() != javaElements);
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
}

