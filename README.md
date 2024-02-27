### Demo Test Automation Project for [www.saucedemo.com](https://www.saucedemo.com/)

### Introduction
This repository hosts a demo test automation project for the dummy webshop at [www.saucedemo.com](https://www.saucedemo.com/). This project is designed to showcase my test automation skills, it uses publicly available credentials for login, ensuring safety for demonstration purposes.

### Technologies Used

- Java
- Selenium
- TestNG
- Maven
- Allure
- log4j2

### Objective

Practicing and strengthening the Java language and Selenium framework. Solution for managing tests, placing them into a suite, and running them together. Implementation of logging, which can greatly assist in finding a potential error. Taking screenshots at the end of successful tests to reduce the risk of overlooking falsely passed test results. Furthermore, reporting is an important part of the test automation process, for which I used Allure.

### Prerequisites

Before running the smoke test suite, ensure the following are installed on your system:

- Java JDK (version 8 or higher)

- Maven (3.6.0 or higher)

- Chrome installed on your machine

The project utilizes Selenium with ChromeDriver in headless mode for testing. Modifications can be made in the WebDriverUtils class for further customization.

### Running the Tests

To run the smoke test suite, clone the project and execute the following command:

mvn test -DsuiteXmlFile="src/test/resources/smoke-test-suite.xml"

### Viewing the Test Reports Locally

After running the tests, generate and serve the test report using the following command in your terminal:

allure serve path-to-your-allure-results-directory

If you prefer not to download the entire project, you can still find useful information the code in this repository. The app.log file and the screenshots folder contain detailed logs and visual evidence of the tests. These resources provide insights into the test execution and outcomes.
