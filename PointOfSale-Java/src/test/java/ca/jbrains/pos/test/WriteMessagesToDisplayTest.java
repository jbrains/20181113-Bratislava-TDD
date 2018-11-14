package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class WriteMessagesToDisplayTest {
    @Test
    public void displayPrice() throws Exception {
        Assert.assertEquals("::formatted price::", formattedPrice(Price.euroCents(795)));
    }

    private String formattedPrice(Price price) {
        StringWriter canvas = new StringWriter();
        new WriterDisplay(canvas, new MessageFormat() {
            @Override
            public String formatPrice(Price price) {
                return "::formatted price::";
            }

            @Override
            public String formatProductNotFoundMessage(String barcodeNotFound) {
                return null;
            }

            @Override
            public String formatScannedEmptyBarcodeMessage() {
                return null;
            }
        }).displayPrice(price);
        String[] lines = canvas.toString().split(System.lineSeparator());
        return lines[0];
    }

    public static class WriterDisplay {

        private final PrintWriter out;
        private final MessageFormat messageFormat;

        public WriterDisplay(StringWriter stringWriter, MessageFormat messageFormat) {
            out = new PrintWriter(stringWriter, true);
            this.messageFormat = messageFormat;
        }

        public void displayPrice(Price price) {
            out.println(messageFormat.formatPrice(price));
        }
    }
}
