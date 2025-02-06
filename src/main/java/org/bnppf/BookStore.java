package org.bnppf;

import java.util.*;

public class BookStore {
    Map<Integer, Double> discounts = Map.of(
            2, 0.05, 3, 0.10, 4, 0.20, 5, 0.25
    );

    public double calculatePrice(List<String> books) {
        double totalPrice = 0;
        List<String> remainingBooks = new ArrayList<>(books);

        while (!remainingBooks.isEmpty()) {
            Set<String> uniqueBooks = new HashSet<>(remainingBooks);
            int differentBooks = uniqueBooks.size();

            double discount = discounts.getOrDefault(differentBooks, 0.0);
            totalPrice += (differentBooks * 50) * (1 - discount);

            uniqueBooks.forEach(remainingBooks::remove);
        }

        return totalPrice;
    }

}

