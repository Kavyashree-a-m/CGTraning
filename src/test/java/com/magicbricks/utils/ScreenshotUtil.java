package com.magicbricks.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenshotUtil {

	private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtil.class);

    public static String captureScreenshot(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = "screenshots/" + testName + "_" + timestamp + ".png";

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destPath = System.getProperty("user.dir") + "/" + screenshotPath;
        File destFile = new File(destPath);

        try {
            FileHandler.copy(srcFile, destFile);
            logger.info("Screenshot captured: {}", screenshotPath);
        } 
        catch (IOException e) {
            logger.error("Error capturing screenshot: {}", e.getMessage());
        }
        return destPath;
    }
}
