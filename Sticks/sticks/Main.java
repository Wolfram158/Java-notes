package sticks;

public class Main {
    public static void main(String[] args) {
        //Board board = new Board(2, 2);
        HumanPlayer player1 = new HumanPlayer("Peter", "A");
        HumanPlayer player2 = new HumanPlayer("Paul", "B");
        //GameServer place1 = new GameServer(board, new Player[]{player1, player2});
        //place1.play();


        Board bigger = new Board(3, 3);
        HumanPlayer player3 = new HumanPlayer("Michael", "C");
        GameServer place2 = new GameServer(bigger, new Player[]{player1, player2, player3});
        place2.play();
    }
}
