package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IosArticlePageObject extends ArticlePageObject {

    static {
        TITLE1 = "id:Java (programming language)";
        TITLE2 = "id:Java (software platform)";
        OPTION_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
    }

    public IosArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
