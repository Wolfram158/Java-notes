package table;

public class Nope implements CommonExpression {
    private char c;
    public Nope(char c) {
        this.c = c;
    }

    public char get() {
        return c;
    }
    @Override
    public double evaluate(double... args) {
        return 0;
    }

    @Override
    public String toString() {
        return "" + c;
    }
}
