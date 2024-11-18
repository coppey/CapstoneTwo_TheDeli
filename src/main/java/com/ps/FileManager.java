package com.ps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileManager {

    private static final String RECEIPTS_FOLDER = "receipts";

    // Writes the order details to a receipt file
    public static String writeReceipt(String orderDetails) {
        try {
            // Create "receipts" folder if it doesn't exist
            File receiptsFolder = new File(RECEIPTS_FOLDER);
            if (!receiptsFolder.exists()) {
                receiptsFolder.mkdir();
            }

            // Generate unique file name
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
            String fileName = RECEIPTS_FOLDER + "/" + timestamp + ".txt";

            // Use BufferedWriter to write to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write("---- Receipt ----\n");
                writer.write(orderDetails);
            }

            return fileName; // Return the file path for confirmation
        } catch (Exception e) {
            System.out.println("An error occurred while writing the receipt: " + e.getMessage());
            e.printStackTrace();
            return null; // Indicate failure
        }
    }

    // Reads the content of a receipt file
    public static String readReceipt(String filePath) {
        try {
            return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
        } catch (Exception e) {
            System.out.println("An error occurred while reading the receipt: " + e.getMessage());
            e.printStackTrace();
            return null; // Indicate failure
        }
    }
}
