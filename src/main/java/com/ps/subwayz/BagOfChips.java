package com.ps.subwayz;

public class BagOfChips implements Product{
    private String flavor;

    public BagOfChips(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public double getPrice() {
        return 1.50; // Flat price for chips
    }

    @Override
    public String toString() {
        return "Bag of Chips (" + flavor + ")";
    }
}
