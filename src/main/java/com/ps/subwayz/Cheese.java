package com.ps.subwayz;

public class Cheese extends Topping{

    public Cheese(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "cheese";
    }

}
