package org.example.service;

import org.example.model.Item;
import org.example.model.PurchaseSummary;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TaxCalculatorIntegrationTest {
    @Test
    public void testMainProgramWithValidInput() {
        TaxCalculator taxCalculator = new TaxCalculator(); // Use real TaxCalculator

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        Item item1 = new Item("book", BigDecimal.valueOf(12.99), false, true);
        Item item2 = new Item("imported chocolate", BigDecimal.valueOf(5.99), true, true);
        Item item3 = new Item("music CD", BigDecimal.valueOf(12.99), true, false);
        Item item4 = new Item("bottle of perfume", BigDecimal.valueOf(5.99), false, false);
        List<Item> basket = List.of(item1, item2, item3, item4);

        Invoice invoice = new Invoice();
        for (Item item : basket) {
            BigDecimal tax = taxCalculator.calculateTax(item);
            BigDecimal totalPrice = item.getPrice().add(tax);
            invoice.addItem(new PurchaseSummary(item, tax, totalPrice));
        }

        ReceiptPrinter printer = new ReceiptPrinter(invoice);
        printer.printReceipt();

        String output = outputStream.toString();
        assertTrue(output.contains("book: 12.99"));
        assertTrue(output.contains("imported chocolate: 6.29"));
        assertTrue(output.contains("Sales Taxes: 0.30"));
        assertTrue(output.contains("Total: 19.28"));
    }
}
