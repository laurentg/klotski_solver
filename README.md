## Klotski Solver

Automatically solve Klotski puzzle game by a brute-force computation of the graph of boards.
For more information on Klotski (also known as "Huarong Dao"),
please see [wikipedia](https://en.wikipedia.org/wiki/Klotski).

The computed graph of boards has a node for each reachable board state (a valid combination of pieces on the board),
and an edge between each board where a single piece movement can switch from one board state to the other.
Each edge is undirected (bidirectionnal), as each piece movement can be done in both directions.

In order to compute the graph, we simply expand from a open list of boards (starting from the initial position)
to each possible moves. Since the graph is strongly connected there may be loops; in order to prevent the
algorithm from backtracking we store in a map each board that has been already visited. To efficiently process
this, we compute a single integer hash code for a board. Since the number of cells is low and the number of
different pieces on each cells too, a simple long int (64 bits) is enough.

Once the graph is fully completed (a typical graph is composed of around 20 - 30k board nodes),
we simply look for the shortest path between the start board position to any final board position
(a final board position is a board where the large square is at the bottom).

The solver is independant from any Klotski consideration and any puzzle game that is similar
could be solved by the generic solver. A new puzzle to solve should simply implement the `Board` interface:

    public interface Board {
       // Compute all possible moves from this board position
	    public List<Board> computeMoves();
	    // Is this board winning (ie solved)?
	    public boolean isWinning();
	    // Compute a unique key for this board
	    public Long getKey();
	    // Print the board
	    public void print(PrintStream ps);
	    // Is this board equals to another
	    public boolean equals(Object b);
    } 

This program is open-source and is release under a GPLv3 license. **Have fun!**