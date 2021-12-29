package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.ArrayDeque;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void BuildTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        assertEquals(10, arb.capacity());
    }

    @Test
    public void DeEnqueTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(3);
        // Test the Enque()
        arb.enqueue(1);
        assertFalse(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(2);
        arb.enqueue(3);
        assertTrue(arb.isFull());
//        arb.enqueue(4);

        // Test the dequeue()
        arb.dequeue();
        assertFalse(arb.isFull());
        assertFalse(arb.isEmpty());
        arb.dequeue();
        assertEquals(1, arb.fillCount());
        assertEquals(3, arb.dequeue());
        assertTrue(arb.isEmpty());
//        arb.dequeue();
    }

    @Test
    public void peekTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(3);
        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals(1, arb.peek());
        assertEquals(2, arb.fillCount());
    }

/*    // Test for arraydeque(dequeARB) which need set to public in ArrayRingBuffer.java
    @Test
    public void fullsizeTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(3);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.enqueue(4);
        arb.dequeue();
        arb.enqueue(5);
        assertEquals(3, arb.dequeARB.size());
    }*/

    @Test
    public void equalTest() {
        int num = 10;
        ArrayRingBuffer arb = new ArrayRingBuffer(num);
        ArrayRingBuffer arbCompare1 = new ArrayRingBuffer(num);
        ArrayDeque arbCompare2 = new ArrayDeque();
        for (int i = 0; i < num; i++) {
            arb.enqueue(i);
            arbCompare1.enqueue(i);
        }
        assertFalse(arb.equals(arbCompare2));
        assertTrue(arbCompare1.equals(arb));
        arbCompare1.dequeue();
        assertFalse(arb.equals(arbCompare1));
        arbCompare1.enqueue(0);
        assertFalse(arb.equals(arbCompare1));
    }
}
