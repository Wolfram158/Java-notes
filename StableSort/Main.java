import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Main {
    public static void main(final String[] args) {
        StableSort utility = new StableSort();
        if (args.length > 1) {
            System.out.printf("Usage cases:%n(1) input file%n(2) empty input");
        } else if (args.length == 1) {
            try  {
                utility.start(Path.of(args[0]));
            } catch (InvalidPathException e) {
                System.out.println("InvalidPathException caught.");
            }
        } else {
            utility.start();
        }
    }
}
