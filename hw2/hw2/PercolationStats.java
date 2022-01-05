package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] FractionPerExper;
    private int Times;

    // perform T independent experiments on a N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N or T should be bigger than 0!");
        }
        this.Times = T;
        FractionPerExper = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            FractionPerExper[i] = (double) isPercolation(p, N) / (double) (N*N);
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
    public double confidenceLow() {
        return (this.mean() - 1.96 * this.stddev() / Math.sqrt(Times));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return (this.mean() + 1.96 * this.stddev() / Math.sqrt(Times));
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats system = new PercolationStats(20,1000, pf);
        System.out.println("The mean probability is: "+ system.mean());
        System.out.println("The standard deviation is: "+ system.stddev());
        System.out.println("The confidence interval is : ( " + system.confidenceLow() + " , " + system.confidenceHigh() + " )");
    }
}
