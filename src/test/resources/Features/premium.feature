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
Feature: Search for premium houses,verify links and calculate EMI

@RegressionTesting
  Scenario: As a buyer, I want to search for premium houses and check that some links are present
    Given I am on the buy search page
    When I click on Buy
    And I click on Premium House
    Then I should verify all the links are present
    
@RegressionTesting   
 Scenario: As a buyer, I want to verify the presence of the pie chart in the EMI Calculator
    Given I am on the buy search page
    When I click on Buy
    And I click on Premium House
    And I click on EMI Calculator
    Then I should see the pie chart displayed
   
