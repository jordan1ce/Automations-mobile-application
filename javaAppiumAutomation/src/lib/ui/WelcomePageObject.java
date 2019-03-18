package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject{

    private static final String
        STEP_MORE_LEARN_LINK = "id:Learn more about Wikipedia",
        STEP_NEW_WAY_TO_EXPLORE_TEXT = "id:New ways to explore",
        STEP_ADD_OR_EDIT_PREFFERD_LANG_TEXT = "id:Add or edit preferred languages",
        STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_TEXT ="id:Learn more about data collected",
        NEXT_LINK = "id:Next",
        GET_STARTED_BUTTON = "id:Get started",
        SKIP = "id:Skip";


    public WelcomePageObject(AppiumDriver driver){
        super(driver);
    }

    public void waitForMoreLearnMoreLink(){

        this.waitForElementPresent(STEP_MORE_LEARN_LINK, "Cannot find 'Learn more about Wikipedia' link", 5);
    }


    public void waitForNewWayToExploreText(){

        this.waitForElementPresent(STEP_NEW_WAY_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore'", 5);
    }

    public void waitForAddOrEditPrefferedLangText(){

        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFFERD_LANG_TEXT, "Cannot find 'Add or edit preferred languages' link", 5);
    }

    public void waitForLearnMoreAboutDataCollectedText(){

        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_TEXT, "Cannot find 'Learn more about data collected' link", 5);
    }

    public void clickNextButton(){

        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link", 5);
    }

    public void clickGetStartedButton(){

        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get started' link", 5);
    }

    public  void clickSkip(){

        this.waitForElementAndClick(SKIP, "Cannot find and click 'Skip' link", 5);
    }
}

