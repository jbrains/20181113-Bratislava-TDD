package ca.jbrains.pos.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class SellOneItemControllerTest {

    private Catalog catalog;
    private Display display;

    @Before
    public void setUp() throws Exception {
        catalog = Mockito.mock(Catalog.class);
        display = Mockito.mock(Display.class);
    }

    @Test
    public void productFound() throws Exception {
        Price matchingPrice = Price.euroCents(795);
        Mockito.when(catalog.findPrice("::barcode with matching price::"))
                .thenReturn(matchingPrice);

        new SellOneItemController(catalog, display).onBarcode("::barcode with matching price::");

        Mockito.verify(display).displayPrice(matchingPrice);
    }

    @Test
    public void productNotFound() throws Exception {
        final String missingBarcode = "::missing barcode::";

        Mockito.when(catalog.findPrice(missingBarcode)).thenReturn(null);

        new SellOneItemController(catalog, display).onBarcode(missingBarcode);

        Mockito.verify(display).displayProductNotFoundMessage(missingBarcode);
    }

    @Test
    public void emptyBarcode() throws Exception {
        new SellOneItemController(null, display).onBarcode("");

        Mockito.verify(display).displayScannedEmptyBarcodeMessage();
    }

    public static class SellOneItemController {
        private final Catalog catalog;
        private final Display display;

        public SellOneItemController(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }

        public void onBarcode(String barcode) {
            if ("".equals(barcode)) {
                display.displayScannedEmptyBarcodeMessage();
                return;
            }

            Price price = catalog.findPrice(barcode);
            if (price == null)
                display.displayProductNotFoundMessage(barcode);
            else
                display.displayPrice(price);
        }
    }

}
