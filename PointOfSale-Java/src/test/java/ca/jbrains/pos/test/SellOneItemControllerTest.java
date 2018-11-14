package ca.jbrains.pos.test;

import org.junit.Test;
import org.mockito.Mockito;

public class SellOneItemControllerTest {
    @Test
    public void productFound() throws Exception {
        Catalog catalog = Mockito.mock(Catalog.class);
        Display display = Mockito.mock(Display.class);

        Price matchingPrice = Price.euroCents(795);
        Mockito.when(catalog.findPrice("12345"))
                .thenReturn(matchingPrice);

        new SellOneItemController(catalog, display).onBarcode("12345");

        Mockito.verify(display).displayPrice(matchingPrice);
    }

    public interface Catalog {
        Price findPrice(String barcode);
    }
    public interface Display {
        void displayPrice(Price price);
    }
    public static class SellOneItemController {
        private final Catalog catalog;
        private final Display display;

        public SellOneItemController(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            display.displayPrice(catalog.findPrice(barcode));
        }
    }

    public static class Price {
        public static Price euroCents(int centsValue) {
            return new Price();
        }

        @Override
        public String toString() {
            return "a Price";
        }
    }
}
