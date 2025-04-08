package com.magicbricks.pages;

import java.nio.channels.SelectableChannel;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magicbricks.driverSetup.DriverSetup;
import com.magicbricks.locatorstore.PropWorthPageLocator;

public class PropWorthPage {

	private WebDriver driver;
	WebDriverWait wait;

	public PropWorthPage() {
		this.driver = DriverSetup.getDriver();
		// String secs = PropertiesReader.getConfigProperty("waittimeout");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void clickOnGetEstimation()  {
		
		wait.until(ExpectedConditions.elementToBeClickable(PropWorthPageLocator.clickestimatebtn)).click();
	}

	public void switchWindow() {
		String mainWindowHandle = driver.getWindowHandle();
		System.out.println(mainWindowHandle);

		// Get all window handles
		Set<String> allWindowHandles = driver.getWindowHandles();

		// Switch to the new window
		for (String handle : allWindowHandles) {
			if (!handle.equals(mainWindowHandle)) {
				driver.switchTo().window(handle);
//				break;
			}
		}

		System.out.println("prod win   " + driver.getWindowHandle());
	}

	public void typeLocation(String location) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(PropWorthPageLocator.locality)).sendKeys(location);

		wait.until(ExpectedConditions.elementToBeClickable(PropWorthPageLocator.localitydropdown)).click();
	}

	public String clickSubLocality(String sublocality) {
		WebElement dropdownSubLocality = wait
				.until(ExpectedConditions.presenceOfElementLocated(PropWorthPageLocator.subLocalityDropdowm));
		dropdownSubLocality.click();
		Select select = new Select(dropdownSubLocality);
		wait.until(ExpectedConditions.presenceOfElementLocated(PropWorthPageLocator.subLocalityDropdowm));
		select.selectByVisibleText(sublocality);
		dropdownSubLocality.click();
		return driver.findElement(PropWorthPageLocator.sublocalityoption).getText();
	}

	public void clickPropertyType(String property, String bhk) {
		By propertytype = By.xpath("//label[text()='" + property + "']");
		By selectBhk = By.xpath("//label[text()='" + bhk + "']");
		driver.findElement(propertytype).click();
		driver.findElement(selectBhk).click();
	}

	public String typeSquareFt(String sqft) {
		WebElement sqrft = driver.findElement(PropWorthPageLocator.squareFt);
		sqrft.sendKeys(sqft);
		return sqrft.getDomAttribute("value");
	}

	public String clickFloor(String numFloor) {
		WebElement dropdownFloor = wait.until(ExpectedConditions.presenceOfElementLocated(PropWorthPageLocator.floors));
		dropdownFloor.click();
		Select select = new Select(dropdownFloor);
		wait.until(ExpectedConditions.presenceOfElementLocated(PropWorthPageLocator.floors));
		select.selectByVisibleText(numFloor);
		dropdownFloor.click();
		return driver.findElement(PropWorthPageLocator.flooroption).getText();

	}

	public void typeTotalFloor(String floor) {
		driver.findElement(PropWorthPageLocator.totalfloor).sendKeys(floor);
	}

	public void clickInteriors(String interiors) {
		By interior = By.xpath("//label[text()='" + interiors + "']");
		WebElement selectname = driver.findElement(PropWorthPageLocator.addDetails);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectname);
		wait.until(ExpectedConditions.visibilityOfElementLocated(interior)).click();

	}

	public void typeMoneySpendOnIt(String amountSpent) {
		wait.until(ExpectedConditions.elementToBeClickable(PropWorthPageLocator.spendmoney)).sendKeys(amountSpent);

	}

	public void clickOnAdditionalDetails() {
		WebElement addDetail = driver.findElement(PropWorthPageLocator.addDetails);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addDetail);
		addDetail.click();
	}

	public void selectDirection(String direction) {
		By facing = By.xpath("//label[text()='" + direction + "']");
		WebElement scroll = wait.until(ExpectedConditions.visibilityOfElementLocated(PropWorthPageLocator.addDetails));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				PropWorthPageLocator.scrolladd);
		wait.until(ExpectedConditions.visibilityOfElementLocated(facing)).click();

	}

	public void clickOnEstimation() {
		driver.findElement(PropWorthPageLocator.getEstimate).click();
	}

	public String getPropworthEstimation() {
		return driver.findElement(PropWorthPageLocator.propworthestimation).getText();
	}

	public String getErrorMsg() {
		return driver.findElement(PropWorthPageLocator.errormsg).getText();
	}

}
