package org.example.service;
import org.example.model.Item;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TaxCalculatorTest {

    private TaxCalculator taxCalculator;

    @Before
    public void setUp() {
        this.taxCalculator = new TaxCalculator();
    }

    @Test
    public void testTaxCalculationForExemptAndNonImportedItem() {
        Item item = new Item("book", new BigDecimal("12.99"), false, true); // Exempt and Non-imported
        BigDecimal tax = taxCalculator.calculateTax(item);

        assertEquals("Tax should be 0 for exempt and non-imported items.", BigDecimal.ZERO.setScale(2), tax);
    }

    @Test
    public void testTaxCalculationForNonExemptAndNonImportedItem() {
        Item item = new Item("shoes", new BigDecimal("45.99"), false, false); // Non-exempt and Non-imported
        BigDecimal tax = taxCalculator.calculateTax(item);

        BigDecimal expectedTax = new BigDecimal("4.60"); // 45.99 * 0.10 = 4.599, rounded up to 4.60
        assertEquals("Tax should be calculated with basic sales tax rate.", expectedTax, tax);
    }

    @Test
    public void testTaxCalculationForExemptAndImportedItem() {
        Item item = new Item("imported chocolate", new BigDecimal("5.99"), true, true); // Exempt but Imported
        BigDecimal tax = taxCalculator.calculateTax(item);

        BigDecimal expectedTax = new BigDecimal("0.30"); // 5.99 * 0.05 (import duty) = 0.2995, rounded up to 0.30
        assertEquals("Tax should only be the import duty for exempt and imported items.",expectedTax, tax);
    }

    @Test
    public void testZeroTaxCalculationForNonExemptItemAndImported() {
        Item item = new Item("imported book", new BigDecimal("10.00"), true, false); // Exempt and Imported
        BigDecimal tax = taxCalculator.calculateTax(item);

        assertEquals("Tax should include only the import duty for exempt items.", new BigDecimal("1.50"), tax);
    }

}