public class Sudoku {
    public static void main(String[] args) {
        int[][] board = { {0, 0, 7, 0, 0, 3, 4, 9, 6},
                {9, 3, 0, 0, 5, 0, 0, 1, 8},
                {8, 2, 0, 0, 0, 9, 7, 0, 0},
                {0, 0, 5, 3, 8, 7, 0, 0, 2},
                {6, 0, 0, 0, 9, 0, 0, 0, 0},
                {0, 9, 0, 5, 1, 6, 0, 0, 0},
                {4, 5, 9, 0, 0, 1, 0, 0, 7},
                {3, 0, 0, 2, 7, 0, 0, 0, 0},
                {0, 6, 2, 0, 4, 0, 0, 3, 1} };
        solve(board);
        printBoard(board);
    }

    public static boolean solve(int[][] board) {
        int x = 0;
        int y = 0;
        boolean signal = false;
        for (int i = 0; i < 9; i++) {
            signal = false;
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    y = i;
                    x = j;
                    signal = true;
                    break;
                }
            }
            if (signal) {
                break;
            }
        }
        if (!signal) {
            return true;
        }
        int startRow = y - y % 3;
        int startColumn = x - x % 3;
        for (int num = 1; num < 10; num++) {
            if (isRow(board, y, num) &&
                    isColumn(board, x, num) &&
                    isSquare(board, startRow, startColumn, num)) {
                board[y][x] = num;
                if (solve(board)) {
                    return true;
                } else {
                    board[y][x] = 0;
                }
            }
        }
        return false;
    }

    public static boolean isRow(int[][] board, int row, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        return true;
    }

    public static boolean isColumn(int[][] board, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSquare(int [][] board, int startRow, int startColumn, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startColumn] == num) {
                    return false;
                }
            }
        }
        return true;
    }

   public static void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j == 2 || j == 5) {
                    System.out.print(board[i][j] + " | ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println("_ _ _   ".repeat(3));
            }
        }
   }
}
