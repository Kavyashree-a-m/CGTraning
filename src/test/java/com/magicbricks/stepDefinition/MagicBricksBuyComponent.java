package com.magicbricks.stepDefinition;

import static org.testng.Assert.*;

import java.util.Map;

import org.apache.commons.math3.analysis.function.Sqrt;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.magicbricks.driverSetup.DriverSetup;
import com.magicbricks.locatorstore.LoginPageLocator;
import com.magicbricks.pages.HomePage;
import com.magicbricks.pages.LoginPage;
import com.magicbricks.pages.PremiumPage;
import com.magicbricks.pages.PropWorthPage;
import com.magicbricks.pages.PropertyForSale;
import com.magicbricks.parameters.PropertiesReader;
import com.magicbricks.utils.JsonUtil;
import com.magicbricks.utils.ScreenshotUtil;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MagicBricksBuyComponent {
	public static Map<String, Object> testData;
	public static Map<String, String> currentData;
	LoginPage loginPage;
	HomePage homePage;
	PropertyForSale propertyForSale;
	PropWorthPage propWorthPage;
	PremiumPage premiumPage;

	// auth test start
	// ==========================================================================================================
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
		Hooks.logger.info("Loading login url");
		DriverSetup.getDriver().get(PropertiesReader.getUrlProperty("loginurl"));
		loginPage = new LoginPage();
	}

	@When("I select Buyer button as the user type")
	public void i_select_buyer_button_as_the_user_type() {
		Hooks.logger.info("Click on buyer");
		loginPage.clickOnBuyer();
	}

	@When("I enter the mobile number {string}")
	public void i_enter_the_mobile_number(String mobileNumber) {
		Hooks.logger.info("Enter Mobile Number");
		loginPage.typeMobileNumber(mobileNumber);
	}

	@When("I enter the captcha")
	public void i_enter_the_captcha() throws InterruptedException {
		loginPage.typeCaptcha();
		//given Thread.sleep to enter captcha manually
		loginPage.manualIntervention();
		Hooks.logger.info("Enter Captcha");
	}

	@When("I click on the Next button")
	public void i_click_on_the_next_button() {
		loginPage.clickOnNext();
		Hooks.logger.info("Click on next");
	}

	@When("I enter the OTP")
	public void i_enter_the_otp() throws InterruptedException {
		//given Thread.sleep to enter OTP manually
		loginPage.manualIntervention();
		loginPage.clickOnContinue();
		Hooks.logger.info("Entered otp");
	}

	@Then("I should be logged in successfully")
	public void i_should_be_logged_in_successfully() {
		try {
			Hooks.logger.info("Login successfully");
			Hooks.test.pass("login with valid data pass");
			assertTrue(loginPage.isHomePageLoaded().contains("magicbricks"));
		} catch (AssertionError e) {
			Hooks.logger.error("Login failed");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("Login failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}
	}

	@When("I enter the invalid mobile number {string}")
	public void i_enter_the_invalid_mobile_number(String mobilenumber) {
		loginPage.typeMobileNumber(mobilenumber);
		loginPage.clickOnNext();
		Hooks.logger.info("Entered wrong mobile number");

	}

	@Then("the system should display an error message indicating that Mobile number should be of min. ten digits. Please re-enter.")
	public void the_system_should_display_an_error_message_indicating_that_mobile_number_should_be_of_min_ten_digits_please_re_enter() {
		try {
			Hooks.logger.info("error message displayed");
			Hooks.test.pass("error message displayed properly");
			assertTrue(loginPage.getErrorMsg().equals("Mobile number should be of min. 10 digits. Please re-ente."));
		} catch (AssertionError e) {
			Hooks.logger.error("error message not displayed properly");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("checking error message for invalid test failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}
	}
	
	@When("I enter the invalid captcha")
	public void i_enter_the_invalid_captcha() throws InterruptedException {
		//given Thread.sleep to enter captcha manually
	    loginPage.typeCaptcha();
	    loginPage.manualIntervention();
	    loginPage.clickOnNext();
	}

	@Then("the system should display an error message indicating that Please enter valid captcha.")
	public void the_system_should_display_an_error_message_indicating_that_please_enter_valid_captcha() {
		try {
			Hooks.logger.info("captcha error message displayed");
			Hooks.test.pass("captha error message displayed properly");
		assertEquals(loginPage.captchError(), "Please enter valid captcha.");
		}catch (AssertionError e) {
			Hooks.logger.error("error message not displayed properly");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("checking error message for invalid captcha test failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}
	}

// end of auth
//=========================================================================================================	

	// Start search test
//===========================================================================================================
	@Given("I am on the buy search page")
	public void i_am_on_the_buy_search_page() {
		DriverSetup.getDriver().get(PropertiesReader.getUrlProperty("baseurl"));
		homePage = new HomePage();
		loginPage = new LoginPage();
		propertyForSale = new PropertyForSale();
		propWorthPage = new PropWorthPage();
		premiumPage = new PremiumPage();
	}

	@When("I enter the location")
	public void i_enter_the_location() {
		testData = JsonUtil.readJson(PropertiesReader.getPathProperty("jsondatapath"));
		String searchvalue = homePage.typeLocation(testData.get("location").toString(),
				testData.get("searchXpath").toString());
		Hooks.logger.info("location entered");
		try {
			Hooks.logger.info("location displayed");
			Hooks.test.pass("location assert pass");
			assertEquals(searchvalue, testData.get("location").toString());
		} catch (AssertionError e) {
			Hooks.logger.error("location not displayed");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("location assert failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}

	}

	@When("I click on the Search button")
	public void i_click_on_the_search_button() {
		homePage.clickOnSearch();
		Hooks.logger.info("clicked on search");
	}

	@Then("the system should display a list of properties in the entered location")
	public void the_system_should_display_a_list_of_properties_in_the_entered_location() {
		boolean val = propertyForSale.getTextToValidateLocation(testData.get("location").toString());
		Hooks.logger.info("Feteching the address text and asserting");
		try {
			Hooks.logger.info("Property are displayed with respect to the search location");
			Hooks.test.pass("Search with location test pass");
			assertTrue(val);
		} catch (AssertionError e) {
			Hooks.logger.error("Property are not displayed with respect to the search location");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("Search with location test failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}

	}

	@When("I select the type of property Villa")
	public void i_select_the_type_of_property_villa() {
		homePage.selectVilla();
		Hooks.logger.info("Selected villa");
	}

	@Then("the system should display a list of selected property type")
	public void the_system_should_display_a_list_of_selected_property_type() {
		testData = JsonUtil.readJson(PropertiesReader.getPathProperty("jsondatapath"));
		boolean villvalue = propertyForSale.getTextToValidateVilla(testData.get("property").toString());
		Hooks.logger.info("Feteching the property type and asserting");
		try {
			Hooks.logger.info("Property are displayed with respect to the property type");
			Hooks.test.pass("Search with property test pass");
			assertTrue(villvalue);

		} catch (AssertionError e) {
			Hooks.logger.error("Property are not displayed with respect to the search property type");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("Search Villa failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}

	}

	@When("I enter the minimum budget")
	public void i_enter_the_minimum_budget() {
		homePage.clickOnBudget();
		Hooks.logger.info("clicked on budget");
		homePage.typeMin(testData.get("minimum").toString());
		Hooks.logger.info("entered minimum value");
	}

	@When("I enter the maximum budget")
	public void i_enter_the_maximum_budget() {
		homePage.typeMax(testData.get("maximum").toString());
		Hooks.logger.info("Entered maximum value");
	}

	@Then("the system should display properties with prices between the range entered")
	public void the_system_should_display_properties_with_prices_between_the_range_entered() {
		boolean priceAssert = propertyForSale.checkRange(testData.get("minimum").toString(),
				testData.get("maximum").toString());
		try {
			Hooks.logger.info("Property are displayed within the range");
			Hooks.test.pass("Budget filter test pass");
			assertTrue(priceAssert);
		} catch (AssertionError e) {
			Hooks.logger.error("Property are not displayed within the range");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("Budget filter failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}

	}
	// end of search test
//=========================================================================================================================	

	// premium start
//==========================================================================================================================	
	@When("I click on Buy")
	public void i_click_on_buy() {
		homePage.clickOnBuy();
		Hooks.logger.info("clicked on Buy");
	}

	@When("I click on Premium House")
	public void i_click_on_premium_house() {
		homePage.clickOnPremiun();
		Hooks.logger.info("Clicked on premium house");
	}

	@Then("I should verify all the links are present")
	public void i_should_verify_all_the_links_are_present() {
		propWorthPage.switchWindow();
		boolean linkVa = premiumPage.checkText();
		try {
			Hooks.logger.info("all the links are present");
			Hooks.test.pass("vrefiy links test pass");
			assertTrue(linkVa);
		} catch (AssertionError e) {
			Hooks.logger.error("links are not present");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("verfiy link test failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}

	}

	@When("I click on EMI Calculator")
	public void i_click_on_emi_calculator() {
		propWorthPage.switchWindow();
		Hooks.logger.info("Switching window");
		premiumPage.clickOnEMI();
		Hooks.logger.info("clicked on emi");
	}

	@Then("I should see the pie chart displayed")
	public void i_should_see_the_pie_chart_displayed() {
		propWorthPage.switchWindow();
		Hooks.logger.info("switching window");
		try {
			Hooks.logger.info("piechart is present");
			Hooks.test.pass(" test pass");
			assertTrue(premiumPage.piechartPresent());
		} catch (AssertionError e) {
			Hooks.logger.error("piechart is not present");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("test failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}

	}
	// end of premium
//===============================================================================================================================	

	// start propworth estimation test
//===========================================================================================================================	
	@When("I click on PropWorth")
	public void i_click_on_prop_worth() {
		homePage.clickOnProp();
		Hooks.logger.info("clicked on propworth");
	}

	@When("I enter location location")
	public void i_enter_location_location()                                                                                                                                                                                                                    
	{
		propWorthPage.switchWindow();
		Hooks.logger.info("switching window");
		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String location = currentData.get("location");
		propWorthPage.clickOnGetEstimation();
		Hooks.logger.info("clicked on get estimation");
		propWorthPage.typeLocation(location);
		Hooks.logger.info("entered location");

	}

	@When("I select sublocality from the sublocality dropdown")
	public void i_select_sublocality_from_the_sublocality_dropdown() {
		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String sublocality = currentData.get("sublocality");
		String optionClicked = propWorthPage.clickSubLocality(sublocality);
		Hooks.logger.info("selected sublocality");
		try {
			Hooks.logger.info("sublocality is selected properly");
			Hooks.test.pass(" assert pass");
			assertEquals(optionClicked, sublocality);
		} catch (AssertionError e) {
			Hooks.logger.error("sublocality is not selected properly");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("assert failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}

	}

	@When("I select propertyType and BHK from the property type and BHK dropdowns")
	public void i_select_property_type_and_bhk_from_the_property_type_and_bhk_dropdowns() {
		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String property = currentData.get("property");
		String bhk = currentData.get("bhk");
		propWorthPage.clickPropertyType(property, bhk);
		Hooks.logger.info("selected property and bhk");
	}

	@When("I enter super built-up area")
	public void i_enter_super_built_up_area() {
		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String sqft = currentData.get("sqft");
		String sqrftvalue = propWorthPage.typeSquareFt(sqft);
		Hooks.logger.info("entered sqft");
	}

	@When("I select floor from the floor dropdown")
	public void i_select_floor_from_the_floor_dropdown() {
		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String numfloor = currentData.get("floor");
		String floor = propWorthPage.clickFloor(numfloor);
		Hooks.logger.info("selected floor");
		try {
			Hooks.logger.info("floor is selected properly");
			Hooks.test.pass(" assert pass");
			assertEquals(floor, numfloor);
		} catch (AssertionError e) {
			Hooks.logger.error("floor is not selected properly");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("assert failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}

	}

	@When("I enter total number of floors totalFloors")
	public void i_enter_total_number_of_floors_total_floors() {
		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String totalfloor = currentData.get("totalfloor");
		propWorthPage.typeTotalFloor(totalfloor);
		Hooks.logger.info("enterde number of floor");
	}

	@When("I select interiors")
	public void i_select_interiors() {
		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String interiors = currentData.get("interiors");
		propWorthPage.clickInteriors(interiors);
		Hooks.logger.info("select interior");
	}

	@When("I enter amountSpent in the amount spent on interiors field")
	public void i_enter_amount_spent_in_the_amount_spent_on_interiors_field() {
		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String amountspent = currentData.get("amountspent");
		propWorthPage.typeMoneySpendOnIt(amountspent);
		Hooks.logger.info("entered amount spent");
	}

	@When("I click on Additional Details")
	public void i_click_on_additional_details() {
		propWorthPage.clickOnAdditionalDetails();
	}

	@When("I select desiredFacing from the desired facing dropdown")
	public void i_select_desired_facing_from_the_desired_facing_dropdown() {
		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String direction = currentData.get("direction");
		propWorthPage.selectDirection(direction);
	}

	@When("I click on Get Estimation")
	public void i_click_on_get_estimation() {
		propWorthPage.clickOnEstimation();
		Hooks.logger.info("click on get estimation");
	}

	@When("I should enter name, email, phone number and check the agree checkbox")
	public void i_should_enter_name_email_phone_number_and_check_the_agree_checkbox() throws InterruptedException {

		currentData = Hooks.excelData.get(Hooks.currentRowIndex);
		String name = currentData.get("name");
		String email = currentData.get("email");
		String phone = currentData.get("phone");
		loginPage.typeName(name);
		Hooks.logger.info("enterde name");
		loginPage.typeEmail(email);
		Hooks.logger.info("enterd email");
		loginPage.typePhone(phone);
		Hooks.logger.info("entered phone number");
		loginPage.manualIntervention();
		Hooks.logger.info("wating to enter captcha");
	}

	@When("I click on Signup")
	public void i_click_on_signup() {
		loginPage.clcikSignUp();
		Hooks.logger.info("click on signup");
	}

	@When("I enter OTp")
	public void i_enter_o_tp() throws InterruptedException {
		Hooks.logger.info("wating to enter otp");
		loginPage.manualIntervention();
		loginPage.clickOnContinue();
		Hooks.logger.info("Clicked on continue");
	}

	@Then("I should able to get the estimation price")
	public void i_should_able_to_get_the_estimation_price() {
		try {
			Hooks.logger.info("Got the estimation");
			Hooks.test.pass(" propworth estimation test pass");
			Assert.assertSame(propWorthPage.getPropworthEstimation(), "Estimation");
		} catch (AssertionError e) {
			Hooks.logger.error("esimation didn't displayed");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("propworth estimation test failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}
	}

	@Then("I should see an error message Enter valid total no. of floor")
	public void i_should_see_an_error_message_enter_valid_total_no_of_floor() {
		String error = propWorthPage.getErrorMsg();
		try {
			Hooks.logger.info("error message displayed");
			Hooks.test.pass(" propworth error message test pass");
			Assert.assertEquals(error, "Enter valid total no. of floor");
		} catch (AssertionError e) {
			Hooks.logger.error("error message didn't displayed");
			String screenshotPath = ScreenshotUtil.captureScreenshot(DriverSetup.getDriver(),
					"redirected to the home page test");
			screenshotPath = screenshotPath.replace("\"", "/");
			Hooks.test.fail("propworth erroe message test failed",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
			throw e;
		}
	}
}
