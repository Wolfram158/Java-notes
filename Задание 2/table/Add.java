package table;

public class Add implements CommonExpression {
    private CommonExpression l, r;

    public Add(CommonExpression l, CommonExpression r) {
        this.l = l;
        this.r = r;
    }

    public double evaluate(double... args) {
        return Add(l.evaluate(args), r.evaluate(args));
    }

    protected double Add(double x, double y) {
        return x+y;
    }

    @Override
    public String toString() {
        return "(" + l + "+" + r + ")";
    }

    public char get() {
        return ' ';
    }
}
