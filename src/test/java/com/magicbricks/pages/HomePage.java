package com.magicbricks.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magicbricks.driverSetup.DriverSetup;
import com.magicbricks.locatorstore.HomePageLocator;

public class HomePage {
	private WebDriver driver;
	WebDriverWait wait;
	

	// Constructor initializes WebDriver
	public HomePage() {
		this.driver = DriverSetup.getDriver();
		// String secs = PropertiesReader.getConfigProperty("waittimeout");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public String typeLocation(String location, String searchXpath) {
		By suggestion = By.xpath("//div[@id='serachSuggest']/div[" + searchXpath + "]");
		WebElement buy = wait.until(ExpectedConditions.elementToBeClickable(HomePageLocator.buybutton));
		buy.click();
		WebElement defaultvalue = wait
				.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocator.finddefaultlocation));
		defaultvalue.click();
		driver.findElement(HomePageLocator.removedefalutlocation).click();
		WebElement enterloc = driver.findElement(HomePageLocator.enterlocationfield);
		enterloc.sendKeys(location);

		WebElement locsugg = wait.until(ExpectedConditions.visibilityOfElementLocated(suggestion));
		Actions actions = new Actions(driver);
		actions.moveToElement(locsugg).click().perform();
		
		return driver.findElement(HomePageLocator.getsearchvalue).getDomAttribute("value");

	}

	public void clickOnSearch() {
		driver.findElement(HomePageLocator.searchbutton).click();
	}

	public void selectVilla() {
		driver.findElement(HomePageLocator.defaultproperty).click();
		WebElement propertyflat = wait.until(ExpectedConditions.elementToBeClickable(HomePageLocator.flat));

		Actions actions = new Actions(driver);
		actions.moveToElement(propertyflat).click().perform();
	}

	public void clickOnProfile() {
		try {
			WebElement popUp = wait.until(ExpectedConditions.presenceOfElementLocated(HomePageLocator.loginPopUp));
			Actions action = new Actions(driver);
			action.moveToElement(popUp).click().perform();
		} catch (Exception e) {
			System.out.println("Not found");
		}
	}

	public void clickOnSignOut() {
		WebElement signOut = wait.until(ExpectedConditions.elementToBeClickable(HomePageLocator.logOut));
		signOut.click();
	}

	public void clickOnBudget() {
		driver.findElement(HomePageLocator.budget).click();
	}

	public void typeMin(String min_budget) {
		WebElement minValue = wait.until(ExpectedConditions.elementToBeClickable(HomePageLocator.minVal));
		minValue.sendKeys(min_budget);
	}

	public void typeMax(String max_budget) {
		WebElement maxValue = wait.until(ExpectedConditions.elementToBeClickable(HomePageLocator.maxVal));
		maxValue.sendKeys(max_budget);
	}

	public void clickOnBuy() {
		WebElement buyBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(HomePageLocator.mainBuybutton));
		if (buyBtn.isDisplayed()) {
			Actions action = new Actions(driver);
			action.moveToElement(buyBtn).perform();
		} else {
			System.out.println("Buy button is not visible.");
		}
	}

	public void clickOnProp() {
		WebElement clickPropWorthy = wait.until(ExpectedConditions.elementToBeClickable(HomePageLocator.clickProp));
		clickPropWorthy.click();
	}

	public void clickOnPremiun() {
		WebElement clickPremiun = wait.until(ExpectedConditions.elementToBeClickable(HomePageLocator.clickPremium));
		clickPremiun.click();
	}
}
