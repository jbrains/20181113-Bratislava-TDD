package ca.jbrains.arithmetic.spec


import ca.jbrains.arithmetic.test.Fraction
import spock.lang.Specification
import spock.lang.Unroll

class AddFractionsSpec extends Specification {
    @Unroll("#augendAsText + #addendAsText = #expectedSumAsText")
    def "add fractions"(String augendAsText, String addendAsText, String expectedSumAsText) {
        given:
        def augend = Fraction.parse(augendAsText)
        def addend = Fraction.parse(addendAsText)
        def expectedSum = Fraction.parse(expectedSumAsText)

        expect:
        augend.plus(addend).equals(expectedSum)

        where:
        augendAsText | addendAsText || expectedSumAsText
        0            | 0            || 0
    }
}
