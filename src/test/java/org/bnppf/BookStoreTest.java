package org.bnppf;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for BookStore.
 */

class BookStoreTest {
    private BookStore bookStore;

    @BeforeEach
    void setUp() {
        bookStore = new BookStore();
    }


    @Test
    void duplicateBooksShouldNotGetDiscount() {
        assertEquals(100.0, bookStore.calculatePrice(List.of("Clean Code", "Clean Code")));
    }

    @Test
    void emptyBasketShouldCostZero() {
        assertEquals(0.0, bookStore.calculatePrice(List.of()));
    }

    @Test
    void singleBookShouldCostFifty() {
        assertEquals(50.0, bookStore.calculatePrice(List.of("Clean Code")));
    }

    @Test
    void twoDifferentBooksShouldHaveFivePercentDiscount() {
        assertEquals(95.0, bookStore.calculatePrice(List.of("Clean Code", "Clean Coder")));
    }

    @Test
    void threeDifferentBooksShouldHaveTenPercentDiscount() {
        assertEquals(135.0, bookStore.calculatePrice(List.of("Clean Code", "Clean Coder", "Clean Architecture")));
    }

    @Test
    void fourDifferentBooksShouldHaveTwentyPercentDiscount() {
        assertEquals(160.0, bookStore.calculatePrice(List.of("Clean Code", "Clean Coder", "Clean Architecture", "TDD")));
    }

    @Test
    void fiveDifferentBooksShouldHaveTwentyFivePercentDiscount() {
        assertEquals(187.5, bookStore.calculatePrice(List.of("Clean Code", "Clean Coder", "Clean Architecture",
                "TDD", "Legacy Code")));
    }

    @Test
    void complexBasketShouldCalculatePriceCorrectly() {
        List<String> books = Arrays.asList(
                "Clean Code", "Clean Code",
                "Clean Coder", "Clean Coder",
                "Clean Architecture", "Clean Architecture",
                "TDD",
                "Legacy Code"
        );
        assertEquals(320.0, bookStore.calculatePrice(books));
    }
}