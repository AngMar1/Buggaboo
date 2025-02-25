# Bugaboo Test Framework

## Overview
This is a test automation framework built using **Java, Selenium WebDriver, Cucumber, and Gherkin**. The framework automates the following test scenarios on [Bugaboo's website](https://www.bugaboo.com/us-en):

Feature: Cart Page
Scenario 1: Close Cookie Pop-up and Click on Cart
gherkin
Copy
Given I am on the homepage
When I close the cookie pop-up by clicking the accept only functional button
And I click on the cart icon
Then I should be navigated to the cart page
Purpose: Verifies that the user can close the cookie pop-up and navigate to the cart page.
Scenario 2: Add an In-Stock Stroller to Cart and Verify Cart Modal
gherkin
Copy
Given I am on the homepage
And I navigate to the strollers shop page
When I check if the first stroller is in stock and click "View more"
And I should be on the product detail page
And I scroll down and click "Add to cart"
Then I should see "Added to your shopping cart" in the modal
Purpose: Verifies that an in-stock stroller can be added to the cart and confirms the appearance of the cart modal.
Feature: User Login
Scenario: User Successfully Logs In and Logs Out
gherkin
Copy
Given I am on the login page
When I enter my credentials
Then I should be logged in successfully

Purpose: Verifies that the user can successfully log in to the website.

## Tech Stack
- **Java**: Programming language for writing test scripts.
- **Selenium WebDriver**: Browser automation tool.
- **Cucumber**: Behavior-Driven Development (BDD) framework.
- **Gherkin**: Defines test scenarios in a human-readable format.
- **JUnit**: Test runner for executing Cucumber tests.

## Project Structure
```
Buggaboo/
│-- src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pageobjects/          # Page Object Models
│   ├── test/
│   │   ├── java/
│   │   │   ├── stepdefinitions/  # Cucumber Step Definitions
│   │   │   ├── runners/         # Cucumber Test Runner
│-- pom.xml  # Maven dependencies
│-- README.md
```

## Setup Instructions
### Prerequisites
- Install **Java 11** or later.
- Install **Maven**.
- Install a **web driver** (e.g., ChromeDriver for Chrome, GeckoDriver for Firefox).

### Clone the Repository
```sh
git clone https://github.com/AngMar1/Buggaboo.git
cd Buggaboo

### Install Dependencies
Run the following command to install dependencies:
```sh
mvn clean install
```

## Running the Tests
Execute the tests using Maven:
```sh
mvn test
```

## Maven Dependencies are present in (`pom.xml`) file 

## Test Reports
After execution, test reports will be generated in `target/cucumber-reports/`.


