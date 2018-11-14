package ca.jbrains.pos.test

import spock.lang.Specification
import spock.lang.Unroll

class FormatPriceMessageInEnglishSpecification extends Specification {
    @Unroll("#price formats as #text")
    def "format price messages"() {
        expect:
        new EnglishLanguageFormatForEurope().formatPrice(price) == text

        where:
        price                || text
        Price.euroCents(795) || "EUR 7.95"
    }
}
