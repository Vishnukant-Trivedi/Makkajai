package org.example.service;

import org.example.model.ProductModel;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculatorService {
    private static final BigDecimal BASIC_SALES_TAX_RATE = BigDecimal.valueOf(0.10);
    private static final BigDecimal IMPORT_DUTY_RATE = BigDecimal.valueOf(0.05);

    public BigDecimal calculateTax(@NotNull ProductModel productModel) {
        BigDecimal tax = BigDecimal.ZERO;

        if (!productModel.isExempt()) {
            tax = tax.add(productModel.getPrice().multiply(BASIC_SALES_TAX_RATE));
        }

        if (productModel.isImported()) {
            tax = tax.add(productModel.getPrice().multiply(IMPORT_DUTY_RATE));
        }

        return roundUpToNearestFiveCents(tax);
    }

    private BigDecimal roundUpToNearestFiveCents(BigDecimal amount) {
        return amount.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP).multiply(BigDecimal.valueOf(0.05));
    }
}
