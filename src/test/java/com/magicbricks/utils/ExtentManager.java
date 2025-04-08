package com.magicbricks.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
    static DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
        	
            extent = new ExtentReports();
            String reportPath = "reports/TestReport_"  
            		+ LocalDateTime.now().format(dateformat) + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            // Optionally, configure spark here (set document title, report name, etc.)
            extent.attachReporter(sparkReporter);
            
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setDocumentTitle("Test Report");
            sparkReporter.config().setReportName("Test Execution");
        }
        return extent;
    }
}
