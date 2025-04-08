package com.magicbricks.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
    features = "src/test/resources/Features",  
    glue = "com.magicbricks.stepDefinition",                 
    plugin = { "pretty", "html:target/cucumber-reports.html" },  
    monochrome = true,                          
    tags = "@test1"                       
)
public class TestNGRunnerParallel extends AbstractTestNGCucumberTests 
{

    // This enables parallel execution
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
	
}





