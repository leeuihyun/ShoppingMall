package com.example.mall.domain.product.enums;

import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductCategory {
    MONITOR(0L, "MONITOR"),
    KEYBOARD(1L, "KEYBOARD"),
    MOUSE(2L, "MOUSE"),
    SPEAKER(3L, "SPEAKER"),
    CPU(4L, "CPU"),
    GPU(5L, "GPU"),
    RAM(6L, "RAM"),
    ROM(7L, "ROM");

    private final Long code;
    private final String name;

    public static ProductCategory valueOf(Long code) {
        for (ProductCategory category : ProductCategory.values()) {
            if (Objects.equals(category.code, code)) {
                return category;
            }
        }
        throw new IllegalArgumentException("product code not found");
    }
}
