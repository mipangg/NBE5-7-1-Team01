package io.binary.coffeenotfound_404.domain;

import java.util.NoSuchElementException;

public enum ItemsCategory {
    COFFEE_BEAN,
    COFFEE,
    FOOD;

    public static ItemsCategory from(String value) {
        try {
            return ItemsCategory.valueOf(value.trim().toUpperCase());
        } catch (Exception e) {
            throw new NoSuchElementException("유효하지 않은 카테고리 " + value);
        }
    }

}
