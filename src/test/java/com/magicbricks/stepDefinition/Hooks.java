package com.magicbricks.stepDefinition;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.magicbricks.driverSetup.DriverSetup;
import com.magicbricks.parameters.PropertiesReader;
import com.magicbricks.utils.ExcelUtils;
import com.magicbricks.utils.ExtentManager;
import com.magicbricks.utils.ScreenshotUtil;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;

public class Hooks {
	public static ExtentReports extent;
	public static ExtentTest test;
	static DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
	public static Logger logger;
	public static List<Map<String, String>> excelData;
	public static int currentRowIndex = 0;
	private static String scenarioName;

	@BeforeAll
	public static void setUpSuite() {
		extent = ExtentManager.getInstance();
		logger = LoggerFactory.getLogger(Hooks.class);
	}

	@AfterAll
	public static void tearDownSuite() {
		if (extent != null) {
			extent.flush();
		}
	}

	@Before
	public void setup(Scenario scenario) {
		scenarioName = scenario.getName();
		test = extent.createTest(scenarioName);
		test.info("Starting Scenario: " + scenarioName);
		DriverSetup.getDriver(); // Initialize driver before scenario
		excelData = ExcelUtils.readExcelData(PropertiesReader.getPathProperty("exceldatapath"),
				"PropWorthEstimationData");
		currentRowIndex = 0; // Initialize the pointer at the beginning.
	}

	@After
	public void teardown(Scenario scenario) {
		
		currentRowIndex++;
		DriverSetup.quitDriver(); // Quit driver after scenario
	}
}
