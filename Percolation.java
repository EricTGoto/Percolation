// the percolating system will be modelled by an n by n grid of sites. 0 for blocked and 1 for open.
// A system "percolates" when there is a chain of sites starting from the top row to the bottom row
// This idea of percolation is a useful idea in many physical applications such as semiconductor manufacturing,
// water drainage, social media connections and so on

public class Percolation {
    private int[][] grid;

    public Percolation(int n) {
        grid = new int[n][n];
    }

    public void open(int row, int col) {

    }
}

