package es.datastructur.synthesizer;

import java.util.Iterator;
import java.util.Objects;

public interface BoundedQueue<T> extends Iterable<T>{
    public boolean equals(Object o);

    public Iterator<T> iterator();
    /**
     * return the size of the buffer
     */
    public int capacity();

    /**
     * return number of items currently in the buffer
     */
    public int fillCount();

    /**
     * add item X to the end
     */
    public void enqueue(T x);

    /**
     * delete and return item from the front
     */
    public T dequeue();

    /**
     * return (but don't delete) item from the front
     */
    public T peek();

    /**
     * if the buffer is empty return ture
     */
    default boolean isEmpty() {
        return (fillCount() == 0);
    }

    /**
     * if the buffer is full return true
     */
    default boolean isFull() {
        return (fillCount() == capacity());
    }
}
