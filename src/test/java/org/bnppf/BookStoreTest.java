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
}