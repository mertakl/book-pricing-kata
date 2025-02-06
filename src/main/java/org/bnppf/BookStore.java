package org.bnppf;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStore {
    private static final int BOOK_PRICE = 50;
    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            2, 0.05, 3, 0.10, 4, 0.20, 5, 0.25
    );

    public double calculatePrice(List<String> books) {
        Map<String, Integer> bookCounts = new HashMap<>();
        for (String book : books) {
            bookCounts.put(book, bookCounts.getOrDefault(book, 0) + 1);
        }

        // Convert the book frequencies into a list of counts
        List<Integer> bookFrequencies = new ArrayList<>(bookCounts.values());

        return findOptimalPrice(bookFrequencies);
    }

    private double findOptimalPrice(List<Integer> bookCounts) {
        // No books left
        if (bookCounts.stream().allMatch(count -> count == 0)) {
            return 0;
        }

        double minPrice = Double.MAX_VALUE;

        // Group books from size 5 down to 2
        for (int setSize = 5; setSize >= 2; setSize--) {
            List<Integer> newBookCounts = new ArrayList<>(bookCounts);
            int actualSize = 0;

            // Reduce the count for up to 'setSize' unique books
            for (int i = 0; i < newBookCounts.size(); i++) {
                if (newBookCounts.get(i) > 0) {
                    newBookCounts.set(i, newBookCounts.get(i) - 1);
                    actualSize++;
                }
                if (actualSize == setSize) break;
            }

            if (actualSize == setSize) {
                double discount = DISCOUNTS.getOrDefault(setSize, 0.0);
                double price = (setSize * BOOK_PRICE) * (1 - discount);
                minPrice = Math.min(minPrice, price + findOptimalPrice(newBookCounts));
            }
        }

        // If no grouping applies, just sum the individual book prices
        int totalBooks = bookCounts.stream().mapToInt(Integer::intValue).sum();
        return Math.min(minPrice, totalBooks * BOOK_PRICE);
    }
}


