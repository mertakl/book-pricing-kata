package org.bnppf;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookStore {
    private static final int BOOK_PRICE = 50;
    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            2, 0.05, 3, 0.10, 4, 0.20, 5, 0.25
    );

    public double calculatePrice(List<String> books) {
        // Count the frequency of each unique book
        Map<String, Integer> bookCounts = books.stream()
                .collect(Collectors.groupingBy(
                        book -> book,
                        Collectors.summingInt(book -> 1)
                ));

        // Convert the book frequencies into a list of counts
        List<Integer> bookFrequencies = new ArrayList<>(bookCounts.values());

        return findOptimalPrice(bookFrequencies);
    }

    private double findOptimalPrice(List<Integer> bookCounts) {
        // No books left
        if (bookCounts.stream().allMatch(count -> count == 0)) {
            return 0;
        }

        return calculateBestPrice(bookCounts);
    }

    private double calculateBestPrice(List<Integer> bookCounts) {
        double minPrice = calculateIndividualBookPrice(bookCounts);

        // Try different set sizes for discount
        for (int setSize = Math.min(5, getTotalUniqueBooks(bookCounts)); setSize >= 2; setSize--) {
            List<Integer> discountedBookCounts = applyDiscountSet(bookCounts, setSize);

            if (discountedBookCounts != null) {
                double discountedPrice = calculateDiscountedPrice(setSize)
                        + findOptimalPrice(discountedBookCounts);
                minPrice = Math.min(minPrice, discountedPrice);
            }
        }

        return minPrice;
    }

    private List<Integer> applyDiscountSet(List<Integer> bookCounts, int setSize) {
        List<Integer> newBookCounts = new ArrayList<>(bookCounts);
        int uniqueBooksInSet = 0;

        for (int i = 0; i < newBookCounts.size(); i++) {
            if (newBookCounts.get(i) > 0) {
                newBookCounts.set(i, newBookCounts.get(i) - 1);
                uniqueBooksInSet++;
            }

            if (uniqueBooksInSet == setSize) {
                return newBookCounts;
            }
        }

        return null;
    }

    private double calculateDiscountedPrice(int setSize) {
        double discount = DISCOUNTS.getOrDefault(setSize, 0.0);
        return (setSize * BOOK_PRICE) * (1 - discount);
    }

    private int calculateIndividualBookPrice(List<Integer> bookCounts) {
        return bookCounts.stream()
                .mapToInt(Integer::intValue)
                .sum() * BOOK_PRICE;
    }

    private int getTotalUniqueBooks(List<Integer> bookCounts) {
        return (int) bookCounts.stream()
                .filter(count -> count > 0)
                .count();
    }
}


