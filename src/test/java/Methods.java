import com.thoughtworks.gauge.Gauge;
import io.appium.java_client.*;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.TestResult;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.net.NetHooks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class Methods extends HookImpl{
    protected static Logger logger = LoggerFactory.getLogger(Methods.class);

    public static long startTime, endTime;
    public static TestResult testResult = new TestResult();
    
    // ELEMENT BULMA  :)
    public static MobileElement elementSelector(String type, String value) {
        MobileElement mobileElement = null;

        switch (type) {
            case "xpath":
                mobileElement = (MobileElement) appiumFluentWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
                break;

            case "id":
                mobileElement = (MobileElement) appiumFluentWait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
                break;

            case "classChain":
                mobileElement = (MobileElement) appiumFluentWait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.iOSClassChain(value)));
                break;

            default:
                logger.info("gonderilen type parametresi dogru degil");
        }
        return mobileElement;
    }

    // ANDROID İÇİN UYGULAMAYI BAŞLATIR.
    public static void OpeningActivityforAND(String appPackage, String appActivity) {
        Activity activity = new Activity(appPackage, appActivity);
        ((AndroidDriver) appiumDriver).startActivity(activity);

    }

    //İOS İÇİN UYGULAMAYI BAŞLATIR.
    public static void OpeningActivityforIOS(String BundleID) {
        HashMap<String, String> args = new HashMap<>();
        args.put("bundleId", BundleID);
        logger.info(args.toString());
        ((IOSDriver) appiumDriver)
                .executeScript("mobile: launchApp", args);
    }

    public static void Clickingonthecordinate(int x, int y) {

        TouchAction action = new TouchAction((MobileDriver) appiumDriver);
        TouchAction tp = new TouchAction(appiumDriver);
        tp
                .press(point(x, y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(300)))
                .press(point(x, y))
                .release()
                .perform();
    }




}