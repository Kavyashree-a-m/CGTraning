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
@tag
Feature: User Login
As a user I must login so that I can buy a property

@SmokeTesting
  Scenario Outline: I log in with valid credentials
    Given I am on the login page
    When I select Buyer button as the user type
    And I enter the mobile number "<mobile number>"
    And I enter the captcha
    And I click on the Next button
    And I enter the OTP
    Then I should be logged in successfully
    Examples:
      | mobile number |
      | 6361518492    |
      
@RegressionTesting
  Scenario Outline: I log in with invalid credentials
    Given I am on the login page
    When I select Buyer button as the user type
    And I enter the invalid mobile number "<mobile number>"
    Then the system should display an error message indicating that Mobile number should be of min. ten digits. Please re-enter.
    Examples:
      | mobile number |
      | 67898         |
      
@RegressionTesting
  Scenario Outline: I log in with invalid credentials
    Given I am on the login page
    When I select Buyer button as the user type
    And I enter the mobile number "<mobile number>"
    And I enter the invalid captcha
    Then the system should display an error message indicating that Please enter valid captcha.
    Examples:
      | mobile number |
      | 6361518492    |
  
  
