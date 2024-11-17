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

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public double getPrice() {
        double price = switch (size) {
            case 4 -> 5.50;
            case 8 -> 7.00;
            case 12 -> 8.50;
            default -> 0.0; // Default to $0 if size is invalid
        };

        for (Topping topping : toppings) {
            price += topping.getPrice();
        }
        return price;
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