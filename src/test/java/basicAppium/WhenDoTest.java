package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class WhenDoTest {
    AppiumDriver phone;
    @BeforeEach
    public void openApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","UPB2023_Android9");
        capabilities.setCapability("platformVersion","9.0");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName","uiautomator2");

        phone = new AndroidDriver(new URL(" http://192.168.50.144:4723/wd/hub"),capabilities);
        phone.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterEach
    public void closeApp(){
        phone.quit();
    }

    @Test
    public void verifyCreateTask() throws InterruptedException {
           String taskName ="UPBExample";
          //click +
           phone.findElement(By.id("com.vrproductiveapps.whendo:id/fab")).click();
            // set titulo
           phone.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextTitle")).sendKeys(taskName);
           // set notes
           phone.findElement(By.id("com.vrproductiveapps.whendo:id/noteTextNotes")).sendKeys("this is a note");
          // click save
          phone.findElement(By.id("com.vrproductiveapps.whendo:id/saveItem")).click();
          // verificar que la tarea esta creada
           Assertions.assertTrue( phone.findElements(By.xpath("//android.widget.TextView[@text='"+taskName+"']")).size() >= 1,"ERROR ! no se creo la tarea");


    }


}
