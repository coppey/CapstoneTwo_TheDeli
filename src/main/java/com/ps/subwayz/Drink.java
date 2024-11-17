package com.ps.subwayz;

public class Drink implements Product{
    private final String size;

    public Drink(String size) {
        this.size = size;
    }

    @Override
    public double getPrice() {
        return switch (size.toLowerCase()) {
            case "small" -> 2.00;
            case "medium" -> 2.50;
            case "large" -> 3.00;
            default -> 0.0; // Default to $0 if size is invalid
        };
    }

    @Override
    public String toString() {
        return size + " Drink ($" + String.format("%.2f", getPrice()) + ")";
    }
}
