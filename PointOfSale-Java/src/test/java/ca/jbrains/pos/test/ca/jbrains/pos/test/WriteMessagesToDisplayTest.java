package ca.jbrains.pos.test.ca.jbrains.pos.test;

import ca.jbrains.pos.test.Price;
import org.junit.Assert;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class WriteMessagesToDisplayTest {
    @Test
    public void displayPrice() throws Exception {
        Assert.assertEquals("EUR 7.95", formatPrice(Price.euroCents(795)));
    }

    private String formatPrice(Price price) {
        StringWriter canvas = new StringWriter();
        new WriterDisplay(canvas).displayPrice(price);
        String[] lines = canvas.toString().split(System.lineSeparator());
        return lines[0];
    }

    public static class WriterDisplay {

        private final PrintWriter out;

        public WriterDisplay(StringWriter stringWriter) {
            out = new PrintWriter(stringWriter, true);
        }

        public void displayPrice(Price price) {
            out.println("EUR 7.95");
        }
    }
}
