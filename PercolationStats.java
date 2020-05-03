import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private Percolation p;
    private double[] thresholds;
    private int trials;

    public PercolationStats(int n, int trials) {
        int count = 0;
        this.trials = trials;
        thresholds = new double[trials];
        do {
            p = new Percolation(n);

            int randRow = 0;
            int randCol = 0;

            while (!p.percolates()) {
                randRow = (int) (StdRandom.uniform() * n);
                randCol = (int) (StdRandom.uniform() * n);
                if (!p.isOpen(randRow, randCol)) p.open(randRow, randCol);
            }

            thresholds[count] = p.numberOfOpenSites() / (double) (n * n);
            count++;
        } while (count < trials);

    }

    public double mean() {
        double total = 0.0;
        for (int k = 0; k < thresholds.length; k++) {
            total += thresholds[k];
        }
        return total / trials;
    }

    public double stddev() {
        double mean = mean();
        double s = 0;
        for (int k = 0; k < thresholds.length; k++) {
            s += Math.pow((thresholds[k] - mean), 2);
        }
        s /= (trials - 1);
        return Math.sqrt(s);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);

        PercolationStats p = new PercolationStats(n, T);
        System.out.println("mean = " + p.mean());
        System.out.println("stddev = " + p.stddev());
        System.out.println("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");

    }
}
