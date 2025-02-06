package org.bnppf;

import java.util.Arrays;

public class BookStore {
    private static final double BOOK_PRICE = 50.0;
    private static final double TWO_BOOKS_DISCOUNT = 0.05;
    private static final double THREE_BOOKS_DISCOUNT = 0.10;

    public double calculatePrice(int[] books) {
        if (books == null || books.length == 0) {
            return 0.0;
        }

        // Count unique books
        long uniqueBooks = Arrays.stream(books).distinct().count();

        if (uniqueBooks == 3) {
            return 3 * BOOK_PRICE * (1 - THREE_BOOKS_DISCOUNT);
        } else if (uniqueBooks == 2) {
            return 2 * BOOK_PRICE * (1 - TWO_BOOKS_DISCOUNT);
        }

        return books.length * BOOK_PRICE;
    }

}

