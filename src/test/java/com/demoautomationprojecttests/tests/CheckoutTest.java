package com.demoautomationprojecttests.tests;

import com.demoautomationproject.itemnames.ProductNameCollector;
import com.demoautomationproject.pages.*;
import com.demoautomationproject.testdataprovider.Customer;
import com.demoautomationproject.util.CustomerDataUtil;
import com.demoautomationproject.util.LoggerUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CheckoutTest extends BaseTest {

    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutYourInfoPage checkoutYourInfoPage;
    private CheckoutOverviewPage checkoutOverviewPage;
    private CheckoutCompletePage checkoutCompletePage;
    private ProductNameCollector productNameCollector;

    @BeforeMethod
    public void beforeTestSetUp() {
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutYourInfoPage = new CheckoutYourInfoPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        productNameCollector = new ProductNameCollector(driver);
        performLogin();
        LoggerUtil.info("Setup for CheckoutTest completed.");
    }

    @Test(description = "Checking the checkout happy path")
    public void checkOutFlowTest() {
        LoggerUtil.info("Starting checkOutFlowTest.");
        Customer randomCustomer = loadTestData();
        addProductsToCart();
        double productsPriceAmount = productsPriceAmount();
        proceedToCheckout();
        fillCheckoutInformation(randomCustomer.firstName(), randomCustomer.lastName(), randomCustomer.zipCode());
        checkoutYourInfoPage.continueButton().click();
        verifyTotalsWithSoftAssert(productsPriceAmount, checkoutOverviewPage.itemTotal());
        checkoutOverviewPage.finishButton().click();
        boolean isCompleted = checkoutCompletePage.completedCheckoutMessage().isDisplayed();
        LoggerUtil.info("Checkout process completed. Success: " + isCompleted);
        Assert.assertTrue(isCompleted, "Checkout process did not complete successfully.");
    }

    private Customer loadTestData() {
        try {
            return CustomerDataUtil.getRandomCustomerData();
        } catch (Exception e) {
            LoggerUtil.error("Error loading test data: " + e.getMessage());
            throw new RuntimeException("Failed to load test data.", e);
        }
    }

    private void verifyTotalsWithSoftAssert(double expectedTotal, double actualTotal) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTotal, expectedTotal, "The item totals do not match!");
        if (actualTotal != expectedTotal) {
            LoggerUtil.error("Mismatch in expected and actual totals: Expected - " + expectedTotal + ", Actual - " + actualTotal);
        }
        softAssert.assertAll();
        LoggerUtil.info("Verified totals with soft assert. Expected: " + expectedTotal + ", Actual: " + actualTotal);
    }

    private void proceedToCheckout() {
        inventoryPage.cartIcon().click();
        cartPage.checkoutButton().click();
        LoggerUtil.info("Proceeded to checkout.");
    }

    private void fillCheckoutInformation(String firstName, String lastName, String zipCode) {
        checkoutYourInfoPage.fillCheckoutYourInfoPage(firstName, lastName, zipCode);
        LoggerUtil.info("Filled checkout information for customer: " + firstName + " " + lastName);
    }

    private void addProductsToCart() {
        inventoryPage.firstItemAddToCartButton().click();
        inventoryPage.secondItemAddToCartButton().click();
        inventoryPage.thirdItemAddToCartButton().click();
        inventoryPage.forthItemAddToCartButton().click();
        inventoryPage.fifthItemAddToCartButton().click();
        inventoryPage.sixthItemAddToCartButton().click();
        LoggerUtil.info("Added products to the cart.");
    }

    public double productsPriceAmount() {
        List<String> listOfProduct = productNameCollector.productsNameAsList();
        double productsPriceAmount = 0;
        for (String product : listOfProduct) {
            double itemPrice = inventoryPage.itemPrice(product);
            productsPriceAmount += itemPrice;
            LoggerUtil.info("Price for " + product + ": " + itemPrice);
        }
        LoggerUtil.info("Total price of products: " + productsPriceAmount);
        return productsPriceAmount;
    }
}
