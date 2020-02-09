import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class Solver {
    private final Node last;
    private final boolean solvable;

    private class Node {
        Board board;
        int moves;
        Node previous;
        int priority;

        public Node(Board board, int moves, Node previous, int priority) {
            this.board = board;
            this.moves = moves;
            this.previous = previous;
            this.priority = priority;
        }
    }

    private static class ByManhattan implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return a.priority - b.priority;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException("initial board must not be null");
        Comparator<Node> BY_MANHATTAN = new ByManhattan();
        MinPQ<Node> pq = new MinPQ<Node>(1, BY_MANHATTAN);
        MinPQ<Node> pqTwin = new MinPQ<Node>(1, BY_MANHATTAN);

        Node min = new Node(initial, 0, null, initial.manhattan());
        Node minTwin = new Node(initial.twin(), 0, null, initial.twin().manhattan());
        while (!min.board.isGoal() && !minTwin.board.isGoal()) {
            for (Board b : min.board.neighbors()) {
                if (min.previous == null || !min.previous.board.equals(b)) {
                    pq.insert(new Node(b, min.moves + 1, min, b.manhattan() + min.moves + 1));
                }
            }
            for (Board b : minTwin.board.neighbors()) {
                if (minTwin.previous == null || !minTwin.previous.board.equals(b)) {
                    pqTwin.insert(new Node(b, minTwin.moves + 1, minTwin, b.manhattan() + minTwin.moves + 1));
                }
            }
            min = pq.delMin();
            minTwin = pqTwin.delMin();
        }
        if (min.board.isGoal()) {
            solvable = true;
            last = min;
        } else {
            solvable = false;
            last = minTwin;
            last.moves = -1;
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solvable;
    }

    // min number of moves to solve initial board
    public int moves() {
        return last.moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (!this.isSolvable()) return null;
        Stack<Board> boards = new Stack<Board>();
        Node prevNode = last;
        do {
            boards.push(prevNode.board);
            prevNode = prevNode.previous;
        } while (prevNode != null);
        return boards;
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
