package sticks;

import java.util.Scanner;

public class HumanPlayer implements Player {
    int score;
    final String name;
    final String sign;
    final Scanner scanner = new Scanner(System.in);

    public HumanPlayer(String name, String sign) {
        this.score = 0;
        this.name = name;
        this.sign = sign;
    }

    final public int[] makeMove(Board board) {
        int i = 0;
        int[] coords = new int[4];
        boolean notEnd = true;
        System.out.print("Player " + name + " is inputting x1, y1, x2, y2 respectively: ");
        while (i < 4 || notEnd) {
            if (scanner.hasNextInt()) {
                coords[i] = 2 * (scanner.nextInt() - 1);
            } else {
                System.out.println("Coordinates must be integer.");
                scanner.nextLine();
                continue;
            }
            if (i % 2 == 0 && !(coords[i] >= 0 && coords[i] <= 2 * board.getX())) {
                System.out.println("Move outs of range.");
                i = 0;
                continue;
            }
            if (i % 2 == 1 && !(coords[i] >= 0 && coords[i] <= 2 * board.getY())) {
                System.out.println("Move outs of range.");
                i = 0;
                continue;
            }
            i++;
            if (i == 4 && !(((coords[0] == coords[2]) && (Math.abs(coords[1] - coords[3]) == 2)) ||
                    ((coords[1] == coords[3]) && (Math.abs(coords[0] - coords[2]) == 2)))) {
                System.out.println("Incorrect stick.");
                i = 0;
                continue;
            }
            if (i == 4 && board.getUsedMoves().contains(new Stick(new Point(coords[0], coords[1]),
                    new Point(coords[2], coords[3])))) {
                System.out.println(board.getUsedMoves());
                System.out.println("This move has already been.");
                i = 0;
            } else if (i == 4) {
                board.setUsedMoves(coords);
                notEnd = false;
            }
        }
        if (Math.abs(coords[1] - coords[3]) == 2) {
            board.setConfiguration(coords[0], Math.max(coords[1], coords[3]) - 1, "|");
        } else {
            board.setConfiguration(Math.min(coords[0], coords[2]) + 1, coords[1], "_");
        }
        return coords;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void incScore() {
        score++;
    }

    public String getSign() {
        return sign;
    }

    public void setScore(int value) {
        score = value;
    }
}
