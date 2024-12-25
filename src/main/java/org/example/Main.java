package org.example;

import org.example.model.Item;
import org.example.model.PurchaseSummary;
import org.example.service.Invoice;
import org.example.service.ReceiptPrinter;
import org.example.service.TaxCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaxCalculator taxCalculator = new TaxCalculator();

        while (true) {
            System.out.println("Enter items for the basket (or type 'done' to finish and print receipt, 'exit' to quit):");
            List<Item> basket = new ArrayList<>();

            while (true) {
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("done")) {
                    break;
                } else if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    return;
                }

                try {
                    Item item = parseInput(input);
                    basket.add(item);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input format. Please try again.");
                }
            }

            Invoice invoice = new Invoice();
            for (Item item : basket) {
                BigDecimal tax = taxCalculator.calculateTax(item);
                BigDecimal totalPrice = item.getPrice().add(tax);
                invoice.addItem(new PurchaseSummary(item, tax, totalPrice));
            }

            ReceiptPrinter printer = new ReceiptPrinter(invoice);
            printer.printReceipt();
        }
    }

    private static Item parseInput(String input) {
        String[] parts = input.split(" at ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid input format.");
        }

        String namePart = parts[0];
        BigDecimal price = new BigDecimal(parts[1]);

        boolean isImported = namePart.toLowerCase().contains("imported");
        boolean isExempt = namePart.toLowerCase().matches(".*(book|chocolate|pill).*");

        return new Item(namePart.trim(), price, isImported, isExempt);
    }
}