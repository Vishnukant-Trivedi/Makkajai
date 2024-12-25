package org.example.service;

import org.example.model.PurchaseSummary;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
public class ReceiptPrinter {
    private final @NotNull Invoice invoice;
    public ReceiptPrinter(Invoice invoice) {
        this.invoice = invoice;
    }

    public void printReceipt() {
        for (PurchaseSummary item : invoice.getItems()) {
            System.out.println(item);
        }
        BigDecimal totalSalesTax = invoice.getTotalSalesTax();
        BigDecimal totalPrice = invoice.getTotalPrice();

        System.out.println("Sales Taxes: " + totalSalesTax.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Total: " + totalPrice.setScale(2, RoundingMode.HALF_UP));
    }
}
