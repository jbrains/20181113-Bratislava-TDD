package ca.jbrains.arithmetic.spec


import ca.jbrains.arithmetic.test.Fraction
import spock.lang.Specification
import spock.lang.Unroll

class ParseFractionSpec extends Specification {
    @Unroll("#text parses as #expected")
    def "fraction parses as value"(text, expected) {
        expect:
        Fraction.parse(text).equals(expected)

        where:
        text || expected
        "0"  || new Fraction(0)
    }
}
