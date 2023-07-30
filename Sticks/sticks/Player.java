package sticks;

public interface Player {
    String getSign();
    int[] makeMove(Board board);
    int getScore();
    void setScore(int value);
    void incScore();
    String getName();
}
