package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

public abstract class FindPriceInCatalogContract {
    @Test
    public void productFound() throws Exception {
        final Price matchingPrice = Price.euroCents(795);
        Catalog catalog = catalogWith("12345", matchingPrice);
        Assert.assertEquals(matchingPrice, catalog.findPrice("12345"));
    }

    @Test
    public void productNotFound() throws Exception {
        Catalog catalog = catalogWithout("12345");
        Assert.assertEquals(null, catalog.findPrice("12345"));
    }

    protected abstract Catalog catalogWith(String barcode, Price matchingPrice);

    protected abstract Catalog catalogWithout(String barcodeToAvoid);
}
