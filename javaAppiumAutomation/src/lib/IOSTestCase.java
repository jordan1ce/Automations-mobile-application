package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class IOSTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumURL = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        DesiredCapabilities capabilitis = new DesiredCapabilities();
        capabilitis.setCapability("platformName", "iOS");
        capabilitis.setCapability("deviceName", "iPhone SE");
        capabilitis.setCapability("platformVersion", "11.0");
        capabilitis.setCapability("app", "/Users/dmitrii/Desktop/Курсы по автоматизации/Automations-mobile-application/javaAppiumAutomation/apks/Wikipedia.app");
        driver = new IOSDriver(new URL(AppiumURL), capabilitis);
        this.rotateScreenPortraint();
    }

    @Override
    protected void tearDown() throws Exception
    {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortraint(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backGroundUp(int seconds){
        driver.runAppInBackground(seconds);
    }

}
