package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;
import java.util.regex.Pattern;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            ARTICLE_BY_TYPE,
            CLOSE_AUTH_BUTTON;

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
                folder_name_xpath,
                "Cannot find folder by name " + name_of_folder,
                15
        );
    }

    public void waitForArticleLeftToAppearByTitle(String article_title){
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 3);
    }

    public void waitForArticleLeftToDisappearByTitle(String article_title){
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Save article still present with title " + article_title, 5);
    }

    public void swipeByArticleToDelete(String article_title){

        this.waitForArticleLeftToAppearByTitle(article_title);
        String article_xpath = getSaveArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find save article"
        );
        this.waitForArticleLeftToDisappearByTitle(article_xpath);

        if(Platform.getInstance().isIos()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
    }
    public void clickAuthCloseButton(){
        this.waitForElementAndClick(CLOSE_AUTH_BUTTON, "Cannot find auth close button", 5);
    }


    public int getAmountsOfElements(String locator){
        By by = this.getLocatorByString(locator);
        List elements = driver.findElements(by);
        return elements.size();
    }

    private By getLocatorByString(String locator_with_type){

        String[] explore_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = explore_locator[0];
        String locator = explore_locator[1];

        if (by_type.equals("xpath")){
            return By.xpath(locator);
        }
        else if (by_type.equals("id")){
            return By.id(locator);
        }
        else throw new IllegalArgumentException("Cannot get type of locator. Locator " + locator_with_type);
    }

    public void countOfArticles(){
            Assert.assertFalse("We have one or more Article on the page", getAmountsOfElements(ARTICLE_BY_TYPE)>1);
    }
}
