package table;

public class Variable implements CommonExpression {
    private String variableName;
    public Variable(String variableName) {
        this.variableName = variableName;
    }
    @Override
    public double evaluate(double... args) {
        try {
            return args[Integer.parseInt(variableName)];
        } catch (RuntimeException e) {
            System.out.println("Unidentified variable");
        }
        return 0;
    }
    @Override
    public String toString() {
        return "(x_"+"("+variableName+")"+")";
    }

    public char get() {
        return ' ';
    }
}
