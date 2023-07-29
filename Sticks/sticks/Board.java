package sticks;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Board {
    private final int x;
    private final int y;
    private Set<Stick> usedMoves = new HashSet<>();

    private String[][] configuration;

    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        this.configuration = new String[2 * y + 1][2 * x + 1];
        for (int i = 0; i < 2 * y + 1; i++) {
            if (i % 2 == 1) {
                Arrays.fill(configuration[i], " ");
                continue;
            }
            for (int j = 0; j < 2 * x + 1; j++) {
                configuration[i][j] = j % 2 == 1 ? " " : ".";
            }
        }
    }

    Set<Stick> getUsedMoves() {
        return usedMoves;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    String getConfiguration(int x, int y) {
        return configuration[y][x];
    }

    void setConfiguration(int x, int y, String sym) {
        configuration[y][x] = sym;
    }

    void setUsedMoves(int[] coords) {
        usedMoves.add(new Stick(new Point(coords[0], coords[1]), new Point(coords[2], coords[3])));
    }

    public void printBoard() {
        for (String[] row : configuration) {
            System.out.println(String.join("  ", row));
        }
        System.out.println();
    }
}
