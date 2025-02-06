package org.bnppf;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for BookStore.
 */

class BookStoreTest {
    private final BookStore bookStore = new BookStore();

    @Test
    void emptyBasketShouldCostZero() {
        assertEquals(0.0, bookStore.calculatePrice(new int[]{}));
    }

    @Test
    void singleBookShouldCostFifty() {
        assertEquals(50.0, bookStore.calculatePrice(new int[]{1}));
    }

    @Test
    void twoDifferentBooksShouldHaveFivePercentDiscount() {
        assertEquals(95.0, bookStore.calculatePrice(new int[]{1, 2}));
    }

    @Test
    void threeDifferentBooksShouldHaveTenPercentDiscount() {
        assertEquals(135.0, bookStore.calculatePrice(new int[]{1, 2, 3}));
    }

    @Test
    void duplicateBooksShouldNotGetDiscount() {
        assertEquals(100.0, bookStore.calculatePrice(new int[]{1, 1}));
    }
}