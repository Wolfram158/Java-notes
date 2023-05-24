package Bool;

public class Variable implements CommonExpression{
    private String variableName;
    public Variable(String variableName) {
        this.variableName = variableName;
    }
    @Override
    public boolean evaluate(boolean... args) {
        if ('a' <= variableName.charAt(0) && variableName.charAt(0) <= 'z') {
            return args[variableName.charAt(0) - 97];
        } else {
            throw new RuntimeException("Unidentified variable");
        }
    }
    @Override
    public String toString() {
        return variableName;
    }

    public char get() {
        return ' ';
    }
}
