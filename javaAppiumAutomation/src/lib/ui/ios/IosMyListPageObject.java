package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IosMyListPageObject extends MyListPageObject{

        static {
            ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
            CLOSE_AUTH_BUTTON = "id:places auth close";
        }
    //XCUIElementTypeLink[@name="Java (programming language) Object-oriented programming language"]
        public IosMyListPageObject(AppiumDriver driver){
            super(driver);
        }
}

