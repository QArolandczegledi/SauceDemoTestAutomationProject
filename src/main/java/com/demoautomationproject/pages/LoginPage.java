package com.demoautomationproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement userNameField() {
        return findElement(By.id("user-name"));
    }

    public WebElement passwordNameField() {
        return findElement(By.id("password"));
    }

    public WebElement loginButton() {
        return findElement(By.id("login-button"));
    }
}