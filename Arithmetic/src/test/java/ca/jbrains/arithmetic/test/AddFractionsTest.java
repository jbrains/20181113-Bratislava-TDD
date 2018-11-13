package ca.jbrains.arithmetic.test;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction zero = new Fraction(0);
        Fraction sum = zero.plus(zero);
        Assert.assertEquals(new Fraction(0), sum);
    }

    @Test
    public void notZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(4).plus(new Fraction(0));
        Assert.assertEquals(new Fraction(4), sum);
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(7));
        Assert.assertEquals(new Fraction(7), sum);
    }
    @Test
    public void nonZeroIntegers() throws Exception {
        Fraction sum = new Fraction(4).plus(new Fraction(5));
        Assert.assertEquals(new Fraction(9), sum);
    }

    @Test
    public void sameDenominator() throws Exception {
        Fraction sum = new Fraction(1, 5)
                .plus(new Fraction(2, 5));

        Assert.assertEquals(new Fraction(3, 5), sum);
    }
    @Test
    public void relativelyPrimeDenominatorsWithoutReducing() throws Exception {
        Fraction sum = new Fraction(4, 3)
                .plus(new Fraction(3, 4));

        Assert.assertEquals(new Fraction(25, 12), sum);
    }
    @Test
    public void differentDenominatorsWithCommonFactor() throws Exception {
        Fraction sum = new Fraction(1, 4)
                .plus(new Fraction(1, 2));

        Assert.assertEquals(new Fraction(3, 4), sum);
    }
    public static class Fraction {
        private final int integerValue;
        private final int numerator;
        private final int denominator;

        public Fraction(int integerValue) {
            this(integerValue, 1);
        }

        public Fraction(int numerator, int denominator) {
            this.integerValue = numerator;
            this.numerator = numerator;
            this.denominator = denominator;
        }

        public Fraction plus(Fraction that) {
            if (this.denominator == 1) {
                return new Fraction(
                        this.integerValue + that.integerValue);
            } else if (this.denominator == that.denominator) {
                return new Fraction(this.integerValue + that.integerValue,
                        this.denominator);
            } else {
                return new Fraction(
                        this.numerator * that.denominator
                                + that.numerator * this.denominator,
                        this.denominator * that.denominator);
            }
        }

        public int intValue() {
            return integerValue;
        }
        public int getNumerator() {
            return numerator;
        }
        public int getDenominator() {
            return denominator;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof Fraction) {
                Fraction that = (Fraction) other;
                return this.numerator * that.denominator
                        == that.numerator * this.denominator;
            }
            else {
                return false;
            }
        }
        @Override
        public int hashCode() {
            return this.numerator ^ 13 + this.denominator ^ 17;
        }
    }
}
