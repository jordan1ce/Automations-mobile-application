package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            OPTION_BUTTON = "//android.widget.ImageView[@content-desc = 'More options']",
            OPTION_TO_MY_LIST_BUTTON = "//*[@text = 'Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//android.widget.Button[@text = 'OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc = 'Navigate up']",
            GET_RESULT_XPATH_FIRSR_ARTICLE_TPL = "//*[@text = '{SUB}']";

    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on the page!", 15);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void addFirstArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTION_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndCleare(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndCleare(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button",
                5
        );
    }


    private static String getResultXpathFirstArticle(String substring){
        return GET_RESULT_XPATH_FIRSR_ARTICLE_TPL.replace("{SUB}", substring);
    }

    public void addSecondArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTION_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );

        String search_result_xpath = getResultXpathFirstArticle(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(search_result_xpath),
                "Cannot find saved article " + search_result_xpath,
                5
        );
    }

    public void closedArticle(){

        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                15
        );
    }
}
