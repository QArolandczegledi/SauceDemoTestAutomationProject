package com.demoautomationprojecttests.tests;

import com.demoautomationproject.pages.InventoryPage;
import com.demoautomationproject.itemnames.ProductNameCollector;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.demoautomationproject.util.LoggerUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class InventoryPageTests extends BaseTest {

    private InventoryPage inventoryPage;
    private ProductNameCollector productNameCollector;

    @BeforeMethod
    public void beforeTestSetUp() {
        productNameCollector = new ProductNameCollector(driver);
        inventoryPage = new InventoryPage(driver);
        performLogin();
        LoggerUtil.info("InventoryPageTests setup complete.");
    }

    @Test(description = "Testing the sorting by name descending")
    public void testSortByNameDescending() {
        LoggerUtil.info("Starting test: Sorting by name descending.");
        List<String> expectedItemOrder = productNameCollector.productsNameAsList();
        Collections.reverse(expectedItemOrder);
        proceedToSorting(InventoryPage.SORT_Z_TO_A);
        List<String> actualItemOrder = productNameCollector.productsNameAsList();
        Assert.assertEquals(actualItemOrder, expectedItemOrder, "The actual item order does not match the expected order.");
        LoggerUtil.info("Sorting by name descending test passed.");
    }

    @Test(description = "Testing the sorting by price ascending")
    void testSortByPriceAscending() {
        LoggerUtil.info("Starting test: Sorting by price ascending.");
        Map<String, Double> expectedOrderOfTheProducts = productNameCollector.productsNameWithPriceAscSorting();
        proceedToSorting(InventoryPage.SORT_LOW_TO_HIGH);
        Map<String, Double> actualOrderOfTheProducts = productNameCollector.productsNameWithPrice();
        boolean isEqual = areMapsEqual(expectedOrderOfTheProducts, actualOrderOfTheProducts);
        Assert.assertTrue(isEqual, "The actual order of products does not match the expected order.");
        LoggerUtil.info("Sorting by price ascending test passed.");
    }

    @Test(description = "Testing the sorting by price descending")
    void testSortByPriceDescending() {
        LoggerUtil.info("Starting test: Sorting by price descending.");
        Map<String, Double> expectedOrderOfTheProducts = productNameCollector.productsNameWithPriceDescSorting();
        proceedToSorting(InventoryPage.SORT_HIGH_TO_LOW);
        Map<String, Double> actualOrderOfTheProducts = productNameCollector.productsNameWithPrice();
        boolean isEqual = areMapsEqual(expectedOrderOfTheProducts, actualOrderOfTheProducts);
        Assert.assertTrue(isEqual, "The actual order of products does not match the expected order.");
        LoggerUtil.info("Sorting by price descending test passed.");
    }

    private boolean areMapsEqual(Map<String, Double> map1, Map<String, Double> map2) {
        if (map1.size() != map2.size()) {
            return false;
        }
        Iterator<Map.Entry<String, Double>> iterator1 = map1.entrySet().iterator();
        Iterator<Map.Entry<String, Double>> iterator2 = map2.entrySet().iterator();

        while (iterator1.hasNext() && iterator2.hasNext()) {
            Map.Entry<String, Double> entry1 = iterator1.next();
            Map.Entry<String, Double> entry2 = iterator2.next();
            if (!entry1.getKey().equals(entry2.getKey()) || !entry1.getValue().equals(entry2.getValue())) {
                return false;
            }
        }
        return true;
    }

    private void proceedToSorting(String sortingType) {
        inventoryPage.sortItems(sortingType);
    }
}
