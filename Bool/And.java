package Bool;

public class And implements CommonExpression {
    private CommonExpression l, r;

    public And(CommonExpression l, CommonExpression r) {
        this.l = l;
        this.r = r;
    }

    @Override
    public boolean evaluate(boolean... args) {
        return And(l.evaluate(args), r.evaluate(args));
    }

    protected boolean And(boolean x, boolean y) {
        return (x == true) && (y == true);
    }

    @Override
    public String toString() {
        return "(" + l + "&" + r + ")";
    }

    public char get() {
        return ' ';
    }
}
