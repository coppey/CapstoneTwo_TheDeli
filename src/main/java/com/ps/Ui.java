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

        boolean isToasted = false;
        System.out.print("Would you like the sandwich toasted? (y/n): ");
        String toastChoice = inputScanner.nextLine().toLowerCase().trim();
        if (toastChoice.equals("y")) {
            isToasted = true;
        }

        // Sandwich initialization
        Sandwich sandwich = new Sandwich(breadType, size, isToasted);

        // Choose Regular Meat
        boolean validMeat = false;
        while (!validMeat) {
            System.out.println(
                    "Select Regular Meat:\n" +
                            "1. Steak\n" +
                            "2. Ham\n" +
                            "3. Salami\n" +
                            "4. Roast Beef\n" +
                            "5. Chicken\n" +
                            "6. Bacon\n" +
                            "Choice: "
            );
            try {
                int meatChoice = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline
                String meatName = switch (meatChoice) {
                    case 1 -> "Steak";
                    case 2 -> "Ham";
                    case 3 -> "Salami";
                    case 4 -> "Roast Beef";
                    case 5 -> "Chicken";
                    case 6 -> "Bacon";
                    default -> null;
                };
                if (meatName != null) {
                    sandwich.addTopping(new Meat(meatName, sandwich.getRegularMeatPrice()), false);
                    validMeat = true;
                } else {
                    System.out.println("Invalid choice. Please select a valid meat.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }

        // Regular Cheese Selection
        boolean validCheese = false;
        while (!validCheese) {
            System.out.println(
                    "Select Regular Cheese:\n" +
                            "1. American\n" +
                            "2. Provolone\n" +
                            "3. Cheddar\n" +
                            "4. Swiss\n" +
                            "Choice: "
            );
            try {
                int cheeseChoice = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline
                String cheeseName = switch (cheeseChoice) {
                    case 1 -> "American";
                    case 2 -> "Provolone";
                    case 3 -> "Cheddar";
                    case 4 -> "Swiss";
                    default -> null;
                };
                if (cheeseName != null) {
                    sandwich.addTopping(new Cheese(cheeseName, sandwich.getRegularCheesePrice()), false);
                    validCheese = true;
                } else {
                    System.out.println("Invalid choice. Please select a valid cheese.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }

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
                            "10. Olives\n" +
                            "---Sauces---\n" +
                            "11. Mustard\n" +
                            "12. Ketchup\n" +
                            "13. Ranch\n" +
                            "14. Thousand Islands\n" +
                            "15. Vinaigrette\n" +
                            "16. Mayo\n" +
                            "17. Honey Mustard\n" +
                            "0. Done Adding Toppings\n" +
                            "Selection: "
            );

            try {
                int choice = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1 -> sandwich.addTopping(new DefaultTopping("Lettuce"), false);
                    case 2 -> sandwich.addTopping(new DefaultTopping("Peppers"), false);
                    case 3 -> sandwich.addTopping(new DefaultTopping("Onions"), false);
                    case 4 -> sandwich.addTopping(new DefaultTopping("Tomatoes"), false);
                    case 5 -> sandwich.addTopping(new DefaultTopping("Jalapenos"), false);
                    case 6 -> sandwich.addTopping(new DefaultTopping("Cucumbers"), false);
                    case 7 -> sandwich.addTopping(new DefaultTopping("Pickles"), false);
                    case 8 -> sandwich.addTopping(new DefaultTopping("Guacamole"), false);
                    case 9 -> sandwich.addTopping(new DefaultTopping("Mushrooms"), false);
                    case 10 -> sandwich.addTopping(new DefaultTopping("Olive"), false);
                    case 11 -> sandwich.addTopping(new DefaultTopping("Mustard"), false);
                    case 12 -> sandwich.addTopping(new DefaultTopping("Ketchup"), false);
                    case 13 -> sandwich.addTopping(new DefaultTopping("Ranch"), false);
                    case 14 -> sandwich.addTopping(new DefaultTopping("Thousand Islands"), false);
                    case 15 -> sandwich.addTopping(new DefaultTopping("Vinaigrette"), false);
                    case 16 -> sandwich.addTopping(new DefaultTopping("Mayo"), false);
                    case 17 -> sandwich.addTopping(new DefaultTopping("Honey Mustard"), false);
                    case 0 -> {
                        System.out.println("Finished adding toppings.");
                        adding = false;
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
        if (sandwich.getToppings().isEmpty()) {
            System.out.println("No toppings to remove.");
            return;
        }

        System.out.println("Select a topping to remove:");
        int index = 1;
        for (Topping topping : sandwich.getToppings()) {
            System.out.println(index + ". " + topping.getName() + " (" + topping.getType() + ")");
            index++;
        }

        try {
            System.out.print("Choice: ");
            int choice = commandScanner.nextInt();
            commandScanner.nextLine(); // Consume newline

            if (choice > 0 && choice <= sandwich.getToppings().size()) {
                Topping removed = sandwich.getToppings().remove(choice - 1);
                System.out.println(removed.getName() + " removed from the sandwich.");
            } else {
                System.out.println("Invalid choice. No topping removed.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            commandScanner.nextLine(); // Clear invalid input
        }
    }

    private static void addExtraPremiumTopping(Sandwich sandwich) {
        System.out.println("What type of premium topping would you like to add?\n"+
                "1. Extra Meat\n" +
                "2. Extra Cheese\n" +
                "Choice: "
        );
        try {
            int choice = commandScanner.nextInt();
            commandScanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("Select a meat:\n" +
                            "1. Steak\n" +
                            "2. Ham\n" +
                            "3. Salami\n" +
                            "4. Roast Beef\n" +
                            "5. Chicken\n" +
                            "6. Bacon\n" +
                            "Choice: "
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
                        default -> null;
                    };

                    if (meatName != null) {
                        sandwich.addTopping(new Meat(meatName, sandwich.getExtraMeatPrice()), true);
                        System.out.println(meatName + " added as extra premium topping.");
                    } else {
                        System.out.println("Invalid choice. No meat added.");
                    }
                }
                case 2 -> {
                    System.out.println("Select a cheese:\n" +
                            "1. American\n" +
                            "2. Provolone\n" +
                            "3. Cheddar\n" +
                            "4. Swiss\n" +
                            "Choice: ");
                    int cheeseChoice = commandScanner.nextInt();
                    commandScanner.nextLine(); // Consume newline

                    String cheeseName = switch (cheeseChoice) {
                        case 1 -> "American";
                        case 2 -> "Provolone";
                        case 3 -> "Cheddar";
                        case 4 -> "Swiss";
                        default -> null;
                    };

                    if (cheeseName != null) {
                        sandwich.addTopping(new Cheese(cheeseName, sandwich.getExtraCheesePrice()), true);
                        System.out.println(cheeseName + " added as extra premium topping.");
                    } else {
                        System.out.println("Invalid choice. No cheese added.");
                    }
                }
                default -> System.out.println("Invalid choice. No premium topping added.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            commandScanner.nextLine(); // Clear invalid input
        }
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
        System.out.println(order); // Display the order details

        boolean done = false;
        while (!done) {
            System.out.println(
                    "1. Confirm Order (Generate Receipt and Exit to Main Menu)\n" +
                            "2. Cancel Order (Delete Order and Exit to Main Menu)\n" +
                            "Select an option: "
            );
            try {
                int choice = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1 -> {
                        generateReceipt(); // Create receipt file
                        System.out.println("Order confirmed! Receipt has been generated.");
                        done = true;
                    }
                    case 2 -> {
                        order = new Order(); // Reset the order
                        System.out.println("Order canceled. Returning to main menu.");
                        done = true;
                    }
                    default -> System.out.println("Invalid choice. Please select 1 or 2.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }
    }

    private static void generateReceipt() {
        System.out.println("\n---- Checkout ----");
        System.out.println(order); // Display the order details

        boolean done = false;
        while (!done) {
            System.out.println(
                    "1. Confirm Order (Generate Receipt and Exit to Main Menu)\n" +
                            "2. Cancel Order (Delete Order and Exit to Main Menu)\n" +
                            "Select an option: "
            );
            try {
                int choice = commandScanner.nextInt();
                commandScanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1 -> {
                        // Generate receipt using FileManager
                        String filePath = FileManager.writeReceipt(order.toString());
                        if (filePath != null) {
                            System.out.println("Order confirmed! Receipt saved at: " + filePath);
                        } else {
                            System.out.println("Failed to generate the receipt.");
                        }
                        done = true;
                    }
                    case 2 -> {
                        order = new Order(); // Reset the order
                        System.out.println("Order canceled. Returning to main menu.");
                        done = true;
                    }
                    default -> System.out.println("Invalid choice. Please select 1 or 2.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                commandScanner.nextLine(); // Clear invalid input
            }
        }
    }
}