package com.demoautomationproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class InventoryPage extends BasePage {

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public WebElement cartIcon() {
        return findElement(By.id("shopping_cart_container"));
    }

    public WebElement hamburgerMenuButton() {
        return findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement logOutLink() {
        return findElement(By.id("logout_sidebar_link"));
    }

    public WebElement firstItemAddToCartButton() {
        return findElement(By.id("add-to-cart-sauce-labs-backpack"));
    }

    public WebElement secondItemAddToCartButton() {
        return findElement(By.id("add-to-cart-sauce-labs-bike-light"));
    }

    public WebElement thirdItemAddToCartButton() {
        return findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt"));
    }

    public WebElement forthItemAddToCartButton() {
        return findElement(By.id("add-to-cart-sauce-labs-fleece-jacket"));
    }

    public WebElement fifthItemAddToCartButton() {
        return findElement(By.id("add-to-cart-sauce-labs-onesie"));
    }

    public WebElement sixthItemAddToCartButton() {
        return findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)"));
    }

    public WebElement sortingDropDownField() {
        return findElement(By.cssSelector("select.product_sort_container"));
    }

    public void sortItems(String sortingType) {
        Select sortSelect = new Select(sortingDropDownField());
        sortSelect.selectByValue(sortingType);
    }

    public double itemPrice(String itemName) {
        try {
            WebElement priceElement = driver.findElement(By.xpath("//div[contains(@class,'inventory_item')][.//div[contains(text(),'" + itemName + "')]]//div[contains(@class,'inventory_item_price')]"));
            String priceAsString = priceElement.getText().replaceAll("[^\\d.]", "");
            return Double.parseDouble(priceAsString);
        } catch (NoSuchElementException e) {
            logger.error(itemName + " price is not found!", e);
            return Double.NaN;
        }
    }

    public static final String SORT_A_TO_Z = "az";
    public static final String SORT_Z_TO_A = "za";
    public static final String SORT_LOW_TO_HIGH = "lohi";
    public static final String SORT_HIGH_TO_LOW = "hilo";
}
