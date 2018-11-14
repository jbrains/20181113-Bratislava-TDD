package ca.jbrains.arithmetic.test;

import java.util.Arrays;
import java.util.List;

public class Fraction {
    private final int numerator;
    private final int denominator;

    public Fraction(int integerValue) {
        this(integerValue, 1);
    }

    public Fraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction parse(String text) {
        List<Integer> fractionParts = parseFraction(text);
        if (fractionParts.isEmpty())
            return new Fraction(parseInt(text));
        else
            return new Fraction(fractionParts.get(0), fractionParts.get(1));
    }

    private static List<Integer> parseFraction(String text) {
        if (text.contains("/")) {
            String[] parts = text.split("/");
            return Arrays.asList(parseInt(parts[0]), parseInt(parts[1]));
        }
        else
            return Arrays.asList();
    }

    private static int parseInt(String text) {
        return Integer.parseInt(text);
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
