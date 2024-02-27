package com.demoautomationproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage extends BasePage {

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public WebElement finishButton() {
        return findElement(By.id("finish"));
    }

    public double itemTotal() {
        WebElement itemTotalElement = findElement(By.cssSelector(".summary_subtotal_label"));
        String itemTotalElementAsString = itemTotalElement.getText();
        String splittedItemTotalElementAsString = itemTotalElementAsString.split("\\$")[1];
        return Double.parseDouble(splittedItemTotalElementAsString);
    }
}
