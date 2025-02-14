Feature: Google Search Functionality
  As a user
  I want to search on Google
  So that I can find information about Selenium Cucumber implementation

  Scenario: Search for Selenium Cucumber implementation
    Given I navigate to Google search page
    When I search for "selenium java cucumber implementation by example"
    Then I should see search results