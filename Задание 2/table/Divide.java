package table;

public class Divide implements CommonExpression {
    private CommonExpression l, r;

    public Divide(CommonExpression l, CommonExpression r) {
        this.l = l;
        this.r = r;
    }

    public double evaluate(double... args) {
        return Divide(l.evaluate(args), r.evaluate(args));
    }

    protected double Divide(double x, double y) {
        return x/y;
    }

    @Override
    public String toString() {
        return "(" + l + "/" + r + ")";
    }

    public char get() {
        return ' ';
    }
}
