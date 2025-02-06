package org.bnppf;

public class BookStore {
    private static final double BOOK_PRICE = 50.0;

    public double calculatePrice(int[] books) {
        if (books == null || books.length == 0) {
            return 0.0;
        }
        return books.length * BOOK_PRICE;
    }
}

