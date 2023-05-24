package Bool;

public class Or implements CommonExpression {
    private CommonExpression l, r;
    public Or(CommonExpression l, CommonExpression r) {
        this.l = l;
        this.r = r;
    }
    @Override
    public boolean evaluate(boolean... args) {
        return Or(l.evaluate(args), r.evaluate(args));
    }

    protected boolean Or(boolean a, boolean b) {
        return (a == true) || (b == true);
    }

    @Override
    public String toString() {
        return "(" + l + "|" + r + ")";
    }

    public char get() {
        return ' ';
    }
}
