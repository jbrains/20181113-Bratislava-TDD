package ca.jbrains.arithmetic.spec

import ca.jbrains.arithmetic.test.AddFractionsTest
import spock.lang.Specification
import spock.lang.Unroll

class AddFractionsSpec extends Specification {
    @Unroll("#augendAsText + #addendAsText = #expectedSumAsText")
    def "add fractions"(String augendAsText, String addendAsText, String expectedSumAsText) {
        given:
        def augend = AddFractionsTest.Fraction.parse(augendAsText)
        def addend = AddFractionsTest.Fraction.parse(addendAsText)
        def expectedSum = AddFractionsTest.Fraction.parse(expectedSumAsText)

        expect:
        augend.plus(addend).equals(expectedSum)

        where:
        augendAsText | addendAsText || expectedSumAsText
        0            | 0            || 0
    }
}
