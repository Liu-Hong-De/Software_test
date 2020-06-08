import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from bs4 import BeautifulSoup
from selenium.webdriver.support.ui import Select

class EditPost(unittest.TestCase):
    # use the demo account to sign in and create a post to edit
    def setUp(self):
        self.driver = webdriver.Chrome()
        driver =self.driver
        driver.implicitly_wait(5)      # set a waiting time at most 20 seconds
        driver.get("http://127.0.0.1:3000")
        driver.find_element_by_xpath("//*[@id=\"navbar-collapse\"]/ul[2]/li[2]/a").click()  # click the sign in button
        time.sleep(1)
        driver.find_element_by_name("email").send_keys("demo@keystonejs.com")               # enter the email and password
        driver.find_element_by_name("password").send_keys("demo")
        time.sleep(1)
        driver.find_element_by_xpath("//*[@id=\"signin-view\"]/div/div[1]/div/div[2]/form/button").click() # click to sign in
        time.sleep(2)

        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[1]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("use selenium to edit a post")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_class_name("css-dmf4a8").click()
        time.sleep(2)

    # test case 1
    # edit post with empty name
    # empty name is invalid
    def test_EditPostWithEmptyName(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        page_result = []
        page_result.append(driver.page_source)
        NewExplain = []
        for item0 in page_result:
            html_soup = BeautifulSoup(item0, "lxml")
            NewExplain = html_soup.select('#react-root > div > main > div > div > div.css-1xkojxp > form > div.css-1nqppvz > div')
        assert "Name is required" in NewExplain[0].text
        driver.back()
        time.sleep(2)

    # test case 2
    # edit post with name
    def test_EditPostWithName(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "Your changes have been saved successfully" in driver.find_element_by_class_name("css-ctpeu").text
        driver.back()
        time.sleep(1)
        assert "edit OK" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr[1]/td[2]/a").text
        time.sleep(2)

    # test case 3
    # edit post with state is Published
    def test_EditPostWithStatePublished(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListState = []
        [inputListState.append(input) for input in inputList if input.is_displayed()]
        inputListState[2].send_keys("Published")
        inputListState[2].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "Your changes have been saved successfully" in driver.find_element_by_class_name("css-ctpeu").text
        driver.back()
        time.sleep(1)
        assert "edit OK" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr[1]/td[2]/a").text
        assert "Published" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr[1]/td[3]/div").text
        time.sleep(2)

    # test case 4
    # edit post with state is Archived
    def test_EditPostWithStateArchived(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListState = []
        [inputListState.append(input) for input in inputList if input.is_displayed()]
        inputListState[2].send_keys("Archived")
        inputListState[2].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "Your changes have been saved successfully" in driver.find_element_by_class_name("css-ctpeu").text
        driver.back()
        time.sleep(1)
        assert "edit OK" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr[1]/td[2]/a").text
        assert "Archived" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr[1]/td[3]/div").text
        time.sleep(2)

    # test case 5
    # edit post with author is Demo User
    def test_EditPostWithAuthor(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListAuthor = []
        [inputListAuthor.append(input) for input in inputList if input.is_displayed()]
        inputListAuthor[3].send_keys("Demo User")
        inputListAuthor[3].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "Your changes have been saved successfully" in driver.find_element_by_class_name("css-ctpeu").text
        driver.back()
        time.sleep(1)
        assert "edit OK" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr[1]/td[2]/a").text
        assert "Demo User" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr/td[4]/a").text
        time.sleep(2)

    # test case 6
    # edit post with published date format is yyyy-mmdd
    def test_EditPostWithDate_yyyy_mmdd(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListDate = []
        [inputListDate.append(input) for input in inputList if input.is_displayed()]
        inputListDate[4].send_keys(Keys.CONTROL, "a")
        inputListDate[4].send_keys(Keys.BACK_SPACE)
        inputListDate[4].send_keys("2020-0603")
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_DOWN)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_UP)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "Your changes have been saved successfully" in driver.find_element_by_class_name("css-ctpeu").text
        driver.back()
        time.sleep(1)
        assert "edit OK" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr[1]/td[2]/a").text
        assert "June 3rd 2020" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr/td[5]/div").text
        time.sleep(2)

    # test case 7
    # failed
    # expected：June 3rd 2020
    # actual：March 1st 2020
    # test post with published date format is yyyymm-dd
    def test_EditPostWithDate_yyyymm_dd(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListDate = []
        [inputListDate.append(input) for input in inputList if input.is_displayed()]
        inputListDate[4].send_keys(Keys.CONTROL, "a")
        inputListDate[4].send_keys(Keys.BACK_SPACE)
        inputListDate[4].send_keys("202006-03")
        time.sleep(1)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_DOWN)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_UP)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "Your changes have been saved successfully" in driver.find_element_by_class_name("css-ctpeu").text
        driver.back()
        time.sleep(1)
        assert "edit OK" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr[1]/td[2]/a").text
        assert "June 3rd 2020" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr/td[5]/div").text
        time.sleep(2)

    # test case 8
    # edit post with published date format is yyyymmdd
    def test_EditPostWithDate_yyyymmdd(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListDate = []
        [inputListDate.append(input) for input in inputList if input.is_displayed()]
        inputListDate[4].send_keys(Keys.CONTROL, "a")
        inputListDate[4].send_keys(Keys.BACK_SPACE)
        inputListDate[4].send_keys("20200603")
        time.sleep(1)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_DOWN)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_UP)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "Your changes have been saved successfully" in driver.find_element_by_class_name("css-ctpeu").text
        driver.back()
        time.sleep(1)
        assert "edit OK" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr[1]/td[2]/a").text
        assert "June 3rd 2020" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[3]/div/div/table/tbody/tr/td[5]/div").text
        time.sleep(2)

    # test case 9
    # test post with published date format is yyyymdd
    # date format is invalid
    def test_EditPostWithDate_yyyymdd(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListDate = []
        [inputListDate.append(input) for input in inputList if input.is_displayed()]
        inputListDate[4].send_keys(Keys.CONTROL, "a")
        inputListDate[4].send_keys(Keys.BACK_SPACE)
        inputListDate[4].send_keys("2020603")
        time.sleep(1)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_DOWN)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_UP)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "PublishedDate is invalid" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[1]/form/div[1]/div").text
        driver.back()
        time.sleep(2)

    # test case 10
    # test post with published date Month is bigger than 12
    # date format is invalid
    def test_EditPostWithDate_Big_Month(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListDate = []
        [inputListDate.append(input) for input in inputList if input.is_displayed()]
        inputListDate[4].send_keys(Keys.CONTROL, "a")
        inputListDate[4].send_keys(Keys.BACK_SPACE)
        inputListDate[4].send_keys("2020-60-03")
        time.sleep(1)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_DOWN)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_UP)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "PublishedDate is invalid" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[1]/form/div[1]/div").text
        driver.back()
        time.sleep(2)

    # test case 11
    # test post with published date Day is bigger than the day of this year
    # date format is invalid
    def test_EditPostWithDate_Big_Day(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListDate = []
        [inputListDate.append(input) for input in inputList if input.is_displayed()]
        inputListDate[4].send_keys(Keys.CONTROL, "a")
        inputListDate[4].send_keys(Keys.BACK_SPACE)
        inputListDate[4].send_keys("2020-06-99")
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_DOWN)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_UP)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "PublishedDate is invalid" in driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[1]/form/div[1]/div").text
        driver.back()
        time.sleep(2)

    # test case 12
    # failed
    # expected：PublishedDate is invalid
    # actual：Your changes have been saved successfully
    # test post with published date Month is more than two digits
    def test_EditPostWithDate_Month_MoreThanTwoDigits(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListDate = []
        [inputListDate.append(input) for input in inputList if input.is_displayed()]
        inputListDate[4].send_keys(Keys.CONTROL, "a")
        inputListDate[4].send_keys(Keys.BACK_SPACE)
        inputListDate[4].send_keys("2020-111-03")
        time.sleep(1)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_DOWN)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_UP)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        NewExplain = []
        NewExplain = driver.find_elements_by_css_selector("#react-root > div > main > div > div > div.css-1xkojxp > form > div.css-ctpeu")
        try:
            assert "PublishedDate is invalid" in NewExplain[0].text
        finally:
            driver.back()
        time.sleep(2)

    # test case 13
    # failed
    # expected：PublishedDate is invalid
    # actual：Your changes have been saved successfully
    # test post with published date Day is more than two digits
    def test_EditPostWithDate_Day_MoreThanTwoDigits(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListDate = []
        [inputListDate.append(input) for input in inputList if input.is_displayed()]
        inputListDate[4].send_keys(Keys.CONTROL, "a")
        inputListDate[4].send_keys(Keys.BACK_SPACE)
        inputListDate[4].send_keys("2020-06-135")
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_DOWN)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_UP)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        NewExplain = []
        NewExplain = driver.find_elements_by_css_selector("#react-root > div > main > div > div > div.css-1xkojxp > form > div.css-ctpeu")
        try:
            assert "PublishedDate is invalid" in NewExplain[0].text
        finally:
            driver.back()
        time.sleep(2)

    # test case 14
    # test post with published date entering characters
    # date format is invalid
    def test_EditPostWithDate_Char(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListDate = []
        [inputListDate.append(input) for input in inputList if input.is_displayed()]
        inputListDate[4].send_keys(Keys.CONTROL, "a")
        inputListDate[4].send_keys(Keys.BACK_SPACE)
        inputListDate[4].send_keys("date")
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_DOWN)
        driver.find_element_by_class_name("css-2960tt").send_keys(Keys.ARROW_UP)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "PublishedDate is invalid" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div.css-1xkojxp > form > div.css-1nqppvz > div").text
        driver.back()
        time.sleep(2)

    # test case 15
    # test post with content brief
    def test_EditPostWithContentBrief(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        iframeList = driver.find_elements_by_tag_name('iframe')
        iframeList[0].send_keys(Keys.ARROW_DOWN)
        iframeList[0].send_keys(Keys.ARROW_UP)
        iframeList[0].send_keys('content brief')
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "Your changes have been saved successfully" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div.css-1xkojxp > form > div.css-ctpeu").text
        driver.back()
        time.sleep(1)
        driver.find_element_by_link_text("edit OK").click()
        time.sleep(1)
        driver.switch_to.frame(0)
        assert "content brief" in driver.find_element_by_tag_name('body').text
        driver.back()
        time.sleep(2)

    # test case 16
    # test posr with content extended
    def test_EditPostWithContentExtended(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to edit a post").click()
        time.sleep(1)
        driver.find_element_by_name("name").clear()
        driver.find_element_by_name("name").send_keys("edit OK")
        time.sleep(1)
        iframeList = driver.find_elements_by_tag_name('iframe')
        iframeList[1].send_keys(Keys.ARROW_DOWN)
        iframeList[1].send_keys(Keys.ARROW_UP)
        iframeList[1].send_keys('content extended')
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)
        assert "Your changes have been saved successfully" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div.css-1xkojxp > form > div.css-ctpeu").text
        driver.back()
        time.sleep(1)
        driver.find_element_by_link_text("edit OK").click()
        time.sleep(1)
        driver.switch_to.frame(1)
        assert "content extended" in driver.find_element_by_tag_name('body').text
        driver.back()
        time.sleep(2)

    def tearDown(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[2]/div/div[1]/div/div/button").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-12yx24t").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-rd63ky").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)
        driver.close()

if __name__ == "__main__":
    unittest.main()