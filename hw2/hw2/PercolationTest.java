package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationTest {
    @Test
    public void BasicTest() {
        Percolation p = new Percolation(5);
        p.open(3, 4);
        p.open(2, 4);
        assertTrue(p.isOpen(2,4));
        assertFalse(p.isOpen(4, 4));
        assertFalse(p.isFull(3, 4));
        p.open(2, 2);
        assertFalse(p.isFull(2, 2));
        assertFalse(p.percolates());
        p.open(2, 3);
        p.open(0, 2);
        p.open(1, 2);
        assertTrue(p.isFull(3, 4));
        assertTrue(p.isFull(2, 2));
        assertEquals(6, p.numberofOpenSites());
        p.open(4, 4);
        assertTrue(p.percolates());
    }

    @Test
    public void MoreTest() {
        Percolation p1 = new Percolation(4);
        p1.open(1, 1);
        p1.open(1 ,2);
        p1.open(2, 2);
        assertFalse(p1.percolates());
        p1.open(3, 3);
        assertFalse(p1.percolates());
        p1.open(3, 2);
        assertFalse(p1.isFull(2,2));
        p1.open(0, 1);
        assertTrue(p1.percolates());
        assertEquals(6, p1.numberofOpenSites());
    }
}
