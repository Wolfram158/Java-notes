package table;

public class Negate implements CommonExpression {
    private CommonExpression l;

    public Negate(CommonExpression l) {
        this.l = l;
    }

    public double evaluate(double... args) {
        return Negate(l.evaluate(args));
    }

    protected double Negate(double x) {
        return -x;
    }

    @Override
    public String toString() {
        return "(" + "-" + l + ")";
    }

    public char get() {
        return ' ';
    }
}
