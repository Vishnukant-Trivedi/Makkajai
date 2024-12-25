package org.example.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record PurchaseSummary(@NotNull Item item, @NotNull BigDecimal salesTax, @NotNull BigDecimal totalPrice) {
    @Override
    public @NotNull String toString() {
        return item.getName() + ": " + totalPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
