package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest extends FindPriceInCatalogContract {

    @Override
    protected Catalog catalogWith(final String barcode, Price matchingPrice) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put("not " + barcode, new Price(0));
            put("definitely not " + barcode, new Price(0));
            put(barcode, matchingPrice);
            put("still not " + barcode + ", you idiot", new Price(0));
        }});
    }

    @Override
    protected Catalog catalogWithout(String barcodeToAvoid) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put("not " + barcodeToAvoid, new Price(0));
            put("definitely not " + barcodeToAvoid, new Price(0));
            put("still not " + barcodeToAvoid + ", you idiot", new Price(0));
        }});
    }

    public static class InMemoryCatalog implements Catalog {
        private final Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }
    }
}
