package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class Item {
    @NotNull
    private final String name;
    @NotNull
    private final BigDecimal price;
    private final boolean isImported;
    private final boolean isExempt;
}
