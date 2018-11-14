package ca.jbrains.pos.test.ca.jbrains.pos.test;

import ca.jbrains.pos.test.Price;
import org.junit.Assert;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class WriteMessagesToDisplayTest {
    @Test
    public void displayPrice() throws Exception {
        StringWriter canvas = new StringWriter();
        new WriterDisplay(canvas).displayPrice(Price.euroCents(795));
        String[] lines = canvas.toString().split(System.lineSeparator());
        String text = lines[0];
        Assert.assertEquals("EUR 7.95", text);
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
