import unittest
import time
from selenium import webdriver
from selenium.webdriver.common.keys import Keys

class CreatePost(unittest.TestCase):
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

    # create a post with chinese
    def test_CreatePostWithChinese(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[1]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("利用測試創建")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_class_name("css-dmf4a8").click()
        assert "利用測試創建" in driver.find_element_by_link_text("利用測試創建").text
        time.sleep(2)

    # create a post with english
    def test_CreatePostWithEnglish(self):
        driver = self.driver
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
        assert "use selenium to create a post" in driver.find_element_by_link_text("use selenium to create a post").text
        time.sleep(2)

    # create a post with special character
    def test_CreatePostWithSpecialChar(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[1]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("+-*/")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_class_name("css-dmf4a8").click()
        assert "+-*/" in driver.find_element_by_link_text("+-*/").text
        time.sleep(2)

    # create a post with only space
    # failed
    # expect alert message or create a post with space
    # but create a empty name post
    def test_CreatePostWithSpace(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[1]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys(" ")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        driver.find_element_by_class_name("css-dmf4a8").click()
        assert " " in driver.find_element_by_link_text("").text
        time.sleep(2)

    # create a post with empty name
    def test_CreatePostEmpty(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[1]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit() 
        assert "Name is required" in driver.find_element_by_css_selector("body > div:nth-child(13) > div > div > div > div > form > div.css-1ogg8o0 > div.css-1nqppvz > div").text
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(13) > div > div > div > div > form > div.css-74dp32 > button").click()
        time.sleep(2)

    def tearDown(self):
        driver = self.driver
        try:
            driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div/div[2]/div/div[1]/div/div/button").click()
            time.sleep(1)
            driver.find_element_by_class_name("css-12yx24t").click()
            time.sleep(1)
            driver.find_element_by_class_name("css-rd63ky").click()
            time.sleep(1)
            driver.find_element_by_class_name("css-t4884").click()
            time.sleep(2)
            driver.close()
        except:
            driver.close()

if __name__ == "__main__":
    unittest.main()