package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@AllArgsConstructor
public class ReceiptModel {
    @NotNull
    private final ProductModel productModel;
    @NotNull
    private final BigDecimal salesTax;
    @NotNull
    private final BigDecimal totalPrice;

    @Override
    public @NotNull String toString() {
        return productModel.getName() + ": " + totalPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
