package FractionTool;

import java.util.function.BiFunction;

public class FractionWorker {
    public static BiFunction<Fraction, Fraction, Fraction> sum = (frac1, frac2) -> {
        int numerator_ = frac1.numerator() * frac2.denumerator() +
                frac2.numerator() * frac1.denumerator();
        int denumerator_ = frac1.denumerator() * frac2.denumerator();
        int gcd = gcd(Math.abs(numerator_), Math.abs(denumerator_));
        return new Fraction(numerator_ / gcd, denumerator_ / gcd);
    };
    public static BiFunction<Fraction, Fraction, Fraction> product = (frac1, frac2) -> {
        int numerator_ = frac1.numerator() * frac2.numerator();
        int denumerator_ = frac1.denumerator() * frac2.denumerator();
        int gcd = gcd(Math.abs(numerator_), Math.abs(denumerator_));
        return new Fraction(numerator_ / gcd, denumerator_ / gcd);
    };

    public static int gcd(int a, int b) {
        if (a == 0 || b == 0) {
            return Math.max(a, b);
        }
        return a < b ? gcd(a, b % a) : gcd(a % b, b);
    }
}
