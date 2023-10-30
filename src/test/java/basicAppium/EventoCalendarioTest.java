package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class EventoCalendarioTest {
    private AppiumDriver phone;
    private static final String NAME_EVENT = "cumpleaños Pepe";

    @BeforeEach
    public void openApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","testerQA");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appPackage","com.simplemobiletools.calendar");
        capabilities.setCapability("appActivity","com.simplemobiletools.calendar.activities.MainActivity");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName","uiautomator2");

        phone = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        phone.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterEach
    public void closeApp() {
        phone.quit();
    }

    @Test
    public void verifyCreateEvent() throws InterruptedException {
        clickElement(By.xpath("//android.widget.TextView[@resource-id=\"com.simplemobiletools.calendar:id/dialog_trial_start_free_trial\"]"));
        clickElement(By.xpath("//android.view.View[@index=38]"));
        clickElement(By.xpath("//android.widget.ImageButton[@content-desc=\"New Event\"]"));
        clickElement(By.xpath("//android.widget.ImageButton[@content-desc=\"New Event\"]"));

        setEventTitle(NAME_EVENT);

        clickElement(By.xpath("//android.widget.TextView[@content-desc=\"Save\"]"));
        clickElement(By.xpath("//android.widget.Button[@resource-id=\"android:id/button1\"]"));

        // verification  expectedResult 12 = actualResult 12
        Thread.sleep(5000);
        String expectedResult = NAME_EVENT;
        String actualResult = getElementText(By.xpath("//android.widget.TextView[@resource-id=\"com.simplemobiletools.calendar:id/event_item_title\"]"));
        Assertions.assertEquals(expectedResult, actualResult, "ERROR No se pudo crear el evento");
    }

    private void clickElement(By locator) {
        phone.findElement(locator).click();
    }

    private void setEventTitle(String title) {
        phone.findElement(By.xpath("//android.widget.EditText[@resource-id=\"com.simplemobiletools.calendar:id/event_title\"]"))
                .sendKeys(title);
    }

    private String getElementText(By locator) {
        return phone.findElement(locator).getText();
    }
}
