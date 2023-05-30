package table;

public class Subtract implements CommonExpression {
    private CommonExpression l, r;

    public Subtract(CommonExpression l, CommonExpression r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public double evaluate(double... args) {
        return Subtract(l.evaluate(args), r.evaluate(args));
    }

    protected double Subtract(double x, double y) {
        return x-y;
    }

    @Override
    public String toString() {
        return "(" + l + "-" + r + ")";
    }

    public char get() {
        return ' ';
    }
}
