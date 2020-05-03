// the percolating system will be modelled by an n by n grid of sites. 0 for blocked and 1 for open.
// A system "percolates" when there is a chain of sites starting from the top row to the bottom row
// This idea of percolation is a useful idea in many physical applications such as semiconductor manufacturing,
// water drainage, social media connections and so on

public class Percolation {
    private int[][] grid;
    private int openSites;

    public Percolation(int n) {
        if (n > 1) throw new IllegalArgumentException("Please enter a value bigger than 0");
        grid = new int[n][n];
        openSites = 0;
    }

    // open a site
    public void open(int row, int col) {
        if (row < 0 || row > grid.length - 1 || col < 0 || col > grid.length - 1)
            throw new IllegalArgumentException("Please enter a valid pair of indices");

        if (grid[row][col] == 0) {
            grid[row][col] = 1;
            openSites++;
        }
    }

    // check if a site is open
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > grid.length - 1 || col < 0 || col > grid.length - 1)
            throw new IllegalArgumentException("Please enter a valid pair of indices");
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // returns true if the system percolates, false if it doesn't
    public boolean percolates() {

    }
}

