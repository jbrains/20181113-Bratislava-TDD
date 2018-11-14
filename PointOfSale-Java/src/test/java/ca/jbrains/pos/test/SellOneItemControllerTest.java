package ca.jbrains.pos.test;

import org.junit.Test;
import org.mockito.Mockito;

public class SellOneItemControllerTest {
    @Test
    public void productFound() throws Exception {
        Catalog catalog = Mockito.mock(Catalog.class);
        Display display = Mockito.mock(Display.class);

        Price matchingPrice = Price.euroCents(795);
        Mockito.when(catalog.findPrice("::barcode with matching price::"))
                .thenReturn(matchingPrice);

        new SellOneItemController(catalog, display).onBarcode("::barcode with matching price::");

        Mockito.verify(display).displayPrice(matchingPrice);
    }

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = Mockito.mock(Catalog.class);
        Display display = Mockito.mock(Display.class);
        final String missingBarcode = "::missing barcode::";

        Mockito.when(catalog.findPrice(missingBarcode)).thenReturn(null);

        new SellOneItemController(catalog, display).onBarcode(missingBarcode);

        Mockito.verify(display).displayProductNotFoundMessage(missingBarcode);
    }

    public interface Catalog {
        Price findPrice(String barcode);
    }

    public interface Display {
        void displayPrice(Price price);

        void displayProductNotFoundMessage(String missingBarcode);
    }

    public static class SellOneItemController {
        private final Catalog catalog;
        private final Display display;

        public SellOneItemController(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            Price price = catalog.findPrice(barcode);
            if (price == null)
                display.displayProductNotFoundMessage(barcode);
            else
                display.displayPrice(price);
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
