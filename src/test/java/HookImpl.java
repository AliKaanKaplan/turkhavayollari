import com.saha.slnarch.common.helper.StringHelper;
import selector.Selector;
import selector.SelectorFactory;
import selector.SelectorType;
import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HookImpl {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected static AppiumDriver<MobileElement> appiumDriver;
    protected static FluentWait<AppiumDriver> appiumFluentWait;
    public static boolean localAndroid = true;//turn off android
    protected static Selector selector;
    public static String currentPhoneType;


    @BeforeScenario
    public void beforeScenario() throws IOException{

        if (StringHelper.isEmpty(System.getenv("key"))) {
            if (localAndroid) {//local için
                logger.info("Local Browser");
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "XIAOMI8");
                desiredCapabilities.setCapability(MobileCapabilityType.UDID, "dd785ff2");
                desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "9.0");
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
                                "com.android.mms");
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
                                "com.android.mms.ui.MmsTabActivity");
                desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
                desiredCapabilities.setCapability("unicodeKeyboard", false);
                desiredCapabilities.setCapability("resetKeyboard", false);
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                appiumDriver = new AndroidDriver(url, desiredCapabilities);

            } else {
                currentPhoneType ="sahaBT iPhone'u"; //local için
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities
                        .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                desiredCapabilities
                        .setCapability(MobileCapabilityType.UDID, "077137e595134542d4ae26e504bff87fcdbab33e");
                desiredCapabilities
                        .setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.mobileslideshow");
                desiredCapabilities
                        .setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
                desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1.2");
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                appiumDriver = new IOSDriver<>(url, desiredCapabilities);
            }
        }
        // TESTINIUM İÇİN Alt taraf çalışacak.
        else {
            String hubURL = "http://172.20.1.50:4444/wd/hub";
            DesiredCapabilities capabilities = new DesiredCapabilities();
            System.out.println("key:" + System.getenv("key"));
            System.out.println("platform" + System.getenv("platform"));
            System.out.println("version" + System.getenv("version"));
            currentPhoneType = System.getenv("browser");


            // Eğer platform androidse if içindekiler çalışır değil ise else tarafından ios başlar
            if (System.getenv("platform").equals("ANDROID")) {
                capabilities.setCapability("key", System.getenv("key"));
                capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                capabilities.setCapability("unicodeKeyboard", false);
                capabilities.setCapability("resetKeyboard", false);
                appiumDriver = new AndroidDriver(new URL(hubURL), capabilities);
                localAndroid = true;
                System.out.println(currentPhoneType);
            }
            else {
                capabilities
                        .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.1");
                capabilities
                        .setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.apple.MobileSMS");
                capabilities.setCapability("key", System.getenv("key"));
                appiumDriver = new IOSDriver(new URL(hubURL), capabilities);
                localAndroid = false;
            }
        }

        selector = SelectorFactory
                .createElementHelper(localAndroid ? SelectorType.ANDROID : SelectorType.IOS);
        appiumDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        appiumFluentWait = new FluentWait(appiumDriver);
        appiumFluentWait.withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(250, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);


    }

    @AfterScenario
    public void afterScenario() {
        appiumDriver.quit();

        /// Tarihi  ve lokasyonu yazdır
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDateTime now = LocalDateTime.now();
//            System.out.println(dtf.format(now));
//            GoogleExel.whriteWithAdress("E",String.valueOf(dtf.format(now)));
//            GoogleExel.whriteWithAdress("C",bolge.toString());

        /////////////////////////////////////
    }
}