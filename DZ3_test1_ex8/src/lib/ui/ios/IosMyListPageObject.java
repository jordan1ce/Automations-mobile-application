package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class IosMyListPageObject extends MyListPageObject {

        static {
            ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeLink[contains(@name, '{TITLE}')]";
            CLOSE_AUTH_BUTTON = "id:places auth close";
            ARTICLE_BY_TYPE = "xpath://XCUIElementTypeCell[contains(@type, 'XCUIElementTypeCell')]";
        }
        public IosMyListPageObject(AppiumDriver driver){
            super(driver);
        }
}

