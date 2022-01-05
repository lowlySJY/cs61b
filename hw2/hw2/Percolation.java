package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int rowNum;
    private int colNum;
    private int size;
    private WeightedQuickUnionUF grid;
    private int NumofOpensites;
    private int[] openRecorder;
    private static final int[][] Directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    // transfer 2D into 1D
    private int xyTo1D(int r, int c) {
        return (r * colNum + c);
    }

    // create M-by-N gird, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N should be geater than 0!");
        }
        this.colNum = N;
        this.rowNum = N;
        this.size = colNum * rowNum;
        // there are two special node knowed as virtual top site (index=size) and virtual bottom site (index=size+1)
        this.grid = new WeightedQuickUnionUF(size + 2);
        this.NumofOpensites = 0;
        this.openRecorder = new int[size];
    }

    // if the argument is outside
    private boolean outsideJudge(int row, int col) {
        return (rowNum > row && row >= 0 && col >= 0 && col < colNum);
    }

    // open the size (row, col) if it is not open already
    public void open(int row, int col) {
        if (!outsideJudge(row, col)) {
            throw new IndexOutOfBoundsException("The input argument is outside!");
        }
        // nothing happen if it has already opened.
        if (isOpen(row, col)) {return;}
        int index = xyTo1D(row, col);
        // if the top connect to virtual top site
        if (row == 0) {
            grid.union(index, size);
            unionSurround(row, col);
        } else if (row == rowNum - 1) {
            grid.union(index, size + 1);
            unionSurround(row, col);
        } else {
            unionSurround(row, col);
        }
    }

    // union surround opened site
    private void unionSurround(int row , int col) {
        int index = xyTo1D(row, col);
        for (int[] dir : Directions) {
            int newX = row + dir[0];
            int newY = col + dir[1];
            int newIndex = xyTo1D(newX, newY);
            if (outsideJudge(newX, newY) && openRecorder[newIndex] == 1) {
                grid.union(index, newIndex);
            }
        }
        openRecorder[index] = 1;
        NumofOpensites++;
    }

    // is the site (row, col) open?
    public boolean isOpen (int row, int col) {
        int index = xyTo1D(row, col);
        return (openRecorder[index] == 1);
    }

    // is the site (row, col) full?
    public boolean isFull (int row, int col) {
        int index = xyTo1D(row, col);
        return grid.connected(index, size);
    }

    // number of open sites
    public int numberofOpenSites() {
        return NumofOpensites;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(size, size +1);
    }

/*
    // use for unit testing (not required, but keep this here for the auto-grader)
    public static void main(String[] args) { }
*/
}
