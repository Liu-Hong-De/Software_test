import unittest
import time
from selenium import webdriver

class CreateUser(unittest.TestCase):
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

    # test case 1
    # test create user with first name and last name are empty
    # can not submit the form
    def test_CreateUserWithName_Empty(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[3].send_keys("demo@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        assert "Name is required" in driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-1ogg8o0 > div.css-1nqppvz > div").text

    # test case 2
    # test create user with first name
    def test_CreateUserWithFirstName(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[3].send_keys("demo1@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        driver.find_element_by_css_selector("#react-root > div > header > nav.secondary-navbar > div > ul > li > a").click()
        time.sleep(1)
        driver.find_element_by_link_text("liu").click()
        time.sleep(1)
        assert "liu" in driver.find_element_by_name("name.first").get_attribute("value")

    # test case 3
    # test create user with last name
    def test_CreateUserWithLastName(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[2].send_keys("hongde")
        time.sleep(1)
        inputListData[3].send_keys("demo2@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        driver.find_element_by_css_selector("#react-root > div > header > nav.secondary-navbar > div > ul > li > a").click()
        time.sleep(1)
        driver.find_element_by_link_text("hongde").click()
        time.sleep(1)
        assert "hongde" in driver.find_element_by_name("name.last").get_attribute("value")

    # test case 4
    # test create user with first name and last name
    def test_CreateUserWithName(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde")
        time.sleep(1)
        inputListData[3].send_keys("demo3@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        driver.find_element_by_css_selector("#react-root > div > header > nav.secondary-navbar > div > ul > li > a").click()
        time.sleep(1)
        driver.find_element_by_link_text("liu hongde").click()
        time.sleep(1)
        assert "liu" in driver.find_element_by_name("name.first").get_attribute("value")
        assert "hongde" in driver.find_element_by_name("name.last").get_attribute("value")

    # test case 5
    # test create user with email
    def test_CreateUserWithEmail(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde1")
        time.sleep(1)
        inputListData[3].send_keys("demo4@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        driver.find_element_by_css_selector("#react-root > div > header > nav.secondary-navbar > div > ul > li > a").click()
        time.sleep(1)
        driver.find_element_by_link_text("liu hongde1").click()
        time.sleep(1)
        assert "demo4@keystonejs.com" in driver.find_element_by_name("email").get_attribute("value")

    # test case 6
    # test create user with email is @keystonejs.com
    # can not submit the form
    def test_CreateUserWithEmail_NoFront(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde1")
        time.sleep(1)
        inputListData[3].send_keys("@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

    # test case 7
    # test create user with email is demo@
    # can not submit the form
    def test_CreateUserWithEmail_NoBack(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde1")
        time.sleep(1)
        inputListData[3].send_keys("demo@")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

    # test case 8
    # test create user with email is demo@keystonejs
    def test_CreateUserWithEmail_NoCom(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde1")
        time.sleep(1)
        inputListData[3].send_keys("demo@keystonejs")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        assert "Email is invalid" in driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-1ogg8o0 > div.css-1nqppvz > div").text

    # test case 9
    # test create user with email is characters
    # can not submit the form
    def test_CreateUserWithEmail_Char(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde1")
        time.sleep(1)
        inputListData[3].send_keys("email")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

    # test case 10
    # test create user with email is empty
    def test_CreateUserWithEmail_Empty(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde1")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        assert "Email is required" in driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-1ogg8o0 > div.css-1nqppvz > div").text

    # test case 11
    # test create user with short password
    def test_CreateUserWithShortPassword(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde1")
        time.sleep(1)
        inputListData[3].send_keys("demo@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("hongde")
        time.sleep(1)
        inputListData[5].send_keys("hongde")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        assert "Password must be longer than 8 characters." in driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-1ogg8o0 > div.css-1nqppvz > div").text

    # test case 12
    # test create user with common password
    def test_CreateUserWithCommonPassword(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde1")
        time.sleep(1)
        inputListData[3].send_keys("demo@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("password")
        time.sleep(1)
        inputListData[5].send_keys("password")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        assert "Password must not be a common, frequently-used password." in driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-1ogg8o0 > div.css-1nqppvz > div").text

    # test case 13
    # test create user with confirm password is not equal of password
    def test_CreateUserWithConfirmPassword(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde1")
        time.sleep(1)
        inputListData[3].send_keys("demo@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("liuhongde")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        assert "Passwords must match." in driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-1ogg8o0 > div.css-1nqppvz > div").text

    # test case 14
    # test create user with password is equal to confirm password
    def test_CreateUserWithPassword(self):
        driver = self.driver
        driver.find_element_by_css_selector("#react-root > div > main > div > div.dashboard-groups > div > div:nth-child(4) > div.dashboard-group__lists > div > span > a.dashboard-group__list-create.octicon.octicon-plus").click()
        time.sleep(1)
        inputList = driver.find_elements_by_tag_name("input")
        inputListData = []
        [inputListData.append(input) for input in inputList if input.is_displayed()]
        inputListData[1].send_keys("liu")
        time.sleep(1)
        inputListData[2].send_keys("hongde2")
        time.sleep(1)
        inputListData[3].send_keys("demo5@keystonejs.com")
        time.sleep(1)
        inputListData[4].send_keys("hongdeliu")
        time.sleep(1)
        inputListData[5].send_keys("hongdeliu")
        time.sleep(1)
        driver.find_element_by_css_selector("body > div:nth-child(17) > div > div > div > div > form > div.css-2dhvf4 > button.css-h629qq").click()
        time.sleep(2)

        driver.find_element_by_css_selector("#react-root > div > header > nav.secondary-navbar > div > ul > li > a").click()
        time.sleep(1)
        driver.find_element_by_link_text("liu hongde2").click()
        time.sleep(1)
        assert "liu" in driver.find_element_by_name("name.first").get_attribute("value")
        assert "hongde2" in driver.find_element_by_name("name.last").get_attribute("value")

    def tearDown(self):
        self.driver.close()

if __name__ == "__main__":
    unittest.main()