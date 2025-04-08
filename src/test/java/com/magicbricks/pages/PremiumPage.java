package com.magicbricks.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magicbricks.driverSetup.DriverSetup;
import com.magicbricks.locatorstore.PremiumPageLocator;

public class PremiumPage {
	private WebDriver driver;
	WebDriverWait wait;
	// String parentWString = HomePage.parentWindow;

	// Constructor initializes WebDriver
	public PremiumPage() {
		this.driver = DriverSetup.getDriver();
		// String secs = PropertiesReader.getConfigProperty("waittimeout");
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	public void windoHandel(String parentWindow) {
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
//			  
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				driver.switchTo().window(window);

				break;
			}
		}

	}

	public boolean checkText() {
		WebElement waitJs = wait.until(ExpectedConditions.visibilityOfElementLocated(PremiumPageLocator.jsWait));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", waitJs);

		List<String> list1 = Arrays.asList("Rates & Trends", "EMI Calculator", "Investment Hotspot",
				"Research Insights");
		List<String> list2 = new ArrayList<String>();
		List<WebElement> elements = driver.findElements(PremiumPageLocator.testAd);
		for (WebElement element : elements) {
			String txt = element.getText();
			list2.add(txt);
		}
		if (list1.equals(list2)) {
			return true;
		}
		return false;
	}

	public void clickOnEMI() {
		WebElement waitJs = wait.until(ExpectedConditions.visibilityOfElementLocated(PremiumPageLocator.jsWait));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", waitJs);

		wait.until(ExpectedConditions.elementToBeClickable(PremiumPageLocator.emi)).click();
	}

	public boolean piechartPresent()
	{
		boolean isPresent = driver.findElements(PremiumPageLocator.piechart).size() > 0;
		return isPresent;
	}
}
