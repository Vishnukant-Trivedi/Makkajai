package org.example.service;


import org.example.model.ProductModel;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class TaxCalculatorServiceTest {

    @Test
    public void testTaxCalculationForExemptItems() {
        TaxCalculatorService TaxCalculatorService = new TaxCalculatorService();
        ProductModel ProductModel = new ProductModel("book", BigDecimal.valueOf(12.49), false, true);
        assertEquals(BigDecimal.ZERO.setScale(2), TaxCalculatorService.calculateTax(ProductModel));
    }

    @Test
    public void testTaxCalculationForNonExemptItems() {
        TaxCalculatorService TaxCalculatorService = new TaxCalculatorService();
        ProductModel ProductModel = new ProductModel("music CD", BigDecimal.valueOf(14.99), false, false);
        assertEquals(BigDecimal.valueOf(1.50).setScale(2), TaxCalculatorService.calculateTax(ProductModel));
    }

    @Test
    public void testTaxCalculationForImportedItems() {
        TaxCalculatorService TaxCalculatorService = new TaxCalculatorService();
        ProductModel ProductModel = new ProductModel("imported bottle of perfume", BigDecimal.valueOf(27.99), true, false);
        assertEquals(BigDecimal.valueOf(4.20).setScale(2), TaxCalculatorService.calculateTax(ProductModel));
    }

    @Test
    public void testTotalPriceCalculation() {
        TaxCalculatorService TaxCalculatorService = new TaxCalculatorService();
        ProductModel ProductModel = new ProductModel("music CD", BigDecimal.valueOf(14.99), false, false);
        BigDecimal tax = TaxCalculatorService.calculateTax(ProductModel);
        assertEquals(BigDecimal.valueOf(16.49).setScale(2), ProductModel.getPrice().add(tax));
    }

}