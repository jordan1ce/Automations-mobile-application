package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            MY_LINK_LIST = "xpath://android.widget.FrameLayout[@content-desc = 'My lists']";

    public NavigationUI(AppiumDriver driver){
        super(driver);
    }

    public void clickMyLists(){

        this.waitForElementAndClick(
                MY_LINK_LIST,
                "Cannot find navigation button to My list",
                3
        );
    }
}
