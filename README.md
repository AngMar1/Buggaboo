# Bugaboo Test Framework

## Overview
This is a test automation framework built using **Java, Selenium WebDriver, Cucumber, and Gherkin**. The framework automates the following test scenario on [Bugaboo's website](https://www.bugaboo.com/us-en):

1. Navigate to the Bugaboo website.
2. Close the cookie pop-up by clicking on accept only functional button.
3. Click on the cart icon.

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


