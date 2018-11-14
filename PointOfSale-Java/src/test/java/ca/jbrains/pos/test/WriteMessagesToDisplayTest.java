package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

public class WriteMessagesToDisplayTest {
    @Test
    public void displayPrice() throws Exception {
        Assert.assertEquals("EUR 7.95", formattedPrice(Price.euroCents(795)));
    }

    private String formattedPrice(Price price) {
        StringWriter canvas = new StringWriter();
        new WriterDisplay(canvas).displayPrice(price);
        String[] lines = canvas.toString().split(System.lineSeparator());
        return lines[0];
    }

    public static class WriterDisplay {

        private final PrintWriter out;
        private final EnglishLanguageFormatForEurope englishLanguageFormatForEurope;

        public WriterDisplay(StringWriter stringWriter) {
            out = new PrintWriter(stringWriter, true);
            englishLanguageFormatForEurope = new EnglishLanguageFormatForEurope();
        }

        public void displayPrice(Price price) {
            out.println(englishLanguageFormatForEurope.formatPrice(price));
        }

        private String formatPrice(Price price) {
            return englishLanguageFormatForEurope.formatPrice(price);
        }
    }

    public static class EnglishLanguageFormatForEurope {
        public EnglishLanguageFormatForEurope() {
        }

        String formatPrice(Price price) {
            return String.format("EUR %.2f", price.inEuro());
        }
    }
}
