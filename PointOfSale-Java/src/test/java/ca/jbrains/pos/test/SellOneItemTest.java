package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display);

        sale.onBarcode("12345");

        Assert.assertEquals("EUR 7.50", display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display);

        sale.onBarcode("23456");

        Assert.assertEquals("EUR 12.95", display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display);

        sale.onBarcode("99999");

        Assert.assertEquals("Product not found: 99999", display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        Display display = new Display();
        Sale sale = new Sale(display);

        sale.onBarcode("");

        Assert.assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Sale {
        private Display display;

        public Sale(Display display) {this.display = display;}

        public void onBarcode(String barcode) {
            Map<String, String> pricesByBarcode = new HashMap<String, String>() {{
                put("12345", "EUR 7.50");
                put("23456", "EUR 12.95");
            }};

            if ("".equals(barcode)) {
                display.setText("Scanning error: empty barcode");
                return;
            }

            String priceAsText = pricesByBarcode.get(barcode);
            if (priceAsText == null) {
                display.setText(String.format("Product not found: %s", barcode));
            }
            else {
                display.setText(priceAsText);
            }
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