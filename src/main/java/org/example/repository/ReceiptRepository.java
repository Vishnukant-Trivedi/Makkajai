package org.example.repository;

import org.example.model.ReceiptModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ReceiptRepository {
    private final List<ReceiptModel> items = new ArrayList<>();
    private BigDecimal totalSalesTax = BigDecimal.ZERO;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public void addItem(ReceiptModel item) {
        items.add(item);
        totalSalesTax = totalSalesTax.add(item.getSalesTax());
        totalPrice = totalPrice.add(item.getTotalPrice());
    }

    public void printReceipt() {
        for (ReceiptModel item : items) {
            System.out.println(item);
        }
        System.out.println("Sales Taxes: " + totalSalesTax.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Total: " + totalPrice.setScale(2, RoundingMode.HALF_UP));
    }
}
