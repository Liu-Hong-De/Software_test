import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

class EditComment(unittest.TestCase):
    # use the demo account to sign in
    def setUp(self):
        self.driver = webdriver.Chrome()
        driver =self.driver
        driver.implicitly_wait(20)      # set a waiting time at most 20 seconds
        driver.get("http://127.0.0.1:3000")
        driver.find_element_by_xpath("//*[@id=\"navbar-collapse\"]/ul[2]/li[2]/a").click()  # click the sign in button
        time.sleep(1)
        driver.find_element_by_name("email").send_keys("demo@keystonejs.com")               # enter the email and password
        driver.find_element_by_name("password").send_keys("demo")
        time.sleep(1)
        driver.find_element_by_xpath("//*[@id=\"signin-view\"]/div/div[1]/div/div[2]/form/button").click() # click to sign in
        time.sleep(2)

        # create a post
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[1]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("use selenium to create a post")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_class_name("css-dmf4a8").click()
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > header > nav.primary-navbar > div > ul.app-nav.app-nav--primary.app-nav--left > li.primary-navbar__item.primary-navbar__brand > a").click()
        time.sleep(2)

        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(1) > div.dashboard-group__lists > div:nth-child(2) > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        try:
            driver.find_element_by_xpath("/html/body/div[6]/div/div/div/div/form/div[3]/button[1]").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_link_text("Comments").click()
        time.sleep(2)

    # test case1
    # test edit comment with author
    def test_EditCommentWithAuthor(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListAuthor = []
        [inputListAuthor.append(input) for input in inputList if input.is_displayed()]
        inputListAuthor[0].send_keys("Demo User")
        time.sleep(1)
        inputListAuthor[0].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div:nth-child(2) > div > div > button.css-2960tt").click()
        assert "Your changes have been saved successfully" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div.css-ctpeu").text
        time.sleep(1)
        driver.back()
        time.sleep(1)
        assert "Demo User" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(3) > a").text
        
    # test case 2
    # test edit comment with post
    def test_EditCommentWithPost(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListPost = []
        [inputListPost.append(input) for input in inputList if input.is_displayed()]
        inputListPost[1].send_keys("use selenium to create a post")
        time.sleep(1)
        inputListPost[1].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div:nth-child(2) > div > div > button.css-2960tt").click()
        assert "Your changes have been saved successfully" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div.css-ctpeu").text
        time.sleep(1)
        driver.back()
        time.sleep(1)
        assert "use selenium to create a post" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td:nth-child(4) > a").text

    # test case 3
    # test edit comment with state is Draft
    def test_EditCommentWithCommentState_Draft(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListCommentState = []
        [inputListCommentState.append(input) for input in inputList if input.is_displayed()]
        inputListCommentState[2].send_keys("Draft")
        time.sleep(1)
        inputListCommentState[2].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div:nth-child(2) > div > div > button.css-2960tt").click()
        assert "Your changes have been saved successfully" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div.css-ctpeu").text
        time.sleep(1)
        driver.back()
        time.sleep(1)
        assert "Draft" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td:nth-child(6) > div").text

    # test case 4
    # test edit comment with state is Published
    def test_EditCommentWithCommentState_Published(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListCommentState = []
        [inputListCommentState.append(input) for input in inputList if input.is_displayed()]
        inputListCommentState[2].send_keys("Published")
        time.sleep(1)
        inputListCommentState[2].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div:nth-child(2) > div > div > button.css-2960tt").click()
        assert "Your changes have been saved successfully" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div.css-ctpeu").text
        time.sleep(1)
        driver.back()
        time.sleep(1)
        assert "Published" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td:nth-child(6) > div").text

    # test case 5
    # test edit comment with state is Archived
    def test_EditCommentWithCommentState_Archived(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListCommentState = []
        [inputListCommentState.append(input) for input in inputList if input.is_displayed()]
        inputListCommentState[2].send_keys("Archived")
        time.sleep(1)
        inputListCommentState[2].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div:nth-child(2) > div > div > button.css-2960tt").click()
        assert "Your changes have been saved successfully" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div.css-ctpeu").text
        time.sleep(1)
        driver.back()
        time.sleep(1)
        assert "Archived" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td:nth-child(6) > div").text

    # test case 6
    # test edit comment with content
    def test_EditCommentWithContent(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        iframeList = driver.find_element_by_tag_name("iframe")
        iframeList.send_keys(Keys.ARROW_DOWN)
        iframeList.send_keys(Keys.ARROW_UP)
        iframeList.send_keys("content")
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div:nth-child(2) > div > div > button.css-2960tt").click()
        assert "Your changes have been saved successfully" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div > form > div.css-ctpeu").text
        time.sleep(1)
        driver.back()
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        driver.switch_to.frame(0)
        assert "content" in driver.find_element_by_tag_name('body').text
        driver.back()
        time.sleep(1)

    def tearDown(self):
        # delete the comment
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(2) > div > div:nth-child(1) > div > div > button").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-12yx24t").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-rd63ky").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

        # delete the post
        driver.find_element_by_css_selector("#react-root > div > header > nav.secondary-navbar > div > ul > li:nth-child(1) > a").click()
        time.sleep(10)
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