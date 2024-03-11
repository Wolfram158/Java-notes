import java.nio.file.Path;
import java.util.function.Function;

import FractionTool.Fraction;
import FractionTool.FractionWorker;
import MatrixTool.Matrix;

public class Main {
    public static void main(String[] args) {
        Function<String, Fraction> converter = str -> {
            String[] words = str.split("/");
            return new Fraction(Integer.parseInt(words[0]), Integer.parseInt(words[1]));
        };

        Matrix<Fraction> matrix1 = new Matrix<>(FractionWorker.sum, FractionWorker.product,
                converter, new Fraction(0, 1));
        Matrix<Fraction> matrix2 = new Matrix<>(FractionWorker.sum, FractionWorker.product,
                converter, new Fraction(0, 1));
        matrix1.readMatrixFromFile(Path.of("Matrix1"));
        matrix2.readMatrixFromFile(Path.of("Matrix2"));

        matrix1.sum(matrix2);
        matrix1.writeMatrixToFile(Path.of("Sum_1"));

        matrix2.transpose();
        matrix2.writeMatrixToFile(Path.of("Transpose_1"));

        Matrix<Fraction> matrix3 = matrix2.product(matrix1);
        matrix3.writeMatrixToFile(Path.of("Product_1"));


    }

}
