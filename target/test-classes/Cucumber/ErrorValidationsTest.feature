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
Feature: Purchase order negative test scenario
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: User should not be able to login while using wrong credentials
    Given I logged in the website
    When I logged in with valid <username> & <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      |         username       |  password   |
      | saikatdas066@gmail.com |  me@Test000 |
