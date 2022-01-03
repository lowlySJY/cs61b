import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class BubbleGrid {
    private int[][] grid;

    public static final int[][] Directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int rows = grid.length;
        int cols = grid[0].length;

        // copy the grid
        int[][] gridCopy = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gridCopy[i][j] = grid[i][j];
            }
        }

        // set the position of Bubbles hit by darts to 0
        for (int[] dart : darts) {
            gridCopy[dart[0]][dart[1]] = 0;
        }

        // Build Disjoint set with a special node (index = size) to connect roof
        UnionFind unionFind = new UnionFind(rows*cols + 1);
        int size = rows * cols;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int index = i * cols + j;
                // Connect all stable bubbles to special node.
                if (gridCopy[i][j] == 1) {
                    // top bubbles
                    if (i == 0) {
                        unionFind.union(index, size);
                    }
                    // the upper is stable
                    if (i > 0 && gridCopy[i-1][j] == 1) {
                        unionFind.union(index, index - cols);
                    }
                    // the left is stable
                    if (j > 0 && gridCopy[i][j-1] == 1) {
                        unionFind.union(index, index - 1);
                    }
                }
            }
        }

        int cnt = darts.length - 1;
        int[] fallNumArray = new int[cnt + 1];
        while (cnt >= 0) {
            int x = darts[cnt][0];
            int y = darts[cnt][1];
            int preSize = unionFind.sizeOf(size);
            if (grid[x][y] == 0) {
                cnt --;
            } else {
                int index = x *cols + y;
                if (x == 0) {
                    unionFind.union(index, size);
                }
                for (int[] dir : Directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if ((newX >= 0 && newY >=0 && newX < rows && newY < cols) && (gridCopy[newX][newY] == 1)) {
                        unionFind.union(index, newX * cols + newY);
                    }
                }
                int curSize = unionFind.sizeOf(size);

                fallNumArray[cnt] = Math.max(0, curSize - preSize - 1);
                gridCopy[x][y] = 1;
                cnt--;
            }
        }
        return fallNumArray;
    }
}
