package ca.jbrains.arithmetic.spec

import ca.jbrains.arithmetic.test.AddFractionsTest
import spock.lang.Unroll

class ParseFractionSpec {
    @Unroll("#{text} parses as #{expected}")
    def "fraction parses as value"() {
        expect:
        AddFractionsTest.Fraction.parse(text).equals(expected)

        where:
        text || expected
        "0"  || 0
    }
}
