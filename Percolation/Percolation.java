import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private final int n;
    private int numberOfOpenSites;
    private final WeightedQuickUnionUF uf;
    private final int top;
    private final int bottom;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n " + n + " is not greater than 0");
        }
        this.n = n;
        this.top = n * n;
        this.bottom = n * n + 1;
        grid = new boolean[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    private int mapIndex(int row, int col) {
        return n * (row - 1) + col - 1;
    }

    private void validate(int p) {
        if (p < 1 || p > n) {
            throw new IllegalArgumentException("index " + p + " is not between 1 and " + n);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (!isOpen(row, col)) {
            grid[row - 1][col - 1] = true;
            numberOfOpenSites++;
            if (row == 1) {
                uf.union(top, mapIndex(row, col));
            }
            if (row == n) {
                uf.union(bottom, mapIndex(row, col));
            }
        }

        // union adjacent sites if open and not already connected
        int p = mapIndex(row, col);
        int q = mapIndex(row, col + 1);
        if (col + 1 <= n && isOpen(row, col + 1)) {
            uf.union(p, q);
        }

        q = mapIndex(row, col - 1);
        if (col - 1 >= 1 && isOpen(row, col - 1)) {
            uf.union(p, q);
        }

        q = mapIndex(row + 1, col);
        if (row + 1 <= n && isOpen(row + 1, col)) {
            uf.union(p, q);
        }

        q = mapIndex(row - 1, col);
        if (row - 1 >= 1 && isOpen(row - 1, col)) {
            uf.union(p, q);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row);
        validate(col);
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row);
        validate(col);
        return uf.find(top) == uf.find(mapIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.find(top) == uf.find(bottom);
    }
}
