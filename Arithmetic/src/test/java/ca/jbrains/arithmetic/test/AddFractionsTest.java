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

    public static class Fraction {
        public Fraction(int integerValue) {
        }

        public Fraction plus(Fraction zero) {
            return new Fraction(23);
        }

        public int intValue() {
            return 0;
        }
    }
}
