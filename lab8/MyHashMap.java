import java.security.Key;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V>{
    // Hash tables size
    private int tableSize;
    // Number of key-value pairs
    private int size;
    // threshold value for resizing
    private double loadFactor;
    // array of linked-list symbol tables
    private ArrayList<K>[] keyArray;
    private ArrayList<V>[] valArray;
    private Set<K> KeySet = new HashSet<>();

    /**
     * Initializes an empty symbol table.
     * InitialSize = 16, loadFactor = 0.75 by default.
     */
    public MyHashMap() {
        this(16, 0.75);
    }
    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }
    public MyHashMap(int initialSize, double loadFactor) {
        if (initialSize < 0 || loadFactor <= 0) {
            throw new IllegalArgumentException("argument for MyHashMap is illegal");
        }
        this.tableSize = initialSize;
        this.loadFactor = loadFactor;
        this.size = 0;
        keyArray = new ArrayList[initialSize];
        valArray = new ArrayList[initialSize];
    }

    private void grow() {
        MyHashMap<K, V> temp = new MyHashMap<K, V>(tableSize * 2, loadFactor);
        for (int i = 0; i < tableSize; i++) {
            if (keyArray[i] != null) {
                for (K key : keyArray[i]) {
                    temp.put(key, get(key));
                }
            }
        }
        this.tableSize = temp.tableSize;
        this.keyArray = temp.keyArray;
        this.valArray = temp.valArray;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    @Override
    public void clear() {
        keyArray = new ArrayList[this.tableSize];
        valArray = new ArrayList[this.tableSize];
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to containskey() is null");
        }
        int i = hash(key);
        if (keyArray[i] == null) {
            return false;
        } else {
            return keyArray[i].contains(key);
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (!containsKey(key)) {
            return null;
        } else {
            int hashcode = hash(key);
            int index = keyArray[hashcode].indexOf(key);
            return valArray[hashcode].get(index);
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument for put() is null");
        }
        if (size >= loadFactor * tableSize) {
            grow();
        }
        int hashcode = hash(key);
        if (containsKey(key)) {
            int index = keyArray[hashcode].indexOf(key);
            valArray[hashcode].set(index, value);
        } else {
            if (keyArray[hashcode] == null) {
                keyArray[hashcode] = new ArrayList<K>();
                valArray[hashcode] = new ArrayList<V>();
                keyArray[hashcode].add(key);
                valArray[hashcode].add(value);
                size++;
            } else {
                keyArray[hashcode].add(key);
                valArray[hashcode].add(value);
                size++;
            }
        }
    }

    @Override
    public Set keySet() {
        for (int i = 0; i < tableSize; i++) {
            if (keyArray[i] != null) {
                for (int j = 0; j < keyArray[i].size(); j++) {
                    KeySet.add(keyArray[i].get(j));
                }
            }
        }
        return KeySet;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument for remove() is null");
        }
        if (!containsKey(key)) {
            return null;
        } else {
            V val = get(key);
            int hashcode = hash(key);
            int index = keyArray[hashcode].indexOf(key);
            keyArray[hashcode].remove(index);
            valArray[hashcode].remove(index);
            return val;
        }
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument for remove() is null");
        }
        if (get(key) == value) {
            return remove(key);
        } else {
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new hashMapIterator();
    }

    private class hashMapIterator implements Iterator<K>{
        @Override
        public boolean hasNext() {
            return KeySet.size() > 0;
        }

        @Override
        public K next() {
            int i = 0;
            K[] keyList =(K[]) KeySet.toArray();
            K nextKey = keyList[i];
            i++;
            return nextKey;
        }
    }
}
