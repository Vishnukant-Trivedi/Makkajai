package org.example;

import org.example.model.ProductModel;
import org.example.model.ReceiptModel;
import org.example.repository.ReceiptRepository;
import org.example.service.TaxCalculatorService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Please give input like this
 * 1 book at 12.49
 * 1 music CD at 14.99
 * 1 chocolate bar at 0.85
 * done
 * And
 * We will see output like this
 * 1 book: 12.49
 * 1 music CD: 16.49
 * 1 chocolate bar: 0.85
 * Sales Taxes: 1.50
 * Total: 29.83
 *
 * If you want to exit the command line enter 'exit'
 *
 * Have added test cases refer to src/test/java/org/example/service/TaxCalculatorServiceTest.java
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaxCalculatorService taxCalculatorService = new TaxCalculatorService();

        while (true) {
            System.out.println("Enter items for the basket (or type 'done' to finish and print receipt, 'exit' to quit):");
            List<ProductModel> basket = new ArrayList<>();

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
                    ProductModel productModel = parseInput(input);
                    basket.add(productModel);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input format. Please try again.");
                }
            }

            ReceiptRepository receiptRepository = new ReceiptRepository();
            for (ProductModel productModel : basket) {
                BigDecimal tax = taxCalculatorService.calculateTax(productModel);
                BigDecimal totalPrice = productModel.getPrice().add(tax);
                receiptRepository.addItem(new ReceiptModel(productModel, tax, totalPrice));
            }

            receiptRepository.printReceipt();
        }
    }

    private static ProductModel parseInput(String input) {
        String[] parts = input.split(" at ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid input format.");
        }

        String namePart = parts[0];
        BigDecimal price = new BigDecimal(parts[1]);

        boolean isImported = namePart.toLowerCase().contains("imported");
        boolean isExempt = namePart.toLowerCase().matches(".*(book|chocolate|pill).*");

        return new ProductModel(namePart.trim(), price, isImported, isExempt);
    }
}