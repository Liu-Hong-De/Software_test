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

public class NoteTest {
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
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

/*    title：chinese
      tag：chinese
      state：TODO
      priority：A
      scheduled time：past
      deadline time：past
      property name：chinese
      property value：chinese
      content：chinese
 */
    @Test
    public void test_NewNote() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("TODO  #A  測試  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("內容測試", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("屬性名稱測試", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

/*    title：english
      tag：english
      state：NEXT
      priority：B
      scheduled time：future
      deadline time：future
      property name：english
      property value：english
      content：chinese
 */
    @Test
    public void test_NewNote2() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #B  test  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("內容測試", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("property value test", driver.findElement(By.id("value")).getText());
    }

    /*    title：special character
          tag：special character
          state：DONE
          priority：C
          scheduled time：future
          deadline time：past
          property name：special character
          property value：special character
          content：english
     */
    @Test
    public void test_NewNote3() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #C  ~!@#$%^&*()  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

    /*    title：empty
          tag：special character
          state：NEXT
          priority：A
          scheduled time：past
          deadline time：future
          property name：special character
          property value：special character
          content：special character
     */
    @Test
    public void test_NewNote4() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

/*    title：empty
      tag：english
      state：TODO
      priority：C
      scheduled time：future
      deadline time：past
      property name：english
      property value：chinese
      content：URL
 */
    @Test
    public void test_NewNote5() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("http://google.com");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

/*    title：special character
      tag：chinese
      state：NEXT
      priority：B
      scheduled time：past
      deadline time：future
      property name：chinese
      property value：english
      content：URL
 */
    @Test
    public void test_NewNote6() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("http://google.com");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #B  ~!@#$%^&*()  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("http://google.com", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("屬性名稱測試", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("property value test", driver.findElement(By.id("value")).getText());
    }

/*    title：english
      tag：chinese
      state：DONE
      priority：C
      scheduled time：past
      deadline time：future
      property name：chinese
      property value：chinese
      content：english
 */
    @Test
    public void test_NewNote7() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #C  test  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("屬性名稱測試", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

/*    title：chinese
      tag：special character
      state：DONE
      priority：B
      scheduled time：future
      deadline time：past
      property name：english
      property value：english
      content：special character
 */
    @Test
    public void test_NewNote8() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #B  測試  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("property value test", driver.findElement(By.id("value")).getText());
    }

/*    title：chinese
      tag：english
      state：NEXT
      priority：C
      scheduled time：past
      deadline time：past
      property name：special character
      property value：special character
      content：URL
 */
    @Test
    public void test_NewNote9() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("http://google.com");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #C  測試  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("http://google.com", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

/*    title：english
      tag：special character
      state：TODO
      priority：A
      scheduled time：future
      deadline time：past
      property name：special character
      property value：english
      content：URL
 */
    @Test
    public void test_NewNote10() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("http://google.com");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("TODO  #A  test  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("http://google.com", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("property value test", driver.findElement(By.id("value")).getText());
    }

/*    title：special character
      tag：english
      state：TODO
      priority：A
      scheduled time：past
      deadline time：future
      property name：english
      property value：special character
      content：english
 */
    @Test
    public void test_NewNote11() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("TODO  #A  ~!@#$%^&*()  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

/*    title：empty
      tag：chinese
      state：DONE
      priority：B
      scheduled time：future
      deadline time：past
      property name：special character
      property value：special character
      content：chinese
 */
    @Test
    public void test_NewNote12() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

/*    title：empty
      tag：english
      state：DONE
      priority：A
      scheduled time：future
      deadline time：past
      property name：chinese
      property value：english
      content：english
*/
    @Test
    public void test_NewNote13() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

/*    title：special character
      tag：special character
      state：TODO
      priority：B
      scheduled time：future
      deadline time：past
      property name：chinese
      property value：chinese
      content：special character
*/
    @Test
    public void test_NewNote14() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("TODO  #B  ~!@#$%^&*()  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("屬性名稱測試", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

/*    title：english
      tag：chinese
      state：NEXT
      priority：C
      scheduled time：future
      deadline time：past
      property name：english
      property value：english
      content：special character
*/
    @Test
    public void test_NewNote15() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #C  test  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("property value test", driver.findElement(By.id("value")).getText());
    }

/*    title：chinese
      tag：special character
      state：NEXT
      priority：C
      scheduled time：future
      deadline time：future
      property name：chinese
      property value：special character
      content：english
*/
    @Test
    public void test_NewNote16() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #C  測試  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("屬性名稱測試", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

/*    title：english
      tag：special character
      state：NEXT
      priority：C
      scheduled time：future
      deadline time：past
      property name：special character
      property value：chinese
      content：chinese
*/
    @Test
    public void test_NewNote17() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #C  test  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("內容測試", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

/*    title：english
      tag：english
      state：DONE
      priority：B
      scheduled time：future
      deadline time：past
      property name：special character
      property value：special character
      content：english
*/
    @Test
    public void test_NewNote18() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #B  test  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

/*    title：special character
      tag：english
      state：DONE
      priority：C
      scheduled time：future
      deadline time：past
      property name：special character
      property value：special character
      content：special character
*/
    @Test
    public void test_NewNote19() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #C  ~!@#$%^&*()  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

/*    title：special character
      tag：english
      state：DONE
      priority：C
      scheduled time：future
      deadline time：past
      property name：special character
      property value：special character
      content：URL
*/
    @Test
    public void test_NewNote20() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #C  ~!@#$%^&*()  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

/*    title：special character
      tag：english
      state：DONE
      priority：C
      scheduled time：future
      deadline time：past
      property name：special character
      property value：special character
      content：chinese
*/
    @Test
    public void test_NewNote21() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #C  ~!@#$%^&*()  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("內容測試", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

    /*    title：special character
          tag：english
          state：DONE
          priority：A
          scheduled time：past
          deadline time：future
          property name：special character
          property value：english
          content：URL
     */
    @Test
    public void test_EditNote() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("http://google.com");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #A  ~!@#$%^&*()  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("http://google.com", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("property value test", driver.findElement(By.id("value")).getText());
    }

    /*    title：empty
          tag：chinese
          state：TODO
          priority：C
          scheduled time：future
          deadline time：past
          property name：chinese
          property value：special character
          content：URL
     */
    @Test
    public void test_EditNote2() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("http://google.com");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

    /*    title：chinese
          tag：special character
          state：NEXT
          priority：B
          scheduled time：future
          deadline time：future
          property name：english
          property value：chinese
          content：chinese
     */
    @Test
    public void test_EditNote3() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #B  測試  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("內容測試", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

    /*    title：english
          tag：special character
          state：TODO
          priority：A
          scheduled time：past
          deadline time：past
          property name：english
          property value：chinese
          content：special character
     */
    @Test
    public void test_EditNote4() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        Thread.sleep(10000);

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("TODO  #A  test  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

/*    title：english
      tag：chinese
      state：DONE
      priority：B
      scheduled time：future
      deadline time：future
      property name：chinese
      property value：english
      content：english
 */
    @Test
    public void test_EditNote5() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #B  test  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("屬性名稱測試", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("property value test", driver.findElement(By.id("value")).getText());
    }

    /*    title：chinese
          tag：english
          state：TODO
          priority：C
          scheduled time：past
          deadline time：past
          property name：special character
          property value：special character
          content：english
     */
    @Test
    public void test_EditNote6() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if(!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("TODO  #C  測試  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

 /*    title：empty
       tag：english
       state：NEXT
       priority：B
       scheduled time：past
       deadline time：past
       property name：special character
       property value：english
       content：chinese
 */
    @Test
    public void test_EditNote7() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

   /*    title：special character
         tag：special character
         state：NEXT
         priority：C
         scheduled time：future
         deadline time：future
         property name：chinese
         property value：special character
         content：special character
    */
    @Test
    public void test_EditNote8() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #C  ~!@#$%^&*()  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("屬性名稱測試", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

/*    title：special character
      tag：chinese
      state：TODO
      priority：B
      scheduled time：past
      deadline time：future
      property name：english
      property value：chinese
      content：english
 */
    @Test
    public void test_EditNote9() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("TODO  #B  ~!@#$%^&*()  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

    /*    title：empty
         tag：special character
         state：DONE
         priority：A
         scheduled time：future
         deadline time：future
         property name：english
         property value：special character
         content：english
    */
    @Test
    public void test_EditNote10() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

/*    title：chinese
      tag：chinese
      state：DONE
      priority：A
      scheduled time：past
      deadline time：past
      property name：chinese
      property value：chinese
      content：chinese
 */
    @Test
    public void test_EditNote11() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #A  測試  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("內容測試", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("屬性名稱測試", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

    /*    title：english
          tag：english
          state：NEXT
          priority：C
          scheduled time：future
          deadline time：future
          property name：english
          property value：chinese
          content：URL
     */
    @Test
    public void test_EditNote12() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("http://google.com");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #C  test  tag test", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("http://google.com", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

    /*    title：english
          tag：chinese
          state：NEXT
          priority：A
          scheduled time：future
          deadline time：future
          property name：special character
          property value：special character
          content：chinese
     */
    @Test
    public void test_EditNote13() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #A  test  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("內容測試", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("value")).getText());
    }

    /*    title：chinese
          tag：special character
          state：DONE
          priority：C
          scheduled time：future
          deadline time：future
          property name：special character
          property value：english
          content：special character
     */
    @Test
    public void test_EditNote14() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("DONE  #C  測試  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("property value test", driver.findElement(By.id("value")).getText());
    }

    /*   title：empty
         tag：english
         state：TODO
         priority：B
         scheduled time：future
         deadline time：future
         property name：chinese
         property value：special character
         content：special character
    */
    @Test
    public void test_EditNote15() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag test");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("屬性名稱測試");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("~!@#$%^&*()")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("~!@#$%^&*()");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

    /*    title：special character
          tag：special character
          state：TODO
          priority：B
          scheduled time：future
          deadline time：past
          property name：special character
          property value：chinese
          content：chinese
     */
    @Test
    public void test_EditNote16() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"16 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("~!@#$%^&*()");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("TODO  #B  ~!@#$%^&*()  ~!@#$%^&*()", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月16日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("內容測試", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

    /*   title：empty
         tag：special character
         state：TODO
         priority：B
         scheduled time：future
         deadline time：future
         property name：english
         property value：english
         content：URL
    */
    @Test
    public void test_EditNote17() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("~!@#$%^&*()");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("property value test");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("http://google.com");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("property value test")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("property value test");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

    /*   title：empty
         tag：chinese
         state：NEXT
         priority：C
         scheduled time：future
         deadline time：future
         property name：english
         property value：chinese
         content：chinese
    */
    @Test
    public void test_EditNote18() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[3]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("內容測試");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("標題不能空白", driver.findElement(By.id("snackbar_text")).getText());
    }

    /*    title：chinese
          tag：chinese
          state：NEXT
          priority：B
          scheduled time：future
          deadline time：future
          property name：english
          property value：chinese
          content：special character
     */
    @Test
    public void test_EditNote19() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("~!@#$%^&*()");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #B  測試  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

    /*    title：chinese
          tag：chinese
          state：NEXT
          priority：B
          scheduled time：future
          deadline time：future
          property name：english
          property value：chinese
          content：english
     */
    @Test
    public void test_EditNote20() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("content text");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #B  測試  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("content text", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

    /*    title：chinese
          tag：chinese
          state：NEXT
          priority：B
          scheduled time：future
          deadline time：future
          property name：english
          property value：chinese
          content：URL
     */
    @Test
    public void test_EditNote21() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        driver.findElement(By.id("item_head_container")).click();
        driver.findElement(By.id("fragment_note_title")).clear();
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");
        driver.findElement(By.id("fragment_note_tags")).clear();
        driver.findElement(By.id("fragment_note_tags")).sendKeys("標籤測試");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[2]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("date_picker_button")).click();                        // 設置時間
        driver.findElement(By.id("prev")).click();
        driver.findElement(By.xpath("//android.view.View[@content-desc=\"30 6月 2020\"]")).click();  // 選擇日期
        driver.findElement(By.id("button1")).click();                                   // 確定
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("property name test");          // attribute name
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("屬性值測試");     // attribute value
        driver.findElement(By.id("body_edit")).clear();
        driver.findElement(By.id("body_edit")).sendKeys("http://google.com");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("屬性值測試")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("屬性值測試");
        }
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("NEXT  #B  測試  標籤測試", driver.findElement(By.id("item_head_title")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_scheduled_text")).getText());
        Assert.assertEquals("6月30日 週二", driver.findElement(By.id("item_head_deadline_text")).getText());
        Assert.assertEquals("http://google.com", driver.findElement(By.id("item_head_content")).getText());

        driver.findElement(By.id("item_head_container")).click();
        Assert.assertEquals("property name test", driver.findElement(By.id("name")).getText());
        Assert.assertEquals("屬性值測試", driver.findElement(By.id("value")).getText());
    }

    @Test
    public void test_delete() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();
        Thread.sleep(1000);

        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();   // +
        driver.findElement(By.id("fragment_note_title")).sendKeys("title");  // note
        driver.findElement(By.id("fragment_note_tags")).sendKeys("tag");    // tag
        driver.findElement(By.id("fragment_note_state_button")).click();                // state
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose state
        driver.findElement(By.id("fragment_note_priority_button")).click();             // priority
        driver.findElement(By.xpath("//android.widget.CheckedTextView[1]")).click();    // choose priority
        driver.findElement(By.id("fragment_note_scheduled_button")).click();            // scheduled time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("fragment_note_deadline_button")).click();             // deadline time
        driver.findElement(By.id("button1")).click();                                   // 設置
        driver.findElement(By.id("name")).sendKeys("name");          // attribute name
        driver.findElement(By.id("value")).sendKeys("value");     // attribute value
        driver.findElement(By.id("body_edit")).sendKeys("content");         // 筆記內文
        if (!driver.findElement(By.id("value")).getText().equals("value")) {
            driver.findElement(By.id("value")).clear();
            driver.findElement(By.id("value")).sendKeys("value");
        }
        driver.findElement(By.id("done")).click();

        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='TODO  #A  title  tag']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@text='刪除']")).click();
        driver.findElement(By.id("button1")).click();

        try {
            driver.findElement(By.xpath(".//*[@text='TODO  #A  title  tag']"));
            Assert.fail();
        } catch (Exception message) {
            System.out.println("delete success");
        }
    }
}
