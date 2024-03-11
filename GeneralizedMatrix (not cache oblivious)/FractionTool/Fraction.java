package FractionTool;

public record Fraction(int numerator, int denumerator) {
    @Override
    public String toString() {
        return numerator + "/" + denumerator;
    }
}
