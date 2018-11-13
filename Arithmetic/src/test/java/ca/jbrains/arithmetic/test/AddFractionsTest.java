package ca.jbrains.arithmetic.test;

import org.junit.Assert;
import org.junit.Test;

public class AddFractionsTest {
    @Test
    public void zeroPlusZero() throws Exception {
        Fraction zero = new Fraction(0);
        Fraction sum = zero.plus(zero);
        Assert.assertEquals(0, sum.intValue());
    }

    @Test
    public void notZeroPlusZero() throws Exception {
        Fraction sum = new Fraction(4).plus(new Fraction(0));
        Assert.assertEquals(4, sum.intValue());
    }

    @Test
    public void zeroPlusNotZero() throws Exception {
        Fraction sum = new Fraction(0).plus(new Fraction(7));
        Assert.assertEquals(7, sum.intValue());
    }
    @Test
    public void nonZeroIntegers() throws Exception {
        Fraction sum = new Fraction(4).plus(new Fraction(5));
        Assert.assertEquals(9, sum.intValue());
    }

    @Test
    public void sameDenominator() throws Exception {
        Fraction sum = new Fraction(1, 5)
                .plus(new Fraction(2, 5));

        Assert.assertEquals(3, sum.getNumerator());
        Assert.assertEquals(5, sum.getDenominator());
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
            } else {
                return new Fraction(this.integerValue + that.integerValue,
                        this.denominator);
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
    }
}
