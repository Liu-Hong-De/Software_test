package com.example.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Display {
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
        capabilities.setCapability("unicodeKeyboard", "True");
        capabilities.setCapability("resetKeyboard", "True");

        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        WebDriverWait wait = new WebDriverWait(driver, 10);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void test_Display_MoveUp() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note1");  // note
        driver.findElement(By.id("done")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note2");  // note
        driver.findElement(By.id("done")).click();

        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='note2']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='移動']")).click();
        driver.findElement(By.id("notes_action_move_up")).click();
        driver.findElement(By.id("action_mode_close_button")).click();

        Assert.assertEquals("note2", driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]")).getText());
        Assert.assertEquals("note1", driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[1]")).getText());
    }

    @Test
    public void test_Display_MoveDown() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note1");  // note
        driver.findElement(By.id("done")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note2");  // note
        driver.findElement(By.id("done")).click();

        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='note1']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='移動']")).click();
        driver.findElement(By.id("notes_action_move_down")).click();
        driver.findElement(By.id("action_mode_close_button")).click();

        Assert.assertEquals("note2", driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]")).getText());
        Assert.assertEquals("note1", driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[1]")).getText());
    }

    @Test
    public void test_Display_MoveRight() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note1");  // note
        driver.findElement(By.id("done")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note2");  // note
        driver.findElement(By.id("done")).click();

        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='note2']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='移動']")).click();
        driver.findElement(By.id("notes_action_move_right")).click();
        driver.findElement(By.id("action_mode_close_button")).click();

        Assert.assertEquals("note1", driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]")).getText());
        Assert.assertTrue(driver.findElement(By.xpath("//android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.FrameLayout")).isDisplayed());
        Assert.assertEquals("note2", driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[1]")).getText());
    }

    @Test
    public void test_Display_MoveLeft() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note1");  // note
        driver.findElement(By.id("done")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note2");  // note
        driver.findElement(By.id("done")).click();

        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='note2']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='移動']")).click();
        driver.findElement(By.id("notes_action_move_right")).click();
        driver.findElement(By.id("action_mode_close_button")).click();

        TouchAction ta1 = new TouchAction(driver);
        ta1.longPress(driver.findElement(By.xpath(".//*[@text='note2']")),2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='移動']")).click();
        driver.findElement(By.id("notes_action_move_left")).click();
        driver.findElement(By.id("action_mode_close_button")).click();

        Assert.assertEquals("note1", driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.TextView[1]")).getText());
        try {
            Assert.assertTrue(driver.findElement(By.xpath("//android.view.ViewGroup[2]/android.widget.LinearLayout/android.widget.FrameLayout")).isDisplayed());
            Assert.fail();
        } catch (Exception message) {
            System.out.println("move to left success");
        }
        Assert.assertEquals("note2", driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.TextView[1]")).getText());
    }

    @Test
    public void test_Display_MoveNoteBook() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();

        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("another notebook");
        driver.findElement(By.id("button1")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note1");  // note
        driver.findElement(By.id("done")).click();

        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='note1']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='移動筆記']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath(".//*[@text='another notebook']")).click();
        driver.findElement(By.id("dialog_refile_refile_here")).click();

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='another notebook']")).click();
        Thread.sleep(1000);
        Assert.assertEquals("note1", driver.findElement(By.xpath(".//*[@text='note1']")).getText());
    }

    @Test
    public void test_Display_Scheduled() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("note1");  // note
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"23 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='Next 3 days']")).click();
        Thread.sleep(1000);
        Assert.assertEquals("note1", driver.findElement(By.xpath(".//*[@text='note1']")).getText());
        Assert.assertEquals("new notebook", driver.findElement(By.xpath(".//*[@text='new notebook']")).getText());
        Assert.assertEquals("6月23日 週二",driver.findElement(By.xpath(".//*[@text='6月23日 週二']")).getText());
    }
}
