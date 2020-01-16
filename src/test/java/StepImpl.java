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

    @Step("<appPackage> and <appActivity> Application opens")
    public void ApplicationOpensIOS(String appPackage,String appActivity) {

        Methods.OpeningActivityforAND(appPackage,appActivity);
    }

    @Step("<BundleID> Application opens")
    public void ApplicationOpensIOS(String BundleID){
        Methods.OpeningActivityforIOS(BundleID);
        //X500 , 1230 y
    }

    @Step("<sure> saniye bekle")
    public void Waiting(int sure) {

        try {
            Thread.sleep(sure * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("xpathli butonuna basilir")
    public void clicktoxpath(){
        Methods.Biletals_button();

    }


    /*  KORDINAT TIKLAMA BUTONU CALISMIYOR !!!!!!!!!!!!!!!!!!!
    @Step("<x> ve <y> kordinatlarına tikla")
    public void Clickingoncordinate(int x,int y){
        Methods.Clickingonthecordinate(x,y);
    }*/
}
/*
Not:Aşağıdaki senaryoda belirtilen uçuş yönü,yolcu bilgileri ve cep telefonu bilgileri alanını
excelden okuyup test senaryosunda kullanılması beklenmektedir.

Senaryo 1
• Thy uygulaması açılır
• Bilet al butonuna tıklanır.
• Tek yön uçuş seçilir
• Yön bilgisi olarak SAW to ESB Seçilir ---------------------------------- EXCELLLL
• Tarih bilgisi günün tarihinden 2 gün sonraya seçilir ---- BUGÜNÜN TARİHİ JAVA İLE ALINMALI. HOOKIMPL DOSYASININ EN ALTINDA VAR.
• Tamam butonuna tıklanır
• Yetişkin bilgisi 2 olarak seçilir
• Uçuş ara butonuna tıklanır.
• Uçuşların listelendiği kontrol edilir
• Ekonomik uçuş seçilir
• Devam butonuna tıklanır.
• Yolcu ekle alanı doldurulur ve devam butonuna tıklanır. ---------------- EXCELLL
• Devam butonuna tıklanır
• Cep telefonu bilgisi girilir. ----------------------------------------- EXCELLLL
• Devam butonuna tıklanır
• Koltuk Seçimi ile devam et butonuna tıklanır
• Uçuş seçilir
• Isim alanına tıklanır
• Koltuk seç butonuna tıklanır
• Rasgele boş koltuk seçilir
• Devam butonuna tıklanır
• Kredi kartı ile öde butonuna tıklanır
• Kart bilgisi alanları doldurulur ve test sonlandırılır
*
* */