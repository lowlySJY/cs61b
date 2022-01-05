package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] FractionPerExper;
    private int Times;

    // perform T independent experiments on a N-by-N grid
    public void PercolationStats(int N, int T, PercolationFactory pf) {
        this.Times = T;
        FractionPerExper = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            FractionPerExper[i] = isPercolation(p, N);
        }
    }

    private int isPercolation(Percolation p, int N) {
        while (!p.percolates()) {
            int RandomX = StdRandom.uniform(N);
            int RandomY = StdRandom.uniform(N);
            p.open(RandomX, RandomY);
        }
        return p.numberOfOpenSites();
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(FractionPerExper);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(FractionPerExper);
    }

    // low endpoint of 95% confidence interval
    public double confidencelow() {
        return (this.mean() - 1.96 * this.stddev() / Math.sqrt(Times));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (this.mean() + 1.96 * this.stddev() / Math.sqrt(Times));
    }
}
