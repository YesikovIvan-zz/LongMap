package com.yesikov.longhashmap;


import java.util.Arrays;

public class LongMap<V> implements ILongMap<V> {

    protected static final int DEFAULT_CAPACITY = 16;
    private Entry<V>[] table;
    private int capacity;
    private int limit;
    private int size;

    public final static class Entry<V> {
        public final long key;
        public V value;
        Entry<V> next;

        Entry(long key, V value, Entry<V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public LongMap() {
        this(DEFAULT_CAPACITY);
    }

    public LongMap(int capacity) {
        this.capacity = capacity;
        this.limit = capacity * 4 / 3;
        this.table = new Entry[capacity];
    }

    public V put(long key, V value) {
        final int index = getIndex(key, capacity);
        final Entry<V> entryOriginal = table[index];
        for (Entry<V> entry = entryOriginal; entry != null; entry = entry.next) {
            if (entry.key == key) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        table[index] = new Entry<>(key, value, entryOriginal);
        size++;
        if (size > limit) {
            setCapacity(2 * capacity);
        }
        return null;
    }

    public V get(long key) {
        final int index = getIndex(key, capacity);
        for (Entry<V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key == key) {
                return entry.value;
            }
        }
        return null;
    }

    public V remove(long key) {
        int index = getIndex(key, capacity);
        Entry<V> previous = null;
        Entry<V> entry = table[index];
        while (entry != null) {
            Entry<V> next = entry.next;
            if (entry.key == key) {
                if (previous == null) {
                    table[index] = next;
                } else {
                    previous.next = next;
                }
                size--;
                return entry.value;
            }
            previous = entry;
            entry = next;
        }
        return null;
    }

    public boolean isEmpty() {
        return table.length == 0;
    }

    public boolean containsKey(long key) {
        final int index = getIndex(key, capacity);
        for (Entry<V> entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key == key) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (int i = 0; i < table.length; i++) {
            if(this.table[i] == null){
                continue;
            }
            if (value.equals(this.table[i].value)) {
                return true;
            }
        }
        return false;
    }

    public long[] keys() {
        long[] values = new long[size];
        int index = 0;
        for (Entry entry : table) {
            while (entry != null) {
                values[index++] = entry.key;
                entry = entry.next;
            }
        }
        return values;
    }

    public V[] values() {
        V[] values = (V[]) new Object[size];
        int index = 0;
        for (Entry entry : table) {
            while (entry != null) {
                values[index++] = (V)entry.value;
                entry = entry.next;
            }
        }
        return values;
    }

    public long size() {
        return size;
    }

    public void clear() {
        size = 0;
        Arrays.fill(table, null);
    }

    public void setCapacity(int newCapacity) {
        Entry<V>[] newTable = new Entry[newCapacity];
        int length = table.length;
        for (int i = 0; i < length; i++) {
            Entry<V> entry = table[i];
            while (entry != null) {
                long key = entry.key;
                int index = getIndex(key, newCapacity);
                Entry<V> originalNext = entry.next;
                entry.next = newTable[index];
                newTable[index] = entry;
                entry = originalNext;
            }
        }
        table = newTable;
        capacity = newCapacity;
        limit = newCapacity * 4 / 3;
    }

    private int getIndex(long key, int capacity){
        return ((((int) (key >>> 32)) ^ ((int) (key))) & 0x7fffffff) % capacity;
    }

}