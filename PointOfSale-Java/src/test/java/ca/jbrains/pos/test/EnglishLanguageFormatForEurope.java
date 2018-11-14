package ca.jbrains.pos.test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class EnglishLanguageFormatForEurope implements MessageFormat {

    private final DecimalFormat decimalFormat = createDecimalFormat();

    public EnglishLanguageFormatForEurope() {
    }

    @Override
    public String formatPrice(Price price) {
        return decimalFormat.format(price.inEuro());
    }

    private static DecimalFormat createDecimalFormat() {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setGroupingSeparator('\u2009');

        DecimalFormat decimalFormat = new DecimalFormat("EUR ############0.00");
        decimalFormat.setGroupingSize(3);
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        return decimalFormat;
    }
}
