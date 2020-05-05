import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] thresholds;
    private final int trials;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Invalid arguments");

        int count = 0;
        this.trials = trials;
        thresholds = new double[trials];

        do {
            Percolation p = new Percolation(n);

            int randRow = 0;
            int randCol = 0;

            while (!p.percolates()) {
                randRow = (int) (StdRandom.uniform() * n + 1);
                randCol = (int) (StdRandom.uniform() * n + 1);
                if (!p.isOpen(randRow, randCol)) p.open(randRow, randCol);
            }

            thresholds[count] = p.numberOfOpenSites() / (double) (n * n);
            count++;
        } while (count < trials);

    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    private double interval() {
        return 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats p = new PercolationStats(n, T);
        double mean = p.mean();
        double stddev = p.stddev();
        double interval = 1.96 * stddev / Math.sqrt(T);
        double lo = mean - interval;
        double hi = mean + interval;

        System.out.println("mean = " + mean);
        System.out.println("stddev = " + stddev);
        System.out.println("95% confidence interval = [" + lo + ", " + hi + "]");

    }
}
