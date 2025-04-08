package com.magicbricks.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.magicbricks.driverSetup.DriverSetup;
import com.magicbricks.locatorstore.PropertyForSaleLocator;

public class PropertyForSale {
	private WebDriver driver;
	WebDriverWait wait;

	public PropertyForSale() {
		this.driver = DriverSetup.getDriver();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public List<WebElement> getAddressDisplayed() {
		return driver.findElements(PropertyForSaleLocator.gettextofadd);
	}

	public boolean getTextToValidateLocation(String location) {
		List<WebElement> address = getAddressDisplayed();

		for (WebElement item : address) {
			String loc = item.getText();
			if (!loc.contains(location)) {
				return false;
			}
		}
		return true;
	}

	public boolean getTextToValidateVilla(String property) {
		List<WebElement> address = getAddressDisplayed();

		for (WebElement item : address) {
			String loc = item.getText();
			if (!loc.contains(property)) {
				return false;
			}
		}
		return true;
	}

	public List<WebElement> getPrice() {
		return driver.findElements(PropertyForSaleLocator.textprice);
	}

	public boolean checkRange(String min_value, String max_value) {
		List<WebElement> price = getPrice();

		// Convert min_value and max_value to numerical values
		double minvalue = Double.parseDouble(min_value);
		double maxvalue = Double.parseDouble(max_value);

		for (WebElement item : price) {
			String cost = item.getText();

			if (cost.startsWith("₹")) {
				cost = cost.substring(1).trim();
			}

			try {
				if (cost.contains("Lac")) {
					cost = cost.replace("Lac", "").trim();
					double value = Double.parseDouble(cost);
					cost = String.valueOf(value * 100000);
				} else if (cost.contains("Cr")) {
					cost = cost.replace("Cr", "").trim();
					double value = Double.parseDouble(cost);
					cost = String.valueOf(value * 10000000);
				} else {
					throw new IllegalArgumentException("Input does not contain 'lac' or 'cr'");
				}
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}

			// Convert cost to numerical value for comparison
			double costValue = Double.parseDouble(cost);

			// Check if cost is within the range
			if (costValue < minvalue || costValue > maxvalue) {
				return false;
			}
		}
		return true;
	}
}


