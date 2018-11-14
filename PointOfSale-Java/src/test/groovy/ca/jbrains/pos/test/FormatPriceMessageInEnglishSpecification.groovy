package ca.jbrains.pos.test

import spock.lang.Specification
import spock.lang.Unroll

class FormatPriceMessageInEnglishSpecification extends Specification {
    @Unroll("#priceInEuroCents eurocents formats as #text")
    def "format price messages"() {
        def price = Price.euroCents(priceInEuroCents)

        expect:
        new EnglishLanguageFormatForEurope().formatPrice(price) == text

        where:
        priceInEuroCents || text
        795              || "EUR 7.95"
        0                || "EUR 0.00"
        1                || "EUR 0.01"
        10               || "EUR 0.10"
        12               || "EUR 0.12"
        1691             || "EUR 16.91"
        600              || "EUR 6.00"
        12932384         || "EUR 129\u2009323.84"
    }
}
