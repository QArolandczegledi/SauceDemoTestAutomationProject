package com.demoautomationproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutYourInfoPage extends BasePage {

    public CheckoutYourInfoPage(WebDriver driver) {
        super(driver);
    }

    public WebElement firstNameField() {
        return findElement(By.id("first-name"));
    }

    public WebElement lastNameField() {
        return findElement(By.id("last-name"));
    }

    public WebElement postalCodeField() {
        return findElement(By.id("postal-code"));
    }

    public WebElement continueButton() {
        return findElement(By.id("continue"));
    }

    public void fillCheckoutYourInfoPage(String firstName, String lastName, String postalCode) {
        firstNameField().sendKeys(firstName);
        lastNameField().sendKeys(lastName);
        postalCodeField().sendKeys(postalCode);
    }
}
