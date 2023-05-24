package Bool;

public class Nope implements CommonExpression {
  
    private char c;
  
    public Nope(char c) {
        this.c = c;
    }

    public char get() {
        return c;
    }
  
    @Override
    public boolean evaluate(boolean... args) {
        return false;
    }

    @Override
    public String toString() {
        return "" + c;
    }
}
