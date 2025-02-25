Feature: User Login

  Scenario: User successfully logs in and logs out
    Given I am on the login page
    When I enter my credentials
    Then I should be logged in successfully
