package MatrixTool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Matrix<T> {
    private List<List<T>> matrix;
    private int rowSize;
    private int columnSize;
    private final T neutral;
    private final BiFunction<T, T, T> sum;
    private final BiFunction<T, T, T> product;
    private final Function<String, T> converter;

    public Matrix(BiFunction<T, T, T> sum,
                  BiFunction<T, T, T> product,
                  Function<String, T> converter,
                  T neutral) {
        this.sum = sum;
        this.product = product;
        this.converter = converter;
        this.neutral = neutral;
        rowSize = -1;
        columnSize = 0;
        matrix = new ArrayList<>();
    }

    public Matrix(List<List<T>> matrix,
                  BiFunction<T, T, T> sum,
                  BiFunction<T, T, T> product,
                  Function<String, T> converter,
                  T neutral) {
        this(sum, product, converter, neutral);
        this.matrix = matrix;
        if (!matrix.isEmpty()) {
            rowSize = matrix.getFirst().size();
            columnSize = matrix.size();
        }
    }

    public void transpose() {
        final List<List<T>> transposed = new ArrayList<>();
        for (int i = 0; i < rowSize; i++) {
            final List<T> newRow = new ArrayList<>();
            for (int j = 0; j < columnSize; j++) {
                newRow.add(matrix.get(j).get(i));
            }
            transposed.add(newRow);
        }
        matrix = transposed;
    }

    public void sum(Matrix<T> other) {
        try {
            if (rowSize != other.rowSize || columnSize != other.columnSize) {
                throw new MatrixException("Can not sum matrices of different sizes.");
            }
            for (int i = 0; i < columnSize; i++) {
                final List<T> row = new ArrayList<>();
                for (int j = 0; j < rowSize; j++) {
                    row.add(sum.apply(matrix.get(i).get(j), other.matrix.get(i).get(j)));
                }
                matrix.set(i, row);
            }
        } catch (final MatrixException e) {
            System.err.println(e.getMessage());
        }
    }

    public Matrix<T> product(Matrix<T> other) {
        try {
            if (rowSize != other.columnSize) {
                throw new MatrixException("Can not multiply provided matrices.");
            }
            final List<List<T>> product = new ArrayList<>();
            for (int k = 0; k < columnSize; k++) {
                final List<T> productRow = new ArrayList<>();
                for (int i = 0; i < other.rowSize; i++) {
                    productRow.add(neutral);
                }
                for (int i = 0; i < other.rowSize; i++) {
                    for (int j = 0; j < other.columnSize; j++) {
                        productRow.set(i, sum.apply(productRow.get(i),
                                this.product.apply(matrix.get(k).get(j),
                                        other.matrix.get(j).get(i))));
                    }
                }
                product.add(productRow);
            }
            return new Matrix<>(product, sum, this.product, converter, neutral);
        } catch (final MatrixException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void readMatrixFromFile(final Path path) {
        invalidate();
        try (final BufferedReader br = Files.newBufferedReader(path)) {
            String rowString;
            String[] row;
            while ((rowString = br.readLine()) != null) {
                final List<T> rowList = new ArrayList<>();
                row = rowString.trim().split("\\s+");
                if (rowSize != row.length) {
                    if (rowSize == -1) {
                        rowSize = row.length;
                    } else {
                        throw new MatrixException("Incorrect format of matrix.");
                    }
                }
                for (String a: row) {
                    rowList.add(converter.apply(a));
                }
                matrix.add(rowList);
                columnSize++;
            }
        } catch (final IOException e) {
            System.err.println("Error while reading matrix from file.");
        } catch (final MatrixException e) {
            System.err.println(e.getMessage());
        } catch (final Exception e) {
            invalidate();
            System.err.println("Incorrect format of matrix.");
        }
    }

    public void writeMatrixToFile(final Path path) {
        try (final BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (int i = 0; i < columnSize; i++) {
                bw.write(String.format("%s%n", matrix.get(i).stream().map(Object::toString)
                        .collect(Collectors.joining(" "))));
            }
        } catch (final IOException e) {
            System.err.println("Error while writing matrix to file.");
        }
    }

    public void invalidate() {
        rowSize = -1;
        columnSize = 0;
        matrix.clear();
    }

}