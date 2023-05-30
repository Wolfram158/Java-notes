package table;

public class Multiply implements CommonExpression {
    private CommonExpression l, r;

    public Multiply(CommonExpression l, CommonExpression r) {
        this.l = l;
        this.r = r;
    }

    public double evaluate(double... args) {
        return Multiply(l.evaluate(args), r.evaluate(args));
    }

    protected double Multiply(double x, double y) {
        return x*y;
    }

    @Override
    public String toString() {
        return "(" + l + "*" + r + ")";
    }

    public char get() {
        return ' ';
    }
}
