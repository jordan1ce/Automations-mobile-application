package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
                TITLE1 = "id:org.wikipedia:id/view_page_title_text";
                OPTION_BUTTON = "xpath://android.widget.ImageView[@content-desc = 'More options']";
                OPTION_TO_MY_LIST_BUTTON = "xpath://*[@text = 'Add to reading list']";
                ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
                MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
                MY_LIST_OK_BUTTON = "xpath://android.widget.Button[@text = 'OK']";
                CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc = 'Navigate up']";
                GET_RESULT_XPATH_FIRSR_ARTICLE_TPL = "xpath://*[@text = '{SUB}']";
    }
    public AndroidArticlePageObject(AppiumDriver driver){
        super(driver);
    }
}
