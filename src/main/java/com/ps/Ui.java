package com.ps;

import com.ps.subwayz.Order;

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
                    "4)Checkout\n" +
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
    }
}