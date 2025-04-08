package com.magicbricks.driverSetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import com.magicbricks.parameters.PropertiesReader;

public class DriverSetup {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		if (driver.get() == null) {

			String browser = PropertiesReader.getConfigProperty("browser");
			
			switch (browser.trim().toLowerCase()) {
			case "edge":
				EdgeOptions edgeoptions = new EdgeOptions();
//				edgeoptions.addArguments("--headless");
//			//** Manage notifivations in from browsers**
				edgeoptions.addArguments("--disable-notifications");
				driver.set(new EdgeDriver(edgeoptions));

				// driver.set(new EdgeDriver());
				
				break;
			case "chrome":
				ChromeOptions chromeoptions = new ChromeOptions();
//			chromeoptions.addArguments("--headless");
//			//** Manage notifivations in from browsers**
				chromeoptions.addArguments("--disable-notifications");
				driver.set(new ChromeDriver(chromeoptions));

				// driver.set(new ChromeDriver());
				
				break;
			default:
				System.out.println(browser + " - Unsupported Browser");
			}
			driver.get().manage().window().maximize();
			driver.get().manage().deleteAllCookies();
		}
		return driver.get();
	}

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
