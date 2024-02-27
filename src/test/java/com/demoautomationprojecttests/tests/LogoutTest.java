package com.demoautomationprojecttests.tests;

import com.demoautomationproject.pages.InventoryPage;
import com.demoautomationproject.pages.LoginPage;
import com.demoautomationproject.util.LoggerUtil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    @BeforeMethod
    public void beforeTestSetUp() {
        LoggerUtil.info("Setting up login before the test.");
        if (isLoggedOut()) {
            performLogin();
            LoggerUtil.info("Login performed successfully.");
        } else {
            LoggerUtil.info("Already logged in, proceeding to test.");
        }
    }

    @Test(description = "Logout testing")
    public void logOut() {
        try {
            InventoryPage inventoryPage = new InventoryPage(driver);
            LoginPage loginPage = new LoginPage(driver);

            LoggerUtil.info("Attempting to log out.");
            inventoryPage.hamburgerMenuButton().click();
            wait.until(ExpectedConditions.visibilityOf(inventoryPage.logOutLink()));
            inventoryPage.logOutLink().click();
            wait.until(ExpectedConditions.visibilityOf(loginPage.loginButton()));
            LoggerUtil.info("Logout successful.");
            Assert.assertTrue(loginPage.loginButton().isDisplayed(), "Logout test did not complete successfully.");
        } catch (Exception e) {
            LoggerUtil.error("Logout test failed: " + e.getMessage());
            Assert.fail("Logout test failed: " + e.getMessage());
        }
    }

    private boolean isLoggedOut() {
        LoginPage loginPage = new LoginPage(driver);
        try {
            return loginPage.loginButton().isDisplayed();
        } catch (Exception e) {
            LoggerUtil.info("Login button is not visible, assuming logged in.");
            return false;
        }
    }
}
