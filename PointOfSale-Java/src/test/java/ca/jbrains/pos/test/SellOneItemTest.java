package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(new HashMap<String, String>() {{
            put("12345", "EUR 7.50");
            put("23456", "EUR 12.95");
        }}, display);

        sale.onBarcode("12345");

        Assert.assertEquals("EUR 7.50", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(new HashMap<String, String>() {{
            put("12345", "EUR 7.50");
            put("23456", "EUR 12.95");
        }}, display);

        sale.onBarcode("23456");

        Assert.assertEquals("EUR 12.95", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(new HashMap<String, String>() {{
            put("12345", "EUR 7.50");
            put("23456", "EUR 12.95");
        }}, display);

        sale.onBarcode("::unknown barcode::");

        Assert.assertEquals("Product not found: ::unknown barcode::", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(new HashMap<String, String>() {{
            put("12345", "EUR 7.50");
            put("23456", "EUR 12.95");
        }}, display);

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Sale {
        private Display display;
        private final Map<String, String> pricesByBarcode;

        public Sale(Map<String, String> pricesByBarcode, Display display) {
            this.display = display;
            this.pricesByBarcode = pricesByBarcode;
        }

        public void onBarcode(String barcode) {
            if ("".equals(barcode)) {
                displayScannedEmptyBarcodeMessage();
                return;
            }

            String priceAsText = findPrice(barcode);
            if (priceAsText == null) {
                displayProductNotFoundMessage(barcode);
            }
            else {
                displayPrice(priceAsText);
            }
        }

        private String findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }

        private void displayScannedEmptyBarcodeMessage() {
            display.setText("Scanning error: empty barcode");
        }

        private void displayProductNotFoundMessage(String barcode) {
            display.setText(String.format("Product not found: %s", barcode));
        }

        private void displayPrice(String priceAsText) {
            display.setText(priceAsText);
        }
    }

    public static class Display {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}