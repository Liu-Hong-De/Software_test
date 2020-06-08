import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

class SearchPost(unittest.TestCase):
    # use the demo account to sign in and ceate a post to search
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
        driver.find_element_by_name("name").send_keys("use selenium to search a post")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_class_name("css-dmf4a8").click()
        time.sleep(2)

    # test post with search of name and find the post success
    def test_SearchPostWithNameSuccess(self):
        driver = self.driver
        search = driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(1) > div.css-19k3iq8 > div.css-1a7pyqa > div > input")
        search.send_keys("use selenium to search a post")
        time.sleep(2)
        assert "use selenium to search a post" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td:nth-child(2) > a").text
        time.sleep(2)

    # test post with search of name amd find the post failed
    def test_SearchPostWithNameFailed(self):
        driver = self.driver
        search = driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(1) > div.css-19k3iq8 > div.css-1a7pyqa > div > input")
        search.send_keys("search not found")
        time.sleep(2)
        assert "No posts found matching search not found" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > h2").text
        search.send_keys(Keys.CONTROL, 'a')
        search.send_keys(Keys.BACK_SPACE)
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