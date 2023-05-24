package Bool;

public class Negate implements CommonExpression {
    private CommonExpression l;
    public Negate(CommonExpression l) {
        this.l = l;
    }
    @Override
    public boolean evaluate(boolean... args) {
        return Negate(l.evaluate(args));
    }

    protected boolean Negate(boolean x) {
        return x != true;
    }

    @Override
    public String toString() {
        return "~" + "(" + l + ")";
    }

    public char get() {
        return ' ';
    }
}
