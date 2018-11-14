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
        "3"  || new Fraction(3)
        "-9"  || new Fraction(-9)
        "-991"  || new Fraction(-991)
        "1/2"  || new Fraction(1, 2)
    }
}
