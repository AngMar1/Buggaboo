Feature: Search Functionality

Scenario: Enable search field and search for a product
Given I am on the home page
When I click on the search icon
Then the search field should become enabled
When I type "butterfly rear wheels" in the search field
Then I should see search results related to "butterfly rear wheels"