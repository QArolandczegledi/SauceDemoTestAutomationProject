package com.demoautomationprojecttests.tests;

import com.demoautomationproject.util.LoggerUtil;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(description = "Login testing")
    public void loginTest() {
        LoggerUtil.info("Starting login test.");
        performLogin();
    }
}
