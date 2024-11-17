package com.ps.subwayz;

public class Meat extends Topping{

    public Meat(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "Meat";
    }
}
