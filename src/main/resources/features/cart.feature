Feature: Cart Page

  Scenario: Close popup and click on cart
    Given I am on the homepage
    When I click on the cart
    Then I should see the cart page

  Scenario: Add an in-stock stroller by viewing more and verify cart modal
    Given I am on the homepage
    And I navigate to the strollers shop page
    When I check if the first stroller is in stock and click "View more"
    And I should be on the product detail page
    And I scroll down and click "Add to cart"
    Then I should see "Added to your shopping cart" in the modal

  Scenario: Sort by most popular
    Given I am on the homepage
    And I navigate to the strollers shop page
    When I sort the strollers by most popular
    Then I should see most popular option on dropdown