import unittest
import time
from selenium import webdriver

class DeleteComment(unittest.TestCase):
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

        # create a comment
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(1) > div.dashboard-group__lists > div:nth-child(2) > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_link_text("Comments").click()
        time.sleep(1)

    # test delete comment with the manage button
    def test_DeleteCommentWithManage(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(2) > div > div:nth-child(1) > div > div > button").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-12yx24t").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-rd63ky").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

    #  test delete comment with the icon of garbage can button
    def test_DeleteCommentWithGarbageCan(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td.ItemList__col--control.ItemList__col--delete > button").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

    # test delete comment with the delete comment button
    def test_DeleteCommentWithDeleteCommentButton(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-1mj7u0z").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    unittest.main()