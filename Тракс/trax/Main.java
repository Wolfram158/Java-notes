package trax;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        Player[] players = new Player[]{new HumanPlayer("Peter", "red"), new HumanPlayer("Paul", "black")};
        GameServer place = new GameServer(configuration, players);
        place.play();
    }
}

/*
Player Peter is moving.
1 2 red
3 4 black
[Color: red
Edges: (1, 2) 
, Color: black
Edges: (3, 4) 
]
Player Paul is moving.
4 15 black
13 14 red
[Color: red
Edges: (1, 2) 
, Color: black
Edges: (3, 4) (4, 15) 
, Color: red
Edges: (13, 14) 
]
Player Peter is moving.
2 5 red
6 7 black
[Color: red
Edges: (1, 2) (2, 5) 
, Color: black
Edges: (3, 4) (4, 15) 
, Color: red
Edges: (13, 14) 
, Color: black
Edges: (6, 7) 
]
Player Paul is moving.
15 18 black
16 17 red
[Color: red
Edges: (1, 2) (2, 5) 
, Color: black
Edges: (3, 4) (4, 15) (15, 18) 
, Color: red
Edges: (13, 14) 
, Color: black
Edges: (6, 7) 
, Color: red
Edges: (16, 17) 
]
Player Peter is moving.
5 8 red
9 10 black
[Color: red
Edges: (1, 2) (2, 5) (5, 8) 
, Color: black
Edges: (3, 4) (4, 15) (15, 18) 
, Color: red
Edges: (13, 14) 
, Color: black
Edges: (6, 7) 
, Color: red
Edges: (16, 17) 
, Color: black
Edges: (9, 10) 
]
Player Paul is moving.
18 21 black
19 20 red
[Color: red
Edges: (1, 2) (2, 5) (5, 8) 
, Color: black
Edges: (3, 4) (4, 15) (15, 18) (18, 21) 
, Color: red
Edges: (13, 14) 
, Color: black
Edges: (6, 7) 
, Color: red
Edges: (16, 17) 
, Color: black
Edges: (9, 10) 
, Color: red
Edges: (19, 20) 
]
Player Peter is moving.
8 1 red
12 11 black
Player Peter has won.
 */
