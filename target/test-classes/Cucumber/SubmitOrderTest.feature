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

  @Regression
  Feature: Purchasing product from eCommerce website
  
  Background: 
  Given I logged in the website
  
  Scenario Outline: Positive test case to test the purchase the flow
    Given I logged in with valid <username> & <password>
    When I select <productName> in cart page
    When submit the order with <productName>
    Then "THANKYOU FOR THE ORDER." message should be displayed

    Examples: 
      |        username        |  password  |   productName   |
      | saikatdas066@gmail.com | me@Test012 | ADIDAS ORIGINAL |
      
