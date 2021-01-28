public class ArrayDeque<I> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private I[] items = (I[]) new Object[3];

    public ArrayDeque() {
        nextFirst = items.length - 1;
        nextLast = 0;
        size = 0;
    }

    public void downsize() {
        if (4 * size < items.length) {
            I[] a =(I[]) new Object[items.length / 2];
            copyresize(items, a);
        }

    }

    public void copyresize(I[] i, I[] a) {
        System.arraycopy(i, nextFirst + 1, a, 0, i.length - nextFirst - 1);
        System.arraycopy(i, 0, a, i.length - nextFirst - 1, nextLast);
        items = a;
        nextLast = i.length;
        nextFirst = items.length - 1;
    }

    private void resize(int capacity) {
        I[] a =(I[]) new Object[capacity];
        copyresize(items, a);
    }

    public void addFirst(I x) {
        if (size == items.length) {
            resize(2 * items.length);
        }

        items[nextFirst] = x;
        nextFirst--;
        size++;
    }

    public void addLast(I x) {
        if (size == items.length) {
            resize(2 * size);
        }

        items[nextLast] = x;
        nextLast++;
        size++;
    }

    public I getLast() {
        return items[nextLast - 1];
    }

    public I get(int i) {
        if (i <= items.length - nextFirst - 2) {
            return items[nextFirst + i + 1];
        }
        return items[i - (items.length - nextFirst - 1) - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public I removeFirst() {
        I x;
        if (nextFirst == items.length - 1) {
            x = items[0];
            I[] a =(I[]) new Object[items.length];
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

    public I removeLast() {
        I x = getLast();
        items[nextLast - 1] = null;
        size--;
        nextLast--;
        downsize();
        return x;
    }

    public void printDeque() {
        int begin = nextFirst + 1;
        int end = 0;
        while (begin < items.length) {
            System.out.print(items[begin] + " ");
            begin++;
        }
        while(end < nextLast) {
            System.out.print(items[end] + " ");
            end++;
        }

    }

    public static void main(String[] args) {
        ArrayDeque<Integer> AD = new ArrayDeque();
        AD.addFirst(5);
        AD.addLast(9);
        AD.addLast(8);
        AD.addFirst(4);
        AD.addFirst(3);
        AD.printDeque();
        AD.removeFirst();
        AD.removeFirst();
        AD.removeLast();
        AD.removeFirst();
    }
}

