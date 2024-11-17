package com.ps.subwayz;

public class Drink implements Product{

    private String size;

    public Drink(String size) {
        this.size = size;
    }

    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "small": return 2.00;
            case "medium": return 2.50;
            case "large": return 3.00;
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return size + " Drink";
    }
}
