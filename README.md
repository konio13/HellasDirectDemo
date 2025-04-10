





# HellasDirect demo

HellasDirect demo is a mini custom-made Java automation framework built for testing functional requirements related to
web applications. Specifically, it can be used for testing car registration page (given as html file in the QA exercise). 
It uses [Maven](https://maven.apache.org/) as a project management tool, [TestNG](https://testng.org/) 
for organizing and executing test cases, [Selenium](https://www.selenium.dev/) for interaction with browsers 
and [Allure](https://allurereport.org/) for reporting (additionally [GoogleGuice](https://github.com/google/guice) for
dependency injection and [Bonigarcia](https://github.com/bonigarcia/webdrivermanager) for driver management).

------------------
# Guidelines for test execution
Here you can find a quick guide on how to set up the environment for executing test scenarios using HellasDirect demo.

## Setting up PC
- Clone project (use Git Bash or IntelliJ) from following repository to manage source code locally:
```  
     git clone https://github.com/konio13/HellasDirectDemo
```
- Download and Install Java 1.8

- Open project from an IDE (I used IntelliJ) and let it download all project dependencies

# Project structure
*_Only files/folders of interest are given_

     HellasDirectDemo
        src/main/java/org.hd
            config
              |browserDriver
                DriverManager
              WebDriverUtilities
            data
            formValidation
            messages
            objects
              |bussinessObjects
              |pageObjects
        src/test/java
          |listeners.testng
          |testClasses
          resources
            |htmlSamplePages
            |testSuites
            conf.properties
        pom.xml


Specifically:

     DriverManager           : controls driver sessions (Chrome,Firefox)
     WebDriverUtilities      : contails custom webdriver methods for browser interaction
     data/                   : contains data POJO (lombok) classes for used data (CarRegistrationData)
     formValidation/         : contains Form validation variables
     messages/               : contains Messages class containing validation messages
     bussinessObjects/       : contains web bussiness-objects (sequences of actions)
     pageObjects/            : contains web page-objects (single page actions)
     listeners.testng/       : contains testng hooks
     testClasses/            : contains testng classes (tests)
     testSuites/             : contains testng suites
     htmlSamplePages/        : contains html sample files ( qa-exercise html file included)

     (created on execution)
     -->target/allure-results  : contains allure report files



--------------------------------
# Triggering framework

At this point you are able to execute all test scenarios included in testSuiteDemo suite by the following maven command:
```sh
> mvn clean test -D"suiteXmlFile=src/test/resources/TestSuites/testSuiteDemo.xml"
```     
Previous command:

- execute all tests from suite: testSuiteDemo.xml
- with parallel test execution mode (default: 3 threads) 
- using Chrome as a default browser (open before each testing class)
- creating Allure Report files under "target/allure-results/"

User is free to use additional configuration (check conf.properties file) :


### ( User configuration )
example 1: Run suite, using test FIREFOX browser
```sh
> mvn clean test -D"suiteXmlFile=src/test/resources/TestSuites/testSuiteDemo.xml" -D"TEST_BROWSER=FIREFOX"

```

### ( Parallel execution )
example 2: Run tests, using 2 parallel threads
```sh
> mvn mvn clean test -D"suiteXmlFile=src/test/resources/TestSuites/testSuiteDemo.xml" -D"PARALLEL_THREAD_COUNT=2"

``` 


## Reporting
After test(s) execution, Allure report files are created inside *target/allure-results/* folder. 
To create a visual representation of results, you have to download Allure latest [release](https://github.com/allure-framework/allure2/tags) and
on a system console execute (inside bin folder):
```sh
> allure serve "<absolute path of target/allure-results/ folder>"
```
Test report will automatically appear on your browser. For example:

![Allure report summary](/_tmp/AllureReportSummaryWithFailures.png "Allure report summary sample")

Or in case you want to check specifically suites, tests and their steps:

![Allure report results](/_tmp/AllureReportPositiveResults.png "Allure report summary sample")

Additional report screenshots are given in project's `/_tmp/` folder .

**NOTES**

* A screenshot is attached to report in case of a failure
* In case of an error, all the following test steps are ignored

## ChromeDriver/Geckodriver
Project uses [Bonigarcia](https://github.com/bonigarcia/webdrivermanager) that is there is no need to
download any ChromeDriver and Geckodriver file. It automatically finds Chrome or Firefox browser version of the executed
environment and download the related driver versions.


--------------------------------
# Next features...

- Add remote support to [Selenium Grid](https://www.selenium.dev/documentation/grid/) for not being dependent on your device browser
- Create Jenkins file: triggering HellasDirect demo test execution and report collection (containing additional options
  for project branch, browser, parallel execution etc.)
- Create Docker image containing Linux, Java, required libraries, HellasDirect demo etc...
- Docker-compose/Dockerfile creating previous image(s)

