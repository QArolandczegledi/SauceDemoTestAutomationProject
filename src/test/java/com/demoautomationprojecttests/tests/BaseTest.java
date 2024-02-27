package com.demoautomationprojecttests.tests;

import com.demoautomationproject.config.TestConfiguration;
import com.demoautomationproject.pages.InventoryPage;
import com.demoautomationproject.pages.LoginPage;
import com.demoautomationproject.util.LoggerUtil;
import com.demoautomationproject.util.WebDriverUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.demoautomationproject.util.AllureUtils.attachScreenshotToAllure;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        try {
            driver = WebDriverUtils.createChromeWebDriver();
            initializeWait();
            WebDriverUtils.navigateToUrl(driver, TestConfiguration.getProperty("url"));
            WebDriverUtils.maximizeWindow(driver);
            LoggerUtil.info("WebDriver initialized and navigated to URL: " + TestConfiguration.getProperty("url"));
        } catch (Exception e) {
            LoggerUtil.error("Error during WebDriver setup: " + e.getMessage());
            throw e;
        }
    }

    protected void initializeWait() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TestConfiguration.getWaitTime()));
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        String testStatus = result.getStatus() == ITestResult.FAILURE ? "FAILED" : "PASSED";
        captureScreenshot(result.getName() + "_" + testStatus);
        if (result.getStatus() == ITestResult.FAILURE) {
            LoggerUtil.error("Test failed: " + result.getName());
        } else {
            LoggerUtil.info("Test passed: " + result.getName());
        }
        if (driver != null) {
            driver.quit();
        }
    }

    public void performLogin() {
        LoginPage loginPage = new LoginPage(driver);
        String password = TestConfiguration.getProperty("password");
        String userName = TestConfiguration.getProperty("userName");
        LoggerUtil.info("Attempting to login with user: " + userName);
        try {
            loginPage.userNameField().sendKeys(userName);
            loginPage.passwordNameField().sendKeys(password);
            loginPage.loginButton().click();
            waitForElementToBeVisible(new InventoryPage(driver).hamburgerMenuButton());
            LoggerUtil.info("Login successful for user: " + userName);
        } catch (Exception e) {
            LoggerUtil.error("Login failed: " + e.getMessage());
            throw new RuntimeException("Login failed for user: " + userName, e);
        }
    }

    private void captureScreenshot(String testName) {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
        String destFileName = "screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";
        File destFile = new File(destFileName);
        try {
            FileUtils.copyFile(sourceFile, destFile);
            LoggerUtil.info("Screenshot captured: " + destFileName);
            attachScreenshotToAllure(destFile);
        } catch (IOException e) {
            LoggerUtil.error("An error occurred while capturing screenshot: " + e.getMessage());
        }
    }
}
