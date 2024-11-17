package com.ps;

import com.ps.subwayz.*;

import java.util.List;
import java.util.Scanner;

public class Ui {
    private static Order order = new Order(); // Initialize the order
    private static final Scanner commandScanner = new Scanner(System.in); // For menu commands
    private static final Scanner inputScanner = new Scanner(System.in); // For detailed user inputs

    public static void display() {
        boolean exit = false;
        while (!exit) {
            System.out.println(
                    "1. Start a New Order\n" +
                            "0. Exit\n" +
                            "Select an option: "
            );
            try {
                int menuOp = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline
                switch (menuOp) {
                    case 1 -> startNewOrder();
                    case 0 -> {
                        exit = true;
                        System.out.println("Thank you for visiting Subwayzz. Goodbye!");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter 0 or 1.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void startNewOrder() {
        order = new Order(); // Reset the order
        boolean ordering = true;
        while (ordering) {
            System.out.println(
                    "1. Add Sandwich\n" +
                            "2. Add Drink\n" +
                            "3. Add Chips\n" +
                            "4. Checkout\n" +
                            "0. Cancel Order\n" +
                            "Select an option: "
            );
            try {
                int menuOp = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline
                switch (menuOp) {
                    case 1 -> addSandwich();
                    case 2 -> addDrink();
                    case 3 -> addChips();
                    case 4 -> {
                        checkout();
                        ordering = false;
                    }
                    case 0 -> {
                        System.out.println("Order canceled. Returning to main menu.");
                        ordering = false;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void addSandwich() {
        System.out.print("Enter bread type (Wheat/White/Rye/Wrap): ");
        String breadType = inputScanner.nextLine();

        // Validate size
        int size = 0;
        boolean validSize = false;
        while (!validSize) {
            try {
                System.out.print("Enter size (4, 8, 12): ");
                size = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline
                if (size == 4 || size == 8 || size == 12) {
                    validSize = true;
                } else {
                    System.out.println("Invalid size. Please enter 4, 8, or 12.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number (4, 8, or 12).");
                commandScanner.nextLine(); // Clear invalid input
            }
        }

        // Sandwich initialization
        Sandwich sandwich = new Sandwich(breadType, size, false);

        // Choose Meat
        boolean validMeat = false;
        while (!validMeat) {
            try {
                System.out.println("Select Meat:");
                System.out.println("1. Steak\n" +
                        "2. Ham\n" +
                        "3. Salami\n" +
                        "4. Roast Beef\n" +
                        "5. Chicken\n" +
                        "6. Bacon\n" +
                        "Choice:"
                );
                int meatChoice = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline

                String meatName = switch (meatChoice) {
                    case 1 -> "Steak";
                    case 2 -> "Ham";
                    case 3 -> "Salami";
                    case 4 -> "Roast Beef";
                    case 5 -> "Chicken";
                    case 6 -> "Bacon";
                    default -> {
                        System.out.println("Invalid choice. Defaulting to Chicken.");
                        yield "Chicken";
                    }
                };

                sandwich.addTopping(new Meat(meatName, sandwich.getRegularMeatPrice()), false);
                validMeat = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }

        // Choose Cheese
        boolean validCheese = false;
        while (!validCheese) {
            try {
                System.out.println("Select Cheese:");
                System.out.println("1. American\n2. Provolone\n3. Cheddar\n4. Swiss\nChoice:");
                int cheeseChoice = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline

                String cheeseName = switch (cheeseChoice) {
                    case 1 -> "American";
                    case 2 -> "Provolone";
                    case 3 -> "Cheddar";
                    case 4 -> "Swiss";
                    default -> {
                        System.out.println("Invalid choice. Defaulting to American.");
                        yield "American";
                    }
                };

                sandwich.addTopping(new Cheese(cheeseName, sandwich.getRegularCheesePrice()), false);
                validCheese = true;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }

        System.out.print("Would you like the sandwich toasted? (y/n): ");
        String toastChoice = inputScanner.nextLine().toLowerCase().trim();
        boolean isToasted = toastChoice.equalsIgnoreCase("y");

        // Create sandwich and add default toppings
        Sandwich sandwich = new Sandwich(breadType, size, isToasted);
        sandwich.addTopping(defaultMeat);
        sandwich.addTopping(defaultCheese);

        // Allow user to modify toppings
        boolean done = false;
        while (!done) {
            System.out.println(
                    "1. Add extra premium topping (Cheese or Meat)\n" +
                            "2. Remove a topping\n" +
                            "3. Add Regular topping\n" +
                            "4. View all toppings\n" +
                            "5. Finish sandwich\n" +
                            "Select an option: "
            );
            try {
                int menuOp = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline
                switch (menuOp) {
                    case 1 -> addExtraPremiumTopping(sandwich);
                    case 2 -> removeTopping(sandwich);
                    case 3 -> addRegularToppings(sandwich);
                    case 4 -> viewToppings(sandwich);
                    case 5 -> {
                        order.addProduct(sandwich);
                        System.out.println("Sandwich added to order.");
                        done = true;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void addRegularToppings(Sandwich sandwich) {
        boolean adding = true;

        while (adding) {
            System.out.println(
                    "What Toppings would you like to add to your Sandwich?\n" +
                            "---Regular Toppings---\n" +
                            "1. Lettuce\n" +
                            "2. Peppers\n" +
                            "3. Onions\n" +
                            "4. Tomatoes\n" +
                            "5. Jalapenos\n" +
                            "6. Cucumbers\n" +
                            "7. Pickles\n" +
                            "8. Guacamole\n" +
                            "9. Mushrooms\n" +
                            "---Sauces---\n" +
                            "10. Mayo\n" +
                            "11. Mustard\n" +
                            "12. Ketchup\n" +
                            "13. Ranch\n" +
                            "14. Thousand Islands\n" +
                            "15. Vinaigrette\n" +
                            "0. Done Adding Toppings\n" +
                            "Selection: "
            );

            try {
                int choice = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> sandwich.addTopping(new DefaultTopping("Lettuce"));
                    case 2 -> sandwich.addTopping(new DefaultTopping("Peppers"));
                    case 3 -> sandwich.addTopping(new DefaultTopping("Onions"));
                    case 4 -> sandwich.addTopping(new DefaultTopping("Tomatoes"));
                    case 5 -> sandwich.addTopping(new DefaultTopping("Jalapenos"));
                    case 6 -> sandwich.addTopping(new DefaultTopping("Cucumbers"));
                    case 7 -> sandwich.addTopping(new DefaultTopping("Pickles"));
                    case 8 -> sandwich.addTopping(new DefaultTopping("Guacamole"));
                    case 9 -> sandwich.addTopping(new DefaultTopping("Mushrooms"));
                    case 10 -> sandwich.addTopping(new DefaultTopping("Mayo"));
                    case 11 -> sandwich.addTopping(new DefaultTopping("Mustard"));
                    case 12 -> sandwich.addTopping(new DefaultTopping("Ketchup"));
                    case 13 -> sandwich.addTopping(new DefaultTopping("Ranch"));
                    case 14 -> sandwich.addTopping(new DefaultTopping("Thousand Islands"));
                    case 15 -> sandwich.addTopping(new DefaultTopping("Vinaigrette"));
                    case 0 -> {
                        adding = false;
                        System.out.println("Finished adding toppings.");
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }

                if (choice >= 1 && choice <= 15) {
                    System.out.println("Topping added successfully.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 0 and 15.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void viewToppings(Sandwich sandwich) {
        System.out.println("Current Toppings:");
        if (sandwich.getToppings().isEmpty()) {
            System.out.println("- No toppings added.");
        } else {
            for (Topping topping : sandwich.getToppings()) {
                System.out.println("- " + topping.getName() + " (" + topping.getType() + "): $" + String.format("%.2f", topping.getPrice()));
            }
        }
    }

    private static void removeTopping(Sandwich sandwich) {

    }

    private static void addExtraPremiumTopping(Sandwich sandwich) {

    }

    private static void addDrink() {
        System.out.print("Enter drink size (Small/Medium/Large): ");
        String size = inputScanner.nextLine();
        order.addProduct(new Drink(size));
        System.out.println(size + " Drink added to order.");
    }

    private static void addChips() {
        System.out.print("Enter chip flavor: ");
        String flavor = inputScanner.nextLine();
        order.addProduct(new BagOfChips(flavor));
        System.out.println(flavor + " Chips added to order.");
    }

    private static void checkout() {
        System.out.println("\n---- Checkout ----");
        System.out.println(order);
        System.out.println("Thank you for your order!");
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
}