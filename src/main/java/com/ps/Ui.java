package com.ps;

import com.ps.subwayz.*;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static Order order = new Order();

    private static Scanner commandScanner = new Scanner(System.in);
    private static Scanner inputScanner = new Scanner(System.in);

    public static void diplay() {
        int menuOp;

        do {
            System.out.println("----Welcome to Subwayzz----" +
                    "What would you like to do?\n" +
                    "1) Place an Order\n" +
                    "2) Leave\n" +
                    "Choice: ");
            menuOp = commandScanner.nextInt();

            try {
                switch (menuOp) {
                    case 1 -> storeMenu();
                    case 2 -> System.out.println("Thank you, Come Again!!!");
                    default -> System.out.println("Please Enter Valid Option");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (menuOp != 0);
    }

    private static void storeMenu() {
        int choice;

        do {
            System.out.println("1. Add Sandwich\n" +
                    "2) Add Drink\n" +
                    "3) Add Chips\n" +
                    "4) Checkout\n" +
                    "0) Exit\n" +
                    "Select an option: "
            );
            choice = inputScanner.nextInt();
            inputScanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1 -> addSandwich();
                    case 2 -> addDrink();
                    case 3 -> addChips();
                    case 4 -> handleCheckout();
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        } while (choice != 0);
    }

    private static void handleCheckout() {
    }

    private static void addChips() {
    }

    private static void addDrink() {
    }

    private static void addSandwich() {
        System.out.print("Enter bread type (Wheat/White/Rye/Wrap): ");
        String breadType = inputScanner.nextLine();

        System.out.print("Enter size (4, 8, 12): ");
        int size = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume newline

        System.out.println("What type of Meat would you like? Options:\n" +
                "-> 1) Steak\n" +
                "-> 2) Ham\n" +
                "-> 3) Salami\n" +
                "-> 4) Roast Beef\n" +
                "-> 5) Chicken\n" +
                "-> 6 Bacon\n" +
                "choice:");
        int selectedMeat = inputScanner.nextInt();
        inputScanner.nextLine(); // New Line consumed
        String meatName = getMeatChoice(selectedMeat);
        Meat defaultMeat = new Meat(meatName, 0);

        System.out.println("What type of Cheese would you like? Options:\n" +
                "-> 1) American\n" +
                "-> 2) Provolone\n" +
                "-> 3) Cheddar\n" +
                "-> 4) Swiss\n" +
                "choice:");
        int selectedCheese = inputScanner.nextInt();
        inputScanner.nextLine(); // New Line consumed
        String cheeseName = getCheeseChoice(selectedCheese);
        Cheese defaultCheese = new Cheese(cheeseName, 0);

        System.out.print("Would you like the sandwich toasted? (y/n): ");
        String toastChoice = inputScanner.nextLine().toLowerCase().trim();
        boolean isToasted = toastChoice.equalsIgnoreCase("y");

        // Create the sandwich
        Sandwich sandwich = new Sandwich(breadType, size, isToasted);

        // Add default toppings
        sandwich.addTopping(defaultMeat);
        sandwich.addTopping(defaultCheese);

        boolean done = false;
        while (!done) {
            System.out.println("What would you like to do?\n" +
                    "1) Add extra premium topping (Cheese or Meat)\n" +
                    "2) Remove a topping\n" +
                    "2) Remove a topping\n" +
                    "3) View all toppings\n" +
                    "4) Finish sandwich\n" +
                    "Choice: ");
            int choice = inputScanner.nextInt();
            inputScanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addExtraPremiumTopping(sandwich);
                case 2 -> removeTopping(sandwich);
                case 3 -> viewToppings(sandwich);
                case 4 -> {
                    done = true;
                    order.addProduct(sandwich);
                    System.out.println("Sandwich added to order.");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static String getMeatChoice(int selectedMeat) {
        return switch (selectedMeat) {
            case 1 -> "Steak";
            case 2 -> "Ham";
            case 3 -> "Salami";
            case 4 -> "Roast Beef";
            case 5 -> "Chicken";
            case 6 -> "Bacon";
            default -> {
                System.out.println("Invalid choice, defaulting to Chicken.");
                yield "Chicken";
            }
        };
    }

    private static String getCheeseChoice(int selectedCheese) {
        return switch (selectedCheese) {
            case 1 -> "American";
            case 2 -> "Provolone";
            case 3 -> "Cheddar";
            case 4 -> "Swiss";
            default -> {
                System.out.println("Invalid choice, defaulting to American.");
                yield "American";
            }
        };
    }
    private static void addExtraPremiumTopping(Sandwich sandwich) {
        System.out.print("Enter topping type (Cheese/Meat): ");
        String type = inputScanner.nextLine();

        System.out.print("Enter topping name: ");
        String name = inputScanner.nextLine();

        double price = 0;
        if (type.equalsIgnoreCase("Cheese")) {
            price = 0.30; // Extra cheese price
            sandwich.addTopping(new Cheese(name, price));
            System.out.println(name + " added as extra cheese.");
        } else if (type.equalsIgnoreCase("Meat")) {
            price = 0.50; // Extra meat price
            sandwich.addTopping(new Meat(name, price));
            System.out.println(name + " added as extra meat.");
        } else {
            System.out.println("not a valid topping");
        }
    }

    private static void removeTopping(Sandwich sandwich) {
        viewToppings(sandwich);
        System.out.print("Enter the name of the topping to remove: ");
        String toppingName = inputScanner.nextLine();

        boolean removed = sandwich.removeTopping(toppingName);
        if (removed) {
            System.out.println(toppingName + " removed from the sandwich.");
        } else {
            System.out.println("Topping not found.");
        }
    }

    private static void viewToppings(Sandwich sandwich) {
        System.out.println("\nCurrent toppings:");
        for (Topping topping : sandwich.getToppings()) {
            System.out.println("- " + topping.getName());
        }
    }
}