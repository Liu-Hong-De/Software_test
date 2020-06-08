import unittest
import time
from selenium import webdriver
from selenium.webdriver.support.ui import Select

class CreateEnquiry(unittest.TestCase):
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

    # test case 1
    # test create enquiry with the empty name
    # can not submit the form
    def test_CreateEnquiryWithName_Empty(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("demo@keystonejs.com")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("Just leaving a message")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(3)

    # test case 2
    # test create enquiry with the name
    def test_CreateEnquiryWithName(self):
        driver = self.driver
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
        assert "name" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td:nth-child(2) > a").text

    # test case 3
    # test create enquiry with correct email
    def test_CreateEnquiryWithEmail(self):
        driver = self.driver
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
        assert "demo@keystonejs.com" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr > td:nth-child(3) > a").text

    # test case 4
    # test create enquiry with email is @keystonejs.com
    # can not submit the form
    def test_CreateEnquiryWithEmail_FrontNoValue(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("@keystonejs.com")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("Just leaving a message")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

    # test case 5
    # test create enquiry with email is demo@
    # can not submit the form
    def test_CreateEnquiryWithEmail_BackNoValue(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("demo@")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("Just leaving a message")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

    # test case 6
    # test create enquiry with email is demo@keystonejs
    # error 500
    def test_CreateEnquiryWithEmail_NoCom(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("demo@keystonejs")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("Just leaving a message")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

        assert "Sorry, an error occurred loading the page (500)" in driver.find_element_by_css_selector("body > div > h1").text

    # test case 7
    # test create enquiry with email is any characters
    # can not submit the form
    def test_CreateEnquiryWithEmail_Char(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("email")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("Just leaving a message")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

    # test case 8
    # test create enquiry with email is empty
    # can not submit the form
    def test_CreateEnquiryWithEmail_Empty(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("Just leaving a message")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

    # test case 9
    # test create enquiry with phone
    def test_CreateEnquiryWithPhone(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("demo@keystonejs.com")
        time.sleep(1)
        inputListData[2].send_keys("0912345678")
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
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        assert "0912345678" in driver.find_element_by_xpath("/html/body/div[1]/div/main/div/div/div/form/div[1]/div[1]/div/div[5]/div/input").get_attribute("value")
        driver.back()
        time.sleep(2)

    # test case 10
    # test create enquiry with regarding is (required)
    # error 500
    def test_CreateEnquiryWithRegarding_Required(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("demo@keystonejs.com")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

        assert "Sorry, an error occurred loading the page (500)" in driver.find_element_by_css_selector("body > div > h1").text

    # test case 11
    # test create enquiry with regarding is Just leaving a message
    def test_CreateEnquiryWithRegarding_JustLeavingAMessage(self):
        driver = self.driver
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
        assert "Just leaving a message" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div").text

    # test case 12
    # test create enquiry with regarding is I've got a question
    def test_CreateEnquiryWithRegarding_IveGotAQuestion(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("demo@keystonejs.com")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("I've got a question")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

        assert "Success!" in driver.find_element_by_css_selector("body > div > div.jumbotron.text-center > h1").text
        driver.find_element_by_css_selector("body > div > div.jumbotron.text-center > a").click()
        time.sleep(1)
        assert "I've got a question" in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div").text

    # test case 13
    # test create enquiry with regarding is Something else...
    def test_CreateEnquiryWithRegarding_SomethingElse(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("demo@keystonejs.com")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("Something else...")
        time.sleep(1)
        driver.find_element_by_name("message").send_keys("message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

        assert "Success!" in driver.find_element_by_css_selector("body > div > div.jumbotron.text-center > h1").text
        driver.find_element_by_css_selector("body > div > div.jumbotron.text-center > a").click()
        time.sleep(1)
        assert "Something else..." in driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(4) > div").text

    # test case 14
    # test create enquiry with message empty
    # can not submit the form
    def test_CreateEnquiryWithMessage_Empty(self):
        driver = self.driver
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[0].send_keys("name")
        time.sleep(1)
        inputListData[1].send_keys("demo@keystonejs.com")
        time.sleep(1)
        Select(driver.find_element_by_name("enquiryType")).select_by_visible_text("Just leaving a message")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div > div.row > div.col-sm-8 > form > div:nth-child(7) > div > button").click()
        time.sleep(2)

    # test case 15
    # test create enquiry with message success
    def test_CreateEnquiryWithMessage(self):
        driver = self.driver
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
        driver.find_element_by_css_selector("#react-root > div > main > div > div > div:nth-child(3) > div > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > a").click()
        time.sleep(1)
        assert "message" in driver.find_element_by_name("message").text
        driver.back()
        time.sleep(2)

    def tearDown(self):
        driver = self.driver
        try:
            # delete the enquiry
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