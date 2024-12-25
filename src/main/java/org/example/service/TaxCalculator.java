package org.example.service;

import org.example.model.Item;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.config.TaxRates.BASIC_SALES_TAX_RATE;
import static org.example.config.TaxRates.IMPORT_DUTY_RATE;

public class TaxCalculator {
    public BigDecimal calculateTax(@NotNull Item item) {
        BigDecimal tax = BigDecimal.ZERO;

        if (!item.isExempt()) {
            tax = tax.add(item.getPrice().multiply(BASIC_SALES_TAX_RATE));
        }

        if (item.isImported()) {
            tax = tax.add(item.getPrice().multiply(IMPORT_DUTY_RATE));
        }

        return roundUpToNearestFiveCents(tax);
    }

    private BigDecimal roundUpToNearestFiveCents(BigDecimal amount) {
        return amount.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP).multiply(BigDecimal.valueOf(0.05));
    }
}
