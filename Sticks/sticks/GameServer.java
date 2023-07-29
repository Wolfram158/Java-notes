package sticks;

import java.util.ArrayList;
import java.util.List;

public class GameServer {
    final private Board board;
    final private Player[] players;
    private int whoseTurn = 0;
    private int stickCounter = 0;
    private int currentBonus = 0;
    private final int limit;

    public GameServer(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        this.limit = board.getX() * (board.getY() + 1) + board.getY() * (board.getX() + 1);
    }

    public void play() {
        while (stickCounter != limit) {
            int[] currentMove = players[whoseTurn].makeMove(board);
            stickCounter++;
            if (currentBonus > 0) {
                currentBonus--;
            }
            int x1, y1, x2, y2;
            if (currentMove[0] != currentMove[2]) {
                x1 = Math.min(currentMove[0], currentMove[2]);
                x2 = Math.max(currentMove[0], currentMove[2]);
                y1 = currentMove[1];
                y2 = currentMove[1];
            } else {
                y1 = Math.min(currentMove[1], currentMove[3]);
                y2 = Math.max(currentMove[1], currentMove[3]);
                x1 = currentMove[0];
                x2 = currentMove[0];
            }

            boolean signal = true;

            if (y1 == 0 && y2 == 0) {
                signal = false;
                if (board.getConfiguration(x2 - 1, 0).equals("_") &&
                        board.getConfiguration(x2 - 1, 2).equals("_") &&
                        board.getConfiguration(x2, 1).equals("|") &&
                        board.getConfiguration(x2 - 2, 1).equals("|")) {
                    currentBonus++;
                    players[whoseTurn].incScore();
                    board.setConfiguration(x2 - 1, 1, players[whoseTurn].getSign());
                }
            }

            if (y1 == 2 * board.getY() && y1 == y2) {
                signal = false;
                if (board.getConfiguration(x2 - 1, y2 - 2).equals("_") &&
                        board.getConfiguration(x2 - 1, y2).equals("_") &&
                        board.getConfiguration(x2, y2 - 1).equals("|") &&
                        board.getConfiguration(x2 - 2, y2 - 1).equals("|")) {
                    currentBonus++;
                    players[whoseTurn].incScore();
                    board.setConfiguration(x2 - 1, y2 - 1, players[whoseTurn].getSign());
                }
            }

            if (x1 == 0 && x1 == x2) {
                signal = false;
                if (board.getConfiguration(x2 + 1, y2 - 2).equals("_") &&
                        board.getConfiguration(x2 + 1, y2).equals("_") &&
                        board.getConfiguration(x2, y2 - 1).equals("|") &&
                        board.getConfiguration(x2 + 2, y2 - 1).equals("|")) {
                    currentBonus++;
                    players[whoseTurn].incScore();
                    board.setConfiguration(x2 + 1, y2 - 1, players[whoseTurn].getSign());
                }
            }

            if (x1 == 2 * board.getX() && x1 == x2) {
                signal = false;
                if (board.getConfiguration(x2 - 1, y2 - 2).equals("_") &&
                        board.getConfiguration(x2 - 1, y2).equals("_") &&
                        board.getConfiguration(x2, y2 - 1).equals("|") &&
                        board.getConfiguration(x2 - 2, y2 - 1).equals("|")) {
                    currentBonus++;
                    players[whoseTurn].incScore();
                    board.setConfiguration(x2 - 1, y2 - 1, players[whoseTurn].getSign());
                }
            }

            if (x1 == x2 && signal) {
                if (board.getConfiguration(x2 - 1, y2 - 2).equals("_") &&
                        board.getConfiguration(x2 - 1, y2).equals("_") &&
                        board.getConfiguration(x2, y2 - 1).equals("|") &&
                        board.getConfiguration(x2 - 2, y2 - 1).equals("|")) {
                    currentBonus++;
                    players[whoseTurn].incScore();
                    board.setConfiguration(x2 - 1, y2 - 1, players[whoseTurn].getSign());
                }
                if (board.getConfiguration(x2 + 1, y2 - 2).equals("_") &&
                        board.getConfiguration(x2 + 1, y2).equals("_") &&
                        board.getConfiguration(x2, y2 - 1).equals("|") &&
                        board.getConfiguration(x2 + 2, y2 - 1).equals("|")) {
                    currentBonus++;
                    players[whoseTurn].incScore();
                    board.setConfiguration(x2 + 1, y2 - 1, players[whoseTurn].getSign());
                }
            }

            if (y1 == y2 && signal) {
                if (board.getConfiguration(x2 - 1, y2 - 2).equals("_") &&
                        board.getConfiguration(x2 - 1, y2).equals("_") &&
                        board.getConfiguration(x2, y2 - 1).equals("|") &&
                        board.getConfiguration(x2 - 2, y2 - 1).equals("|")) {
                    currentBonus++;
                    players[whoseTurn].incScore();
                    board.setConfiguration(x2 - 1, y2 - 1, players[whoseTurn].getSign());
                }
                if (board.getConfiguration(x2 - 1, y2).equals("_") &&
                        board.getConfiguration(x2 - 1, y2 + 2).equals("_") &&
                        board.getConfiguration(x2, y2 + 1).equals("|") &&
                        board.getConfiguration(x2 - 2, y2 + 1).equals("|")) {
                    currentBonus++;
                    players[whoseTurn].incScore();
                    board.setConfiguration(x2 + 1, y2 - 1, players[whoseTurn].getSign());
                }
            }

            board.printBoard();

            if (currentBonus == 0) {
                whoseTurn = (whoseTurn + 1) % players.length;
            }
        }

        List<Integer> winnerOrDraw = new ArrayList<>();
        int maxScore = 0;

        for (Player player : players) {
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
            }
        }

        for (int i = 0; i < players.length; i++) {
            if (players[i].getScore() == maxScore) {
                winnerOrDraw.add(i);
            }
        }

        if (winnerOrDraw.size() == 1) {
            System.out.println("Player " + players[winnerOrDraw.get(0)].getName() + " has won.");
        } else {
            System.out.println("No one won.");
        }

        for (Player player : players) {
            player.setScore(0);
        }
    }
}
