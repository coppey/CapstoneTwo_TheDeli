package com.ps.subwayz;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Product {
    private final String breadType;
    private final int size;
    private final boolean isToasted;
    private final List<Topping> toppings;

    public Sandwich(String breadType, int size, boolean isToasted) {
        this.breadType = breadType;
        this.size = size;
        this.isToasted = isToasted;
        this.toppings = new ArrayList<>();
    }

    public void addTopping(Topping topping, boolean isExtra) {
        // Adjust price based on sandwich size for meats and cheeses
        if (topping instanceof Meat) {
            double adjustedPrice = isExtra ? getExtraMeatPrice() : getRegularMeatPrice();
            toppings.add(new Meat(topping.getName(), adjustedPrice));
        } else if (topping instanceof Cheese) {
            double adjustedPrice = isExtra ? getExtraCheesePrice() : getRegularCheesePrice();
            toppings.add(new Cheese(topping.getName(), adjustedPrice));
        } else {
            toppings.add(topping); // Regular toppings are free
        }
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public double getPrice() {
        // Base price for sandwich size
        double price = switch (size) {
            case 4 -> 5.50;
            case 8 -> 7.00;
            case 12 -> 8.50;
            default -> 0.0; // Default to $0 if size is invalid
        };

        // Add topping prices
        for (Topping topping : toppings) {
            price += topping.getPrice();
        }
        return price;
    }

    private double getRegularMeatPrice() {
        // Regular meat prices based on sandwich size
        return switch (size) {
            case 4 -> 1.00; // $1 for 4"
            case 8 -> 2.00; // $2 for 8"
            case 12 -> 3.00; // $3 for 12"
            default -> 0.0; // Default to $0 if size is invalid
        };
    }

    private double getExtraMeatPrice() {
        // Extra meat prices based on sandwich size
        return switch (size) {
            case 4 -> 0.50; // $0.50 for 4"
            case 8 -> 1.00; // $1.00 for 8"
            case 12 -> 1.50; // $1.50 for 12"
            default -> 0.0; // Default to $0 if size is invalid
        };
    }

    private double getRegularCheesePrice() {
        // Regular cheese prices based on sandwich size
        return switch (size) {
            case 4 -> 0.75; // $0.75 for 4"
            case 8 -> 1.50; // $1.50 for 8"
            case 12 -> 2.25; // $2.25 for 12"
            default -> 0.0; // Default to $0 if size is invalid
        };
    }

    private double getExtraCheesePrice() {
        // Extra cheese prices based on sandwich size
        return switch (size) {
            case 4 -> 0.30; // $0.30 for 4"
            case 8 -> 0.60; // $0.60 for 8"
            case 12 -> 0.90; // $0.90 for 12"
            default -> 0.0; // Default to $0 if size is invalid
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(size).append("\" ").append(breadType).append(isToasted ? " (Toasted)" : "").append("\n");
        sb.append("Toppings:\n");
        for (Topping topping : toppings) {
            sb.append("- ").append(topping.getName()).append(" ($").append(String.format("%.2f", topping.getPrice())).append(")\n");
        }
        sb.append("Price: $").append(String.format("%.2f", getPrice()));
        return sb.toString();
    }
}