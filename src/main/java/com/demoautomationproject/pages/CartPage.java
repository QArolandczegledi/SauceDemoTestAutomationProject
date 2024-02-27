package com.demoautomationproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement checkoutButton() {
        return findElement(By.id("checkout"));
    }
}
