package lib.ui;
import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject{

    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    protected static String
            TITLE1,
            TITLE2,
            OPTION_BUTTON,
            OPTION_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            GET_RESULT_XPATH_FIRSR_ARTICLE_TPL;

    public WebElement waitForTitleElementFirstArticle(){
        return this.waitForElementPresent(TITLE1, "Cannot find article title on the page!", 15);
    }

    public WebElement waitForTitleElementSecondArticle(){
        return this.waitForElementPresent(TITLE2, "Cannot find article title on the page!", 15);
    }

    public String getFirstArticleTitle(){
        WebElement title_element = waitForTitleElementFirstArticle();
        if(Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else return title_element.getAttribute("name");
    }

    public String getSecondArticleTitle(){
        WebElement title_element = waitForTitleElementSecondArticle();
        if(Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else return title_element.getAttribute("name");
    }

    public void addFirstArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTION_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTION_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );
        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );
        this.waitForElementAndClear(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }


    private static String getResultXpathFirstArticle(String substring){
        return GET_RESULT_XPATH_FIRSR_ARTICLE_TPL.replace("{SUB}", substring);
    }

    public void addSecondArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(
                OPTION_BUTTON,
                "Cannot find button to open article options",
                5
        );
        this.waitForElementAndClick(
                OPTION_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        String search_result_xpath = getResultXpathFirstArticle(name_of_folder);
        this.waitForElementAndClick(
                search_result_xpath,
                "Cannot find saved article " + search_result_xpath,
                5
        );
    }

    public void closedArticle(){

        this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                15
        );
    }

    public void addArticlesToMySaved(){
        this.waitForElementAndClick(OPTION_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }
}
