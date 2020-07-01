package com.example.test;

import com.google.common.collect.ImmutableMap;

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

public class SerachTest {
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

//    test search note with chinese
    @Test
    public void test_SearchChinese() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
//        create notebook
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();

        Thread.sleep(1000);
//        create note with chinese title in the new notebook
        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_note_title")).sendKeys("測試");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='筆記本']")).click();
//        search
        driver.findElement(By.id("activity_action_search")).click();
        driver.findElement(By.id("search_src_text")).sendKeys("測試");
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

        Assert.assertEquals("測試", driver.findElement(By.xpath(".//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[1][@text='測試']")).getText());
        Assert.assertEquals("new notebook", driver.findElement(By.xpath(".//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[2][@text='new notebook']")).getText());
    }

//    test search note with english
    @Test
    public void test_SearchEnglish() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
//        create notebook
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();

        Thread.sleep(1000);
//        create note with english title in the new notebook
        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_note_title")).sendKeys("test");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='筆記本']")).click();
//        search
        driver.findElement(By.id("activity_action_search")).click();
        driver.findElement(By.id("search_src_text")).sendKeys("test");
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

        Assert.assertEquals("test", driver.findElement(By.xpath(".//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[1][@text='test']")).getText());
        Assert.assertEquals("new notebook", driver.findElement(By.xpath(".//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[2][@text='new notebook']")).getText());
    }

    //    test search note with special character
    @Test
    public void test_SearchSpecialCharacter() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
//        create notebook
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("dialog_input")).sendKeys("new notebook");
        driver.findElement(By.id("button1")).click();

        Thread.sleep(1000);
//        create note with english title in the new notebook
        driver.findElement(By.xpath(".//*[@text='new notebook']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_note_title")).sendKeys("~!@#$%^&*()");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='筆記本']")).click();
//        search
        driver.findElement(By.id("activity_action_search")).click();
        driver.findElement(By.id("search_src_text")).sendKeys("~!@#$%^&*()");
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.xpath(".//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[1][@text='~!@#$%^&*()']")).getText());
        Assert.assertEquals("new notebook", driver.findElement(By.xpath(".//androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup/android.widget.TextView[2][@text='new notebook']")).getText());
    }

//    test search note with empty
//    when the search title is empty , the search box is also displayed after searching
    @Test
    public void test_SearchEmpty() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();

//        serrch
        driver.findElement(By.id("activity_action_search")).click();
        driver.findElement(By.id("search_src_text")).sendKeys("");
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

        Assert.assertTrue(driver.findElement(By.id("search_src_text")).isDisplayed());
    }

//    test search note with the note which is not exist
    @Test
    public void test_SearchNotExist() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();

//        search
        driver.findElement(By.id("activity_action_search")).click();
        driver.findElement(By.id("search_src_text")).sendKeys("不存在");
        driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));

        Assert.assertEquals("您的搜尋沒有任何結果", driver.findElement(By.xpath(".//*[@text='您的搜尋沒有任何結果']")).getText());
    }

//    test create search condition
//    Name：中文，Query：True
    @Test
    public void test_Search_Chinese_TrueQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
//        create search condition
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("測試");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys(".it.done");
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("測試", driver.findElement(By.xpath(".//*[@text='測試']")).getText());
        Assert.assertEquals(".it.done", driver.findElement(By.xpath(".//*[@text='.it.done']")).getText());
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("測試", driver.findElement(By.xpath(".//*[@text='測試']")).getText());
    }

//    test create search condition
//    Name：中文，Query：False
//    當查詢為錯誤語法時，搜尋必沒有任何結果
    @Test
    public void test_Search_Chinese_FalseQuery() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
//        create search condition
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("測試");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("false query");
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("測試", driver.findElement(By.xpath(".//*[@text='測試']")).getText());
        Assert.assertEquals("false query", driver.findElement(By.xpath(".//*[@text='false query']")).getText());
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("測試", driver.findElement(By.xpath(".//*[@text='測試']")).getText());
        driver.findElement(By.xpath(".//*[@text='測試']")).click();
        Assert.assertEquals("您的搜尋沒有任何結果", driver.findElement(By.xpath(".//*[@text='您的搜尋沒有任何結果']")).getText());
    }

//    test create search condition
//    Name：中文，Query：empty
//    query can not be empty
    @Test
    public void test_Search_Chinese_EmptyQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
//        create search condition
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("測試");
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("不能留空", driver.findElement(By.id("textinput_error")).getText());
    }

//    test create notebook condition
//    Name：英文，Query：True
    @Test
    public void test_Search_English_TrueQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
//        create search condition
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("test");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys(".it.done");
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());
        Assert.assertEquals(".it.done", driver.findElement(By.xpath(".//*[@text='.it.done']")).getText());
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());
    }

//    test create search condition
//    Name：英文，Query：False
//    當查詢為錯誤語法時，搜尋必沒有任何結果
    @Test
    public void test_Search_English_FalseQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
//        create search condition
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("test");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("false query");
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());
        Assert.assertEquals("false query", driver.findElement(By.xpath(".//*[@text='false query']")).getText());
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());
        driver.findElement(By.xpath(".//*[@text='test']")).click();
        Assert.assertEquals("您的搜尋沒有任何結果", driver.findElement(By.xpath(".//*[@text='您的搜尋沒有任何結果']")).getText());
    }

//    test create search condition
//    Name：英文，Query：empty
    @Test
    public void test_Search_English_EmptyQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("test");
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.id("textinput_error")).getText());
    }

//    test create notebook condition
//    Name：特殊符號，Query：True
    @Test
    public void test_Search_SpecialCharacter_TrueQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
//        create search condition
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("~!@#$%^&*()");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys(".it.done");
        driver.findElement(By.id("done")).click();

        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.xpath(".//*[@text='~!@#$%^&*()']")).getText());
        Assert.assertEquals(".it.done", driver.findElement(By.xpath(".//*[@text='.it.done']")).getText());
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.xpath(".//*[@text='~!@#$%^&*()']")).getText());
    }

//    test create search condition
//    Name：特殊符號，Query：False
//    當查詢為錯誤語法時，搜尋必沒有任何結果
    @Test
    public void test_Search_SpecialCharacter_FalseQuery() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
//        create search condition
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("~!@#$%^&*()");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("false query");
        driver.findElement(By.id("done")).click();

        Thread.sleep(1000);
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.xpath(".//*[@text='~!@#$%^&*()']")).getText());
        Assert.assertEquals("false query", driver.findElement(By.xpath(".//*[@text='false query']")).getText());
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("~!@#$%^&*()", driver.findElement(By.xpath(".//*[@text='~!@#$%^&*()']")).getText());
        driver.findElement(By.xpath(".//*[@text='~!@#$%^&*()']")).click();
        Assert.assertEquals("您的搜尋沒有任何結果", driver.findElement(By.xpath(".//*[@text='您的搜尋沒有任何結果']")).getText());
    }

//    test create search condition
//    Name：特殊符號，Query：empty
    @Test
    public void test_Search_SpecialCharacter_EmptyQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("~!@#$%^&*()");
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.id("textinput_error")).getText());
    }

    //    Name：empty，Query：True
    @Test
    public void test_Search_Empty_TrueQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys(".it.done");
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.id("textinput_error")).getText());
    }

    //    Name：empty，Query：False
    @Test
    public void test_Search_Empty_FalseQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("false query");
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.id("textinput_error")).getText());
    }

    //    Name：empty，Query：empty
    @Test
    public void test_Search_Empty_EmptyQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")).getText());
        Assert.assertEquals("不能留空", driver.findElement(By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")).getText());
    }

//    edit Name：中文，Query：True
    @Test
    public void test_edit_Chinese_TrueQuery() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath(".//*[@text='name']")).click();
        driver.findElement(By.id("fragment_saved_search_name")).clear();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("測試");
        driver.findElement(By.id("fragment_saved_search_query")).clear();
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys(".it.done");
        driver.findElement(By.id("done")).click();
        Thread.sleep(1000);
        Assert.assertEquals("測試", driver.findElement(By.xpath(".//*[@text='測試']")).getText());
        Assert.assertEquals(".it.done", driver.findElement(By.xpath(".//*[@text='.it.done']")).getText());

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("測試", driver.findElement(By.xpath(".//*[@text='測試']")).getText());
    }

    //    edit Name：中文，Query：False
    @Test
    public void test_edit_Chinese_FalseQuery() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath(".//*[@text='name']")).click();
        driver.findElement(By.id("fragment_saved_search_name")).clear();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("測試");
        driver.findElement(By.id("fragment_saved_search_query")).clear();
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("false query");
        driver.findElement(By.id("done")).click();
        Thread.sleep(1000);
        Assert.assertEquals("測試", driver.findElement(By.xpath(".//*[@text='測試']")).getText());
        Assert.assertEquals("false query", driver.findElement(By.xpath(".//*[@text='false query']")).getText());

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("測試", driver.findElement(By.xpath(".//*[@text='測試']")).getText());

        driver.findElement(By.xpath(".//*[@text='測試']")).click();
        Assert.assertEquals("您的搜尋沒有任何結果", driver.findElement(By.xpath(".//*[@text='您的搜尋沒有任何結果']")).getText());
    }

    //    edit Name：中文，Query：Empty
    @Test
    public void test_edit_Chinese_EmptyQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath(".//*[@text='name']")).click();
        driver.findElement(By.id("fragment_saved_search_name")).clear();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("測試");
        driver.findElement(By.id("fragment_saved_search_query")).clear();
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.id("textinput_error")).getText());
    }

    //    edit Name：英文，Query：True
    @Test
    public void test_edit_English_TrueQuery() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath(".//*[@text='name']")).click();
        driver.findElement(By.id("fragment_saved_search_name")).clear();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("test");
        driver.findElement(By.id("fragment_saved_search_query")).clear();
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys(".it.done");
        driver.findElement(By.id("done")).click();
        Thread.sleep(1000);
        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());
        Assert.assertEquals(".it.done", driver.findElement(By.xpath(".//*[@text='.it.done']")).getText());

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());
    }

    //    edit Name：英文，Query：False
    @Test
    public void test_edit_English_FalseQuery() throws InterruptedException {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        Thread.sleep(1000);
        driver.findElement(By.xpath(".//*[@text='name']")).click();
        driver.findElement(By.id("fragment_saved_search_name")).clear();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("test");
        driver.findElement(By.id("fragment_saved_search_query")).clear();
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("false query");
        driver.findElement(By.id("done")).click();
        Thread.sleep(1000);
        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());
        Assert.assertEquals("false query", driver.findElement(By.xpath(".//*[@text='false query']")).getText());

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        Assert.assertEquals("test", driver.findElement(By.xpath(".//*[@text='test']")).getText());

        driver.findElement(By.xpath(".//*[@text='test']")).click();
        Assert.assertEquals("您的搜尋沒有任何結果", driver.findElement(By.xpath(".//*[@text='您的搜尋沒有任何結果']")).getText());
    }

    //    edit Name：英文，Query：Empty
    @Test
    public void test_edit_English_EmptyQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath(".//*[@text='name']")).click();
        driver.findElement(By.id("fragment_saved_search_name")).clear();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("test");
        driver.findElement(By.id("fragment_saved_search_query")).clear();
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.id("textinput_error")).getText());
    }

    //    edit Name：Empty，Query：True
    @Test
    public void test_edit_Empty_TrueQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath(".//*[@text='name']")).click();
        driver.findElement(By.id("fragment_saved_search_name")).clear();
        driver.findElement(By.id("fragment_saved_search_query")).clear();
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys(".it.done");
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.id("textinput_error")).getText());
    }

    //    edit Name：Empty，Query：False
    @Test
    public void test_edit_Empty_FalseQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath(".//*[@text='name']")).click();
        driver.findElement(By.id("fragment_saved_search_name")).clear();
        driver.findElement(By.id("fragment_saved_search_query")).clear();
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("false query");
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.id("textinput_error")).getText());
    }

    //    edit Name：Empty，Query：Empty
    @Test
    public void test_edit_Empty_EmptyQuery() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        driver.findElement(By.xpath(".//*[@text='name']")).click();
        driver.findElement(By.id("fragment_saved_search_name")).clear();
        driver.findElement(By.id("fragment_saved_search_query")).clear();
        driver.findElement(By.id("done")).click();
        Assert.assertEquals("不能留空", driver.findElement(By.xpath("//android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")).getText());
        Assert.assertEquals("不能留空", driver.findElement(By.xpath("//android.widget.LinearLayout[2]/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.TextView")).getText());
    }

    @Test
    public void test_delete() {
        driver.findElement(By.xpath(".//*[@text='好的']")).click();
        driver.navigate().back();
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        driver.findElement(By.xpath(".//*[@text='搜尋']")).click();
        driver.findElement(By.id("fab")).click();
        driver.findElement(By.id("fragment_saved_search_name")).sendKeys("name");
        driver.findElement(By.id("fragment_saved_search_query")).sendKeys("query");
        driver.findElement(By.id("done")).click();

        TouchAction ta = new TouchAction(driver);
        ta.longPress(driver.findElement(By.xpath(".//*[@text='name']")), 2000).release().perform();
        driver.findElement(By.xpath("(//android.widget.ImageView[@content-desc=\"更多選項\"])[1]")).click();
        driver.findElement(By.xpath(".//*[@text='刪除']")).click();

        try {
            driver.findElement(By.xpath(".//*[@text='name']"));
            Assert.fail();
        } catch (Exception message) {
            System.out.println("delete success");
        }

        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"打開抽屜\"]")).click();
        try {
            driver.findElement(By.xpath(".//*[@text='name']"));
            Assert.fail();
        } catch (Exception message) {
            System.out.println("delete success");
        }
    }
}
