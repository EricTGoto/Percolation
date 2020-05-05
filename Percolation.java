import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// the percolating system will be modelled by an n by n grid of sites. 0 for blocked and 1 for open.
// A full site is an open site that can be connected to the top row via a chain of open sites
// A system "percolates" when there is a chain of sites starting from the top row to the bottom row, in other words
// a full site in the bottom row
// This idea of percolation is a useful idea in many physical applications such as semiconductor manufacturing,
// water drainage, social media connections and so on
// The convention being used is that the upper left corner of the grid is (1,1) and not (0,0)

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF UF;
    private int openSites;

    public Percolation(int n) {
        if (n < 1) throw new IllegalArgumentException("Please enter a value bigger than 0");

        // initialize the UF data structure and create the virtual bottom and top sites
        UF = new WeightedQuickUnionUF(n * n);
        for (int i = 0; i < n - 1; i++)
            UF.union(i, i + 1);
        for (int j = n * n - n; j < n * n - 1; j++)
            UF.union(j, j + 1);

        // I use n+1 because of the convention to have the upper left corner as (1,1) instead of (0,0)
        grid = new boolean[n + 1][n + 1];

        openSites = 0;
    }

    // open a site and also create the connections
    public void open(int row, int col) {
        if (row < 1 || row > grid.length - 1 || col < 1 || col > grid.length - 1)
            throw new IllegalArgumentException("Please enter a valid pair of indices");

        // open a site by changing the value in the grid to a 1
        if (!grid[row][col]) {
            grid[row][col] = true;
            openSites++;
        }

        // create connections by using union
        if (row + 1 < grid.length && grid[row + 1][col]) {
            UF.union(unionIndex(row, col), unionIndex(row, col) + grid.length - 1);
        }
        if (row - 1 >= 1 && grid[row - 1][col]) {
            UF.union(unionIndex(row, col), unionIndex(row, col) - grid.length + 1);
        }
        if (col + 1 < grid.length && grid[row][col + 1]) {
            UF.union(unionIndex(row, col), unionIndex(row, col) + 1);
        }
        if (col - 1 >= 1 && grid[row][col - 1]) {
            UF.union(unionIndex(row, col), unionIndex(row, col) - 1);
        }
    }

    // check if a site is open
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > grid.length - 1 || col < 1 || col > grid.length - 1)
            throw new IllegalArgumentException("Please enter a valid pair of indices");
        return grid[row][col];
    }

    // a site is full if it is connected to the top row and it is open
    public boolean isFull(int row, int col) {
        if (row < 1 || row > grid.length - 1 || col < 1 || col > grid.length - 1)
            throw new IllegalArgumentException("Please enter a valid pair of indices");
        return UF.find(unionIndex(row, col)) == UF.find(0) && grid[row][col];
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // returns true if the system percolates, false if it doesn't
    public boolean percolates() {
        if (grid.length - 1 == 1) return isFull(1, 1);
        return UF.find((grid.length - 1) * (grid.length - 1) - 1) == UF.find(0);
    }

    private int unionIndex(int row, int col) {
        return (row - 1) * (grid.length - 1) + col - 1;
    }
}

