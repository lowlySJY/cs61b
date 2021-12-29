package es.datastructur.synthesizer;
import org.junit.Test;
import java.util.*;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* Array list for circular deque.*/
    private List<T> dequeARB;
    /* The size of the buffer.*/
    private int capacityofARB;

    /**
     * return the size of the buffer
     */
    @Override
    public int capacity(){
        return capacityofARB;
    }

    /**
     * return number of items currently in the buffer
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        dequeARB = new ArrayList<T>();
        capacityofARB = capacity;
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (this.isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        } else if(last == capacityofARB -1 && dequeARB.size() < capacityofARB) {
            dequeARB.add(last, x);
            last = 0;
            fillCount += 1;
        } else if(last == capacityofARB -1 && dequeARB.size() == capacityofARB) {
            dequeARB.set(last, x);
            last = 0;
            fillCount += 1;
        } else if(dequeARB.size() == capacityofARB){
            dequeARB.set(last,x);
            last += 1;
            fillCount += 1;
        } else {
            dequeARB.add(last, x);
            last += 1;
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else if (first == capacityofARB -1) {
            T dequeItem = dequeARB.get(first);
            dequeARB.set(first, null);
            first = 0;
            fillCount -= 1;
            return dequeItem;
        } else {
            T dequeItem = dequeARB.get(first);
            dequeARB.set(first, null);
            first += 1;
            fillCount -= 1;
            return dequeItem;
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (this.isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        } else {
            return (dequeARB.get(first));
        }
    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }

    private class ARBIterator implements Iterator<T> {
        private int wizPos;
        public ARBIterator() { wizPos = 0; }
        public boolean hasNext() { return wizPos < dequeARB.size(); }
        public T next() {
            T returnItem = dequeARB.get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ArrayRingBuffer) {
            ArrayRingBuffer ObjinArrayRingBuffer =(ArrayRingBuffer) o;
            if (this.fillCount() != ObjinArrayRingBuffer.fillCount()) {
                return false;
            } else {
                ArrayRingBuffer thisCopy = this;
                for (int i =0; i < thisCopy.fillCount(); i++) {
                    if (thisCopy.dequeue() != ObjinArrayRingBuffer.dequeue()) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return false;
        }
    }
}

