package ca.jbrains.pos.test;

import org.junit.Assert;
import org.junit.Test;

public class FormatOtherMessagesInEnglishTest {
    @Test
    public void productNotFoundMessage() throws Exception {
        Assert.assertEquals(
                "Product not found: ::barcode not found::",
                new EnglishLanguageFormatForEurope()
                        .formatProductNotFoundMessage("::barcode not found::"));
    }
}
