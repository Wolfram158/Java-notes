package table;

public class Const implements CommonExpression {
    private int constValue;
    public Const(int constValue) {
        this.constValue = constValue;
    }
    @Override
    public double evaluate(double... args) {
        return constValue;
    }

    @Override
    public String toString() {
        return ""+constValue;
    }

    public char get() {
        return ' ';
    }
}
