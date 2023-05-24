package Bool;

public class Const implements CommonExpression {
    private int constValue;
    public Const(int constValue) {
        this.constValue = constValue;
    }
    @Override
    public boolean evaluate(boolean... args) {
        return intToBoolean(this.constValue);
    }

    @Override
    public String toString() {
        return ""+constValue;
    }

    private boolean intToBoolean(int input) {
        if((input==0)||(input==1)) {
            return input!=0;
        }else {
            throw new IllegalArgumentException("Входное значение может быть равно только 0 или 1 !");
        }
    }

    public char get() {
        return ' ';
    }
}
