package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.model.PurchaseSummary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Invoice {
    private final List<PurchaseSummary> items = new ArrayList<>();
    private BigDecimal totalSalesTax = BigDecimal.ZERO;
    private BigDecimal totalPrice = BigDecimal.ZERO;

    public void addItem(PurchaseSummary item) {
        items.add(item);
        totalSalesTax = totalSalesTax.add(item.salesTax());
        totalPrice = totalPrice.add(item.totalPrice());
    }
}
