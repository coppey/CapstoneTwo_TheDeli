package com.ps.subwayz;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements Product {
    private String breadType; // e.g., "Wheat", "White"
    private int size; // e.g., 4, 8, or 12 inches
    private boolean isToasted;
    private List<Topping> toppings;

    public Sandwich(String breadType, int size, boolean isToasted) {
        this.breadType = breadType;
        this.size = size;
        this.isToasted = isToasted;
        this.toppings = new ArrayList<>();
        addDefaultToppings();
    }

    private void addDefaultToppings() {
        // Regular Toppings
        toppings.add(new DefaultTopping("Lettuce"));
        toppings.add(new DefaultTopping("Peppers"));
        toppings.add(new DefaultTopping("Onions"));
        toppings.add(new DefaultTopping("Tomatoes"));
        toppings.add(new DefaultTopping("Jalapenos"));
        toppings.add(new DefaultTopping("Cucumbers"));
        toppings.add(new DefaultTopping("Pickles"));
        toppings.add(new DefaultTopping("Guacamole"));
        toppings.add(new DefaultTopping("Mushrooms"));

        // Sauces
        toppings.add(new DefaultTopping("Mayo"));
        toppings.add(new DefaultTopping("Mustard"));
        toppings.add(new DefaultTopping("Ketchup"));
        toppings.add(new DefaultTopping("Ranch"));
        toppings.add(new DefaultTopping("Thousand Islands"));

        toppings.add(new Cheese("Default Cheese", 0));
        toppings.add(new Meat("Default Meat", 0));
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    @Override
    public double getPrice() {
        double basePrice = 0;
        switch (size) {
            case 4: basePrice = 5.50; break;
            case 8: basePrice = 7.00; break;
            case 12: basePrice = 8.50; break;
        }
        for (Topping topping : toppings) {
            basePrice += topping.getPrice();
        }
        return basePrice;
    }

    public boolean removeTopping(String name) {
        for (Topping topping : toppings) {
            if (topping.getName().equalsIgnoreCase(name)) {
                toppings.remove(topping);
                return true;
            }
        }
        return false; // Topping not found
    }

    public String getBreadType() {
        return breadType;
    }

    public int getSize() {
        return size;
    }

    public boolean isToasted() {
        return isToasted;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "breadType='" + breadType + '\'' +
                ", size=" + size +
                ", isToasted=" + isToasted +
                ", toppings=" + toppings +
                '}';
    }
}
