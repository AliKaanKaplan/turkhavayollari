import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.junit.Assert;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.Duration;

public class StepImpl extends HookImpl {

    @Step("deneme123")
    public void implementation1() {
        System.out.println("deneme123");
    }

    @Step("deneme124")
    public void implementatation2(){
        System.out.println("deneme124");
    }
}