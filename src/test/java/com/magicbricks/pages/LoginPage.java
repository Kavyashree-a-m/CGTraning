package com.magicbricks.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magicbricks.driverSetup.DriverSetup;
import com.magicbricks.locatorstore.LoginPageLocator;

public class LoginPage {
	private WebDriver driver;
	WebDriverWait wait;

	// Constructor initializes WebDriver
	public LoginPage() {
		this.driver = DriverSetup.getDriver();
		// String secs = PropertiesReader.getConfigProperty("waittimeout");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void clickOnBuyer() {
		WebElement buyeroption = driver.findElement(LoginPageLocator.buyer);
		if (!buyeroption.isSelected()) {
			buyeroption.click();
		} else {
			System.out.println("Buyer is already selected");
		}
	}

	public void typeMobileNumber(String mobilenumber) {
		WebElement mobilenumberfield = wait.until(ExpectedConditions.elementToBeClickable(LoginPageLocator.mobile));
		mobilenumberfield.sendKeys(mobilenumber);
	}

	public void typeCaptcha() {
		driver.findElement(LoginPageLocator.capctha).click();
	}

	public void manualIntervention() throws InterruptedException {
		Thread.sleep(45000);
	}

	public void clickOnNext() {
		driver.findElement(LoginPageLocator.nextbutton).click();
	}

	public void clickOnContinue() {
		driver.findElement(LoginPageLocator.continuebtn).click();
	}

	public String isHomePageLoaded() {
		return driver.getCurrentUrl();
	}

	public String getErrorMsg() {
		WebElement error = driver.findElement(LoginPageLocator.errormsg);
		return error.getText();
	}

	public void typeName(String name) {
		driver.findElement(LoginPageLocator.name).sendKeys(name);
	}

	public void typeEmail(String email) {
		driver.findElement(LoginPageLocator.email).sendKeys(email);
	}

	public void typePhone(String phone) {
		driver.findElement(LoginPageLocator.phone).sendKeys(phone);
		driver.findElement(LoginPageLocator.checkBox).click();
		driver.findElement(LoginPageLocator.capcthaProp).click();
	}

	public void clcikSignUp() {
		driver.findElement(LoginPageLocator.signUp).click();
	}
	
	public String captchError()
	{
		return driver.findElement(LoginPageLocator.captchaerror).getText();
	}

}
