package com.demoautomationproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage extends BasePage {

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public WebElement completedCheckoutMessage() {
        return findElement(By.cssSelector("h2.complete-header"));
    }
}