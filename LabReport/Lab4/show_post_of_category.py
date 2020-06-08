import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

class ShowPostOfCategory(unittest.TestCase):
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

    # test show post of category success
    def test_ShowPostOfCategorySuccess(self):
        driver = self.driver
        # create a category
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[3]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("use selenium to create a category")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_class_name("css-dmf4a8").click()
        time.sleep(1)

        # create a post
        driver.find_element_by_css_selector("#react-root > div > header > nav.primary-navbar > div > ul.app-nav.app-nav--primary.app-nav--left > li.primary-navbar__item.primary-navbar__brand > a").click()
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(1) > div.dashboard-group__lists > div:nth-child(1) > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("use selenium to create a post")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.refresh()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[2].send_keys("Published")
        inputListData[2].send_keys(Keys.ENTER)
        time.sleep(1)
        inputListData[5].send_keys("use selenium to create a category")
        time.sleep(1)
        inputListData[5].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)

        # go to blog page
        driver.find_element_by_css_selector("#react-root > div > header > nav.primary-navbar > div > ul.app-nav.app-nav--primary.app-nav--right > li:nth-child(1) > a").click()
        time.sleep(1)
        driver.find_element_by_link_text("Blog").click()
        time.sleep(1)
        driver.find_element_by_partial_link_text("category").click()
        time.sleep(10)
        assert "use selenium to create a post" in driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > div.blog > article > div.media-body > h3 > a").text

    # test show post of category failed
    def test_ShowPostOfCategoryFailed(self):
        driver = self.driver
        # create a category
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[3]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("use selenium to create a category")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_class_name("css-dmf4a8").click()
        time.sleep(1)

        # create a post
        driver.find_element_by_css_selector("#react-root > div > header > nav.primary-navbar > div > ul.app-nav.app-nav--primary.app-nav--left > li.primary-navbar__item.primary-navbar__brand > a").click()
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(1) > div.dashboard-group__lists > div:nth-child(1) > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("use selenium to create a post")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.refresh()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[2].send_keys("Published")
        inputListData[2].send_keys(Keys.ENTER)
        time.sleep(1)
        driver.find_element_by_class_name("css-2960tt").click()
        time.sleep(1)

        # go to blog page
        driver.find_element_by_css_selector("#react-root > div > header > nav.primary-navbar > div > ul.app-nav.app-nav--primary.app-nav--right > li:nth-child(1) > a").click()
        time.sleep(1)
        driver.find_element_by_link_text("Blog").click()
        time.sleep(1)
        driver.find_element_by_partial_link_text("category").click()
        time.sleep(10)
        assert "No posts in the category use selenium to create a category" in driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > div > h3").text

    def tearDown(self):
        driver = self.driver
        # delete the post
        driver.find_element_by_link_text("Admin UI").click()
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(1) > div.dashboard-group__lists > div:nth-child(1) > span > a.dashboard-group__list-tile").click()
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(2) > div > div:nth-child(1) > div > div > button").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-12yx24t").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-rd63ky").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

        # delete the category
        driver.find_element_by_css_selector("#react-root > div > header > nav.secondary-navbar > div > ul > li:nth-child(3) > a").click()
        time.sleep(1)
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(2) > div > div:nth-child(1) > div > div > button").click()
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