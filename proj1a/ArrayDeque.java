public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private T[] items = (T[]) new Object[2];

    public ArrayDeque() {
        nextFirst = items.length - 1;
        nextLast = 0;
        size = 0;
    }

    private void downsize() {
        if (4 * size < items.length && items.length > 8) {
            T[] a = (T[]) new Object[items.length / 2];
            copyresize(items, a);
        }

    }

    private void copyresize(T[] i, T[] a) {
        System.arraycopy(i, nextFirst + 1, a, 0, i.length - nextFirst - 1);
        System.arraycopy(i, 0, a, i.length - nextFirst - 1, nextLast);
        items = a;
        nextLast = i.length;
        nextFirst = items.length - 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        copyresize(items, a);
    }

    public void addFirst(T x) {
        items[nextFirst] = x;
        nextFirst--;
        size++;
        if (size == items.length) {
            resize(2 * items.length);
        }
    }

    public void addLast(T x) {
        items[nextLast] = x;
        nextLast++;
        size++;
        if (size == items.length) {
            resize(2 * items.length);
        }

    }

    private T getLast() {
        return items[nextLast - 1];
    }

    public T get(int i) {
        if (i >= items.length) {
            return null;
        }

        if (i <= items.length - nextFirst - 2) {
            return items[nextFirst + i + 1];
        }
        return items[i - (items.length - nextFirst - 1)];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T x;
        if (nextFirst == items.length - 1) {
            x = items[0];
            T[] a = (T[]) new Object[items.length];
            System.arraycopy(items, 1, a, 0, size - 1);
            items = a;
            size--;
            nextLast--;
            downsize();
            return x;
        } else {
            x = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            size--;
            nextFirst++;
            downsize();
            return x;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (nextLast > 0) {
            T x = getLast();
            items[nextLast - 1] = null;
            size--;
            nextLast--;
            downsize();
            return x;
        } else {
            T x = items[items.length - 1];
            T[] a = (T[]) new Object[items.length];
            System.arraycopy(items, 0, a, 1, items.length - 1);
            items = a;
            nextFirst++;
            size--;
            downsize();
            return x;
        }
    }

    public void printDeque() {
        int begin = nextFirst + 1;
        int end = 0;
        while (begin < items.length) {
            System.out.print(items[begin] + " ");
            begin++;
        }
        while (end < nextLast) {
            System.out.print(items[end] + " ");
            end++;
        }
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> a = new ArrayDeque();
        a.addLast(0);
        a.removeFirst();
        a.removeFirst();
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(4);
        System.out.println(a.isEmpty());
        a.addFirst(5);
//        a.addLast(9);
//        a.addLast(8);
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        a.removeFirst();
        System.out.println(a.isEmpty());
        a.addFirst(4);
        System.out.println(a.isEmpty());
        a.addFirst(3);
//        a.printDeque();
//        a.removeFirst();

        a.removeLast();
        a.removeFirst();
    }

}
