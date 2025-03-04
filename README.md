# Bugaboo Test Framework

## Overview
This is a test automation framework built using **Java, Selenium WebDriver, Cucumber, and Gherkin**. The framework automates the following test scenarios on [Bugaboo's website](https://www.bugaboo.com/us-en):


### Test Scenarios

#### Feature: Cart Page

**Scenario 1: Close Cookie Pop-up and Click on Cart**

```gherkin
Given I am on the homepage
When I close the cookie pop-up by clicking the accept only functional button
And I click on the cart icon
Then I should be navigated to the cart page
```
**Scenario 2: Add an In-Stock Stroller to Cart and Verify Cart Modal**

```gherkin
Given I am on the homepage
And I navigate to the strollers shop page
When I check if the first stroller is in stock and click "View more"
And I should be on the product detail page
And I scroll down and click "Add to cart"
Then I should see "Added to your shopping cart" in the modal
```  
**Scenario 3: Sort by most popular**
```gherkin
Scenario: Sort by most popular
Given I am on the homepage
And I navigate to the strollers shop page
When I sort the strollers by most popular
Then I should see most popular option on dropdown
```

#### Feature: Login Page
**Scenario 4: Successful login**
 
```gherkin 
Given I am on the login page
When I enter my credentials
Then I should be logged in successfully
``` 

#### Feature: Search Functionality
**Scenario 5: Enable search field and search for a product**

```gherkin
Given I am on the home page
When I click on the search icon
Then the search field should become enabled
When I type "butterfly rear wheels" in the search field
Then I should see search results related to "butterfly rear wheels"
```
  
## Tech Stack
- **Java**: Programming language for writing test scripts.
- **Selenium WebDriver**: Browser automation tool.
- **Cucumber**: Behavior-Driven Development (BDD) framework.
- **Gherkin**: Defines test scenarios in a human-readable format.
- **JUnit**: Test runner for executing Cucumber tests.

## Project Structure
```text
Buggaboo/
└── src/
   └── main/
      └── java/
           ├── pageobjects/       # Page Object Models (represent UI interactions)
           ├── stepdefinitions/   # Cucumber Step Definitions (test steps)
           └── runners/           # Cucumber Test Runner (test execution)
pom.xml         # Maven dependencies configuration
README.md       # Project documentation
```

## Setup Instructions
### Prerequisites
- Install **Java 11** or later.
- Install **Maven**.
- Install a **web driver** (e.g., ChromeDriver for Chrome, GeckoDriver for Firefox).

### Clone the Repository

To clone the repository, run the following commands:

```sh
git clone https://github.com/AngMar1/Buggaboo.git
cd Buggaboo
```

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


