package com.ps.subwayz;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products; // A list to hold all items in the order (Sandwiches, Drinks, Chips)

    public Order() {
        this.products = new ArrayList<>();
    }

    // Add a product to the order
    public void addProduct(Product product) {
        products.add(product);
    }

    // Calculate the total cost of all items in the order
    public double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    // Display a detailed receipt of the order
    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Order Summary:\n");
        int sandwichCount = 0, drinkCount = 0, chipsCount = 0;

        for (Product product : products) {
            receipt.append("- ").append(product).append(" - $").append(product.getPrice()).append("\n");

            // Count product types for a cleaner summary
            if (product instanceof Sandwich) {
                sandwichCount++;
            } else if (product instanceof Drink) {
                drinkCount++;
            } else if (product instanceof BagOfChips) {
                chipsCount++;
            }
        }

        receipt.append("\nSummary:\n");
        receipt.append("Sandwiches: ").append(sandwichCount).append("\n");
        receipt.append("Drinks: ").append(drinkCount).append("\n");
        receipt.append("Chips: ").append(chipsCount).append("\n");
        receipt.append("Total: $").append(getTotal()).append("\n");

        return receipt.toString();
    }

    // Cancel the order by clearing all products
    public void cancelOrder() {
        products.clear();
        System.out.println("Order has been canceled.");
    }
}