package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListPageObject extends MainPageObject {

    private static final String
            FOLDER_BY_NAME_TPL = "//*[@text = '{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TPL = "//*[@text = '{TITLE}']";

    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSaveArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    public MyListPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find find folder by name " + name_of_folder,
                2
        );
    }

    public void waitForArticleLeftToAppearByTitle(String article_title){
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Cannot find saved article by title " + article_title, 3);
    }

    public void waitForArticleLeftToDisappearByTitle(String article_title){
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Save article still present with title " + article_title, 5);
    }

    public void swipeByArticleToDelete(String article_title){

        this.waitForArticleLeftToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find save article"
        );
        this.waitForArticleLeftToDisappearByTitle(article_xpath);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getSaveArticleXpathByTitle(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring " + substring, 5);
    }
}
