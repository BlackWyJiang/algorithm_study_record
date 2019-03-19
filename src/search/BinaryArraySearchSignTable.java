package search;

// +----------------------------------------------------------------------
// | ProjectName: algorithm_study_record
// +----------------------------------------------------------------------
// | Date: 2019/3/18
// +----------------------------------------------------------------------
// | Time: 13:29
// +----------------------------------------------------------------------
// +----------------------------------------------------------------------

import java.util.Iterator;

/**
 * 基于有序数组实现的符号表
 * 基于数组的二分法查找方式
 * 所有读操作的时间复杂度为o(logN)  写的操作为o(N)
 *
 * @param <Key>
 * @param <Value>
 */
public class BinaryArraySearchSignTable<Key extends Comparable<Key>, Value> implements SortedSignTable<Key, Value> {


    private Key[] keys;

    private Value[] values;

    private int size;

    public BinaryArraySearchSignTable(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    @Override
    public Key min() {
        return keys[0];
    }

    @Override
    public Key max() {
        return keys[size - 1];
    }

    @Override
    public Key floor(Key key) {

        int rank = rank(key);
        if (key.compareTo(keys[rank]) == 0) return key;
        if (rank == 0) return null;
        return keys[rank - 1];
    }

    @Override
    public Key ceiling(Key key) {
        int rank = rank(key);
        if (key.compareTo(keys[rank]) == 0) return key;
        if (rank == size - 1) return null;
        return keys[rank ];

    }

    @Override
    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("key 不能为空");

        int low = 0, high = size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int i = key.compareTo(keys[mid]);
            if (i < 0)
                high = mid - 1;
            else if (i > 0) low = mid + 1;
            else return mid;
        }
        return low;

    }

    @Override
    public void delete(Key key) {

        int rank = rank(key);
        if (key.compareTo(keys[rank]) != 0) return;

        for (int i = rank; i < size -1  ; i++) {
            keys[i] = keys[i+1];
            values[i] = values[i+1];
        }
        keys[size -1 ] = null;
        values[size -1 ] = null;
        size--;

    }

    @Override
    public Key select(int i) {
        if (i < 0 || i >= size) throw new IllegalArgumentException("超过了最大含量");
        return keys[i];
    }

    @Override
    public Iterable<Key> keys(Key low, Key high) {
        return new Iterable<Key>() {
            @Override
            public Iterator<Key> iterator() {
                return new BSSTIterable(low, high);
            }
        };
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("key 不能为空");
        if (value == null) {
            delete(key);
            return;
        }

        int rank = rank(key);

        if (key.compareTo(keys[rank]) == 0) {
            values[rank] = value;
            return;
        }

        for (int i = size - 1; i >= rank; i--) {
            keys[i + 1] = keys[i];
            values[i + 1] = values[i];
        }
        keys[rank] = key;
        values[rank] = value;
        size++;
    }

    @Override
    public Value get(Key key) {

        if (key == null) throw new IllegalArgumentException("key 不能为空");

        int rank = rank(key);

        if (key.compareTo(keys[rank]) == 0) return values[rank];
        return null;

    }

    @Override
    public int size() {
        return size;
    }

    private class BSSTIterable implements Iterator<Key> {

        private int curse = -1;
        private int end;

        private BSSTIterable(Key low, Key high) {

            if (low == null || high == null) throw new IllegalArgumentException("key 不能为空");
            if (low.compareTo(high) > 0) return;


            int lowR = rank(low);
            if (low.compareTo(keys[lowR]) == 0) curse = lowR;
            else curse = lowR + 1;
            end = rank(high);

        }

        @Override
        public boolean hasNext() {
            return curse >= 0 && curse <= end;
        }

        @Override
        public Key next() {

            return hasNext() ? keys[curse++] : keys[curse -= end];
        }
    }
}
