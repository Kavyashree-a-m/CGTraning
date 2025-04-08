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

Feature: Property Search and Filter
As a buyer I must able to search, apply filter so that i can buy a desired property

@RegressionTesting
   Scenario Outline: I search for a property with location
    Given I am on the buy search page
    When I enter the location 
    And I click on the Search button
    Then the system should display a list of properties in the entered location
    
@RegressionTesting
  Scenario: I search for only Villas
    Given I am on the buy search page
    When I enter the location 
    And I select the type of property Villa
    And I click on the Search button
    Then the system should display a list of selected property type
    
@RegressionTesting
  Scenario: Search properties within a specified budget range
    Given I am on the buy search page
    When I enter the location 
    And I enter the minimum budget
    And I enter the maximum budget
    And I click on the Search button
    Then the system should display properties with prices between the range entered
  
    