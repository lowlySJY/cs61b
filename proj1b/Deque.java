public interface Deque<T> {
    public void addFirst(T x);

    public void addLast(T x);

    public T get(int i);

    public int size();

    public boolean isEmpty();

    public T removeFirst();

    public T removeLast();

    public void printDeque();
}