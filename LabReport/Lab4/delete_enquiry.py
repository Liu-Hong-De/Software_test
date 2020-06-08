import unittest
import time
from selenium import webdriver
from selenium.webdriver.support.ui import Select

class DeleteEnquiry(unittest.TestCase):
    # use the demo account to sign in
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

        # go to the contact page
        driver.find_element_by_css_selector("#react-root > div > header > nav > div > ul.app-nav.app-nav--primary.app-nav--right > li:nth-child(1) > a").click()
        time.sleep(1)
        driver.find_element_by_link_text("Contact").click()
        time.sleep(2)

        # create a enquiry
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("demo@keystonejs.com")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("Just leaving a message")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

        assert "Success!" in driver.find_element_by_css_selector("body > div > div.jumbotron.text-center > h1").text
        driver.find_element_by_css_selector("body > div > div.jumbotron.text-center > a").click()
        time.sleep(1)

    # test delete enquiry with the manage button
    def test_DeleteEnquiryWithManage(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(2) > div > div:nth-child(1) > div > div > button").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-12yx24t").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-rd63ky").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

    #  test delete enquiry with the icon of garbage can button
    def test_DeleteEnquiryWithGarbageCan(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td.ItemList__col--control.ItemList__col--delete > button").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

    # test delete enquiry with the delete enquiry button
    def test_DeleteEnquiryWithDeleteEnquiryButton(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td:nth-child(2) > a").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-1mj7u0z").click()
        time.sleep(1)
        driver.find_element_by_class_name("css-t4884").click()
        time.sleep(2)

    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    unittest.main()