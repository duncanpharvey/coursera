import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int[][] board;
    private int emptyRow;
    private int emptyCol;
    private final int n;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        n = tiles.length;
        board = new int[n][n];
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int val = tiles[row][col];
                if (val == 0) {
                    emptyRow = row;
                    emptyCol = col;
                }
                board[row][col] = val;
            }
        }
    }

    private int mapIndex(int row, int col) {
        return n * row + col + 1;
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                s.append(String.format("%2d ", board[row][col]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int val = board[row][col];
                if (val != 0 && mapIndex(row, col) != val) count++;
            }
        }
        return count;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int val = board[row][col];
                if (val == 0) continue;
                sum += Math.abs(row - (val - 1) / n) + Math.abs(col - (val - 1) % n);
            }
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return this.manhattan() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) return false;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (this.board[row][col] != that.board[row][col]) return false;
            }
        }
        return true;
    }

    private boolean isValid(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            return false;
        }
        return true;
    }

    private void addNeighbor(Stack<Board> neighbors, int row, int col) {
        if (!isValid(row, col)) {
            return;
        }
        int val = board[row][col];
        board[emptyRow][emptyCol] = val;
        board[row][col] = 0;
        neighbors.push(new Board(board));
        board[emptyRow][emptyCol] = 0;
        board[row][col] = val;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> neighbors = new Stack<Board>();
        addNeighbor(neighbors, emptyRow, emptyCol - 1);
        addNeighbor(neighbors, emptyRow, emptyCol + 1);
        addNeighbor(neighbors, emptyRow - 1, emptyCol);
        addNeighbor(neighbors, emptyRow + 1, emptyCol);
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int row = 0;
        if (emptyRow == 0) {
            row = 1;
        }
        int val0 = board[row][0];
        int val1 = board[row][1];
        board[row][0] = val1;
        board[row][1] = val0;
        Board twin = new Board(board);
        board[row][0] = val0;
        board[row][1] = val1;
        return twin;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] arr = new int[4][4];
        arr[0][0] = 1;
        arr[0][1] = 2;
        arr[0][2] = 3;
        arr[0][3] = 4;
        arr[1][0] = 5;
        arr[1][1] = 6;
        arr[1][2] = 7;
        arr[1][3] = 8;
        arr[2][0] = 9;
        arr[2][1] = 10;
        arr[2][2] = 11;
        arr[2][3] = 12;
        arr[3][0] = 13;
        arr[3][1] = 14;
        arr[3][2] = 15;
        arr[3][3] = 0;

        Board board = new Board(arr);
        int hamming = board.hamming();
        int man = board.manhattan();

        StdOut.println(board.toString());
        StdOut.println();
        StdOut.println(board.twin().toString());
        StdOut.println("equals twin: " + board.equals(board.twin()));
        StdOut.println("equals itself: " + board.equals(board));

        StdOut.println("hamming: " + hamming);
        StdOut.println("manhattan: " + man);

        board.neighbors();
        for (Board b : board.neighbors()) {
            StdOut.println(b.toString());
        }
    }

}
