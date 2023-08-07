package trax;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final String name;
    private final String color;
    private final Scanner scanner;

    public HumanPlayer(final String name, final String color) {
        this.name = name;
        this.color = color;
        this.scanner = new Scanner(System.in);
    }

    public void makeMove(Configuration board) {
        int x1;
        int x2;
        int x3;
        int x4;
        String color12;
        String color34;
        x1 = scanner.nextInt();
        x2 = scanner.nextInt();
        color12 = scanner.nextLine().trim();
        x3 = scanner.nextInt();
        x4 = scanner.nextInt();
        color34 = scanner.nextLine().trim();
        board.add(x1, x2, color12);
        board.add(x3, x4, color34);
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
