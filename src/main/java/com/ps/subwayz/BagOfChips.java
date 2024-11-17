package com.ps.subwayz;

public class BagOfChips implements Product{
    private final String flavor;

    public BagOfChips(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return 1.50; // Fixed price for chips
    }

    @Override
    public String toString() {
        return flavor + " Chips ($" + String.format("%.2f", getPrice()) + ")";
    }
}
