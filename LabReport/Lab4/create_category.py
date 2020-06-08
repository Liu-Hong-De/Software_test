import unittest
import time
from selenium import webdriver

class CreateCategory(unittest.TestCase):
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

    # create a category with chinese
    def test_CreateCategoryWithChinese(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[3]/span/a[2]").click()
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

    # create a category with english
    def test_CreateCategoryWithEnglish(self):
        driver = self.driver
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
        assert "use selenium to create a category" in driver.find_element_by_link_text("use selenium to create a category").text
        time.sleep(2)

    # create a category with special character
    def test_CreateCategoryWithSpecialChar(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[3]/span/a[2]").click()
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

    # create a category with only space
    # failed
    # expect alert message or create a category with space
    # but create a empty name category
    def test_CreateCategoryWithSpace(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[3]/span/a[2]").click()
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

    # create a category with empty name
    def test_CreateCategoryEmpty(self):
        driver = self.driver
        driver.find_element_by_xpath("//*[@id=\"react-root\"]/div/main/div/div[2]/div/div[1]/div[2]/div[3]/span/a[2]").click()
        time.sleep(1)
        driver.find_element_by_name("name").send_keys("")
        time.sleep(1)
        try:
            driver.find_element_by_class_name("css-h629qq").click()
        except:
            driver.find_element_by_class_name("css-nil").submit()
        time.sleep(1)
        assert "Name is required" in driver.find_element_by_xpath("/html/body/div[2]/div/div/div/div/form/div[2]/div[1]/div").text
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(13) > div > div > div > div > form > div.css-74dp32 > button > i").click()
        time.sleep(2)

    def tearDown(self):
        driver = self.driver
        try:
            driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(2) > div > div:nth-child(1) > div > div > button").click()
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