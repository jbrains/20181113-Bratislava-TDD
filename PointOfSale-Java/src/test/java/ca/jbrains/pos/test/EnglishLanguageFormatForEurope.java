package ca.jbrains.pos.test;

public class EnglishLanguageFormatForEurope implements MessageFormat {
    public EnglishLanguageFormatForEurope() {
    }

    @Override
    public String formatPrice(Price price) {
        return String.format("EUR %.2f", price.inEuro());
    }
}
