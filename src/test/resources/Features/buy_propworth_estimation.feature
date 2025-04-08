#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
#@tag
Feature: Verify proper estimation of price
  As a buyer, I want to get a proper estimation of price based on entered details

@RegressionTesting
  Scenario Outline: User gets proper estimation of price based on entered details
    Given I am on the buy search page
    When I click on Buy
    And I click on PropWorth
    And I enter location location
    And I select sublocality from the sublocality dropdown
    And I select propertyType and BHK from the property type and BHK dropdowns
    And I enter super built-up area 
    And I select floor from the floor dropdown
    And I enter total number of floors totalFloors
    And I select interiors 
    And I enter amountSpent in the amount spent on interiors field
    And I click on Additional Details
    And I click on Get Estimation
    And I should enter name, email, phone number and check the agree checkbox
    And I click on Signup
    And I enter OTp
    Then I should able to get the estimation price 
    
@RegressionTesting
    Scenario Outline: User gets an error message for missing total number of floors
    Given I am on the buy search page
    When I click on Buy
    And I click on PropWorth
    And I enter location location
    And I select sublocality from the sublocality dropdown
    And I select propertyType and BHK from the property type and BHK dropdowns
    And I enter super built-up area 
    And I select floor from the floor dropdown
    And I select interiors 
    And I enter amountSpent in the amount spent on interiors field
    And I click on Get Estimation
    Then I should see an error message Enter valid total no. of floor
