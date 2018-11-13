package ca.jbrains.arithmetic.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Assert.assertEquals(new Fraction(0), new Fraction(0).plus(new Fraction(0)));
    }

    @Test
    public void notZeroPlusZero() throws Exception {
        Assert.assertEquals(new Fraction(4), new Fraction(4).plus(new Fraction(0)));
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Assert.assertEquals(new Fraction(7), new Fraction(0).plus(new Fraction(7)));
    }
    @Test
    public void nonZeroIntegers() throws Exception {
        Assert.assertEquals(new Fraction(9), new Fraction(4).plus(new Fraction(5)));
    }

    @Test
    public void sameDenominator() throws Exception {
        Assert.assertEquals(new Fraction(3, 5), new Fraction(1, 5)
                .plus(new Fraction(2, 5)));
    }
    @Test
    public void relativelyPrimeDenominatorsWithoutReducing() throws Exception {
        Assert.assertEquals(new Fraction(25, 12), new Fraction(4, 3)
                .plus(new Fraction(3, 4)));
    }
    @Test
    public void differentDenominatorsWithCommonFactor() throws Exception {
        Assert.assertEquals(new Fraction(3, 4), new Fraction(1, 4)
                .plus(new Fraction(1, 2)));
    }
    public static class Fraction {
        private final int numerator;
        private final int denominator;

        public Fraction(int integerValue) {
            this(integerValue, 1);
        }

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction that) {
            return new Fraction(
                    this.numerator * that.denominator
                            + that.numerator * this.denominator,
                    this.denominator * that.denominator);
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator * that.denominator
                        == that.numerator * this.denominator;
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return this.numerator ^ 13 + this.denominator ^ 17;
        }
    }
}
