package ca.jbrains.pos.test;

public interface MessageFormat {
    String formatPrice(Price price);

    String formatProductNotFoundMessage(String barcodeNotFound);

    String formatScannedEmptyBarcodeMessage();
}
