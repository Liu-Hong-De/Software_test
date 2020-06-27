package com.example.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class NoteBookTest {
    private AppiumDriver<AndroidElement> driver;

    @Before
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/src/main/java/apps/");
        File app = new File(appDir, "app-fdroid-debug.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Pixel");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appPackage", "com.orgzly");
        capabilities.setCapability("appActivity", ".android.ui.LauncherActivity");
        capabilities.setCapability("automationName", "UiAutomator1");
//        use to key the chinese
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

//    test create notebook
//    name：chinese
    @Test
    public void test_createNoteBookWithChinese() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("測試");
        driver.findElement(By.id("button1")).click();

        Assert.assertEquals("測試", driver.findElement(By.xpath(".//*[@text='測試']")).getText());
    }

//    test create notebook
//    name：english
    @Test
    public void test_createNoteBookWithEnglish() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("test");
        driver.findElement(By.id("button1")).click();

        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());
    }

//    test create notebook
//    name：empty
//    nothing occurred so I don't know how to assert
    @Test
    public void test_createNoteBookWithEmpty() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("");
        driver.findElement(By.id("button1")).click();
    }

//    test edit notebook
//    edit name：english
    @Test
    public void test_editNoteBookToEnglish() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();

        Thread.sleep(1000);
        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='new notebook']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='重新命名']")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("test");
        driver.findElement(By.id("button1")).click();

        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());
    }

//    test edit notebook
//    edit name：chinese
    @Test
    public void test_editNoteBookToChinese() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();

        Thread.sleep(1000);
        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='new notebook']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='重新命名']")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("編輯過的筆記本");
        driver.findElement(By.id("button1")).click();

        Assert.assertEquals("編輯過的筆記本", driver.findElement(By.xpath(".//*[@text='編輯過的筆記本']")).getText());
    }

//    test edit notebook
//    edit name：empty
//    the confirm button can not be pressed
    @Test
    public void test_editNoteBookToEmpty() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();

        Thread.sleep(1000);
        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='new notebook']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='重新命名']")).click();
        driver.findElement(By.id("name")).clear();

        Assert.assertTrue(!driver.findElement(By.id("button1")).isEnabled());
    }

//    test delete notebook
//    to verify the notebook is exist or not
    @Test
    public void test_delete() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();

        Thread.sleep(1000);
        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='new notebook']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='刪除']")).click();
        driver.findElement(By.id("button1")).click();

        try {
            driver.findElement(By.xpath(".//*[@text='new notebook']"));
            Assert.fail();
        } catch (Exception message) {
            System.out.println("delete success");
        }
    }
}
