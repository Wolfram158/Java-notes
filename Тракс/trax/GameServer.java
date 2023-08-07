package trax;

public class GameServer {
    private int whoseTurn = 0;
    private Configuration board;
    private Player[] players;

    public GameServer(Configuration board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public void play() {
        System.out.printf("Player %s is moving.\n", players[whoseTurn].getName());
        players[whoseTurn].makeMove(board);
        if (board.hasCycle(players[whoseTurn].getColor())) {
            System.out.printf("Player %s has won.", players[whoseTurn].getName());
            return;
        }
        whoseTurn = (whoseTurn + 1) % players.length;
        System.out.println(board);
        play();
    }
}
