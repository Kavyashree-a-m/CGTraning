package com.magicbricks.runner;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
	    features = "src/test/resources/Features",  
	    glue = "com.magicbricks.stepDefinition",                 
	    plugin = { "pretty" },  
	    monochrome = true
	    //tags = "@RegressionTesting and not @SmokeTesting"
	)
	public class TestRunner extends AbstractTestNGCucumberTests 
	{
		@BeforeClass
	    public static void setup() {
	        String cpath = com.magicbricks.parameters.PropertiesReader.getPathProperty("cucumberreportspath"); 
	        System.setProperty("cucumber.plugin", "pretty," + cpath);
	    }
	}
