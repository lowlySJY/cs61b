public interface Deque<T> {
    void addFirst(T x);

    void addLast(T x);

    T get(int i);

    int size();

    boolean isEmpty();

    T removeFirst();

    T removeLast();

    void printDeque();
}
