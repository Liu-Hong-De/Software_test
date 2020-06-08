import unittest
import time
from selenium import webdriver

class DeletePost(unittest.TestCase):
    # use the demo account to sign in and ceate a post to delete
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

        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[1]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("use selenium to delete a post")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_class_name("css-dmf4a8").click()
        time.sleep(2)

    # test delete post with the manage button
    def test_DeletePostWithManage(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[2]/div/div[1]/div/div/button").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-12yx24t").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-rd63ky").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

    #  test delete post with the icon of garbage can button
    def test_DeletePostWithGarbageCan(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td.ItemList__col--control.ItemList__col--delete > button").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

    # test delete post with the delete post button
    def test_DeletePostWithDeletePostButton(self):
        driver = self.driver
        driver.find_element_by_link_text("use selenium to delete a post").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-1mj7u0z").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    unittest.main()