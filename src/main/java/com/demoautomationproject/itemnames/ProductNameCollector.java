package com.demoautomationproject.itemnames;

import com.demoautomationproject.pages.InventoryPage;
import com.demoautomationproject.util.LoggerUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;
import java.util.stream.Collectors;

public class ProductNameCollector {
    private WebDriver driver;
    private InventoryPage inventoryPage;

    public ProductNameCollector(WebDriver driver) {
        this.driver = driver;
        this.inventoryPage = new InventoryPage(driver);
    }

    public Map<String, Double> productsNameWithPrice() {
        Map<String, Double> productsNameWithPrice = new LinkedHashMap<>();
        List<WebElement> productElements = driver.findElements(By.cssSelector(".inventory_item_name"));
        for (WebElement item : productElements) {
            String itemName = item.getText();
            try {
                double price = inventoryPage.itemPrice(itemName);
                if (!Double.isNaN(price)) {
                    productsNameWithPrice.put(itemName, price);
                }
            } catch (Exception e) {
                LoggerUtil.error("Error retrieving price for item: " + itemName + " - " + e.getMessage());
            }
        }
        return productsNameWithPrice;
    }

    public List<String> productsNameAsList() {
        return new ArrayList<>(productsNameWithPrice().keySet());
    }

    private Map<String, Double> productsNameWithPriceSorting(Map<String, Double> map, boolean ascending) {
        Comparator<Map.Entry<String, Double>> comparator = Map.Entry.comparingByValue();
        if (!ascending) {
            comparator = comparator.reversed();
        }

        return map.entrySet().stream()
                .sorted(comparator)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    public Map<String, Double> productsNameWithPriceAscSorting() {
        Map<String, Double> unsortedMap = productsNameWithPrice();
        return productsNameWithPriceSorting(unsortedMap, true);
    }

    public Map<String, Double> productsNameWithPriceDescSorting() {
        Map<String, Double> unsortedMap = productsNameWithPrice();
        return productsNameWithPriceSorting(unsortedMap, false);
    }
}
