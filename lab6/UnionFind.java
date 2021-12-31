public class UnionFind {

    // TODO - Add instance variables?
    private int[] parent;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        // TODO
        parent =  new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        // TODO
        if (vertex < 0 || vertex >= parent.length) {
            throw new IllegalArgumentException("vertex is not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        // TODO
        int r = find(v1);
        return -parent[r];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        // TODO
        validate(v1);
        return parent[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        return (find(v1)==find(v2));
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        // TODO
        validate(v1);
        validate(v2);
        if (connected(v1,v2)) {
            return;
        } else if (sizeOf(v1) <= sizeOf(v2)) {
            parent[find(v2)] -= sizeOf(v1);
            parent[find(v1)] = find(v2);
            parent[v1] = find(v2);
            if (parent[v2] >= 0 ) {
                parent[v2] = parent[v1];
            }
        } else {
            parent[find(v1)] -= sizeOf(v2);
            parent[find(v2)] = find(v1);
            parent[v2] = find(v1);
            if (parent[v1] >= 0) {
                parent[v1] = find(v1);
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        // TODO
        validate(vertex);
        int r = vertex;
        while (parent[r] >= 0 ) {
            r = parent[r];
        }
        return r;
    }

}
