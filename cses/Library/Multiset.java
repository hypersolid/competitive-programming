/**
 * HMultiset utility class. It's just like TreeSet, but allows duplicate elements *
 */
static class HMultiset<T> extends HashMap<T, Integer> {
    void add(T key) {
        Integer count = get(key);
        put(key, count == null ? 1 : count + 1);
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public Integer remove(Object key) {
        if (!containsKey(key)) return null;

        int count = get(key);
        if (count > 1) return put((T) key, count - 1);

        return super.remove(key);
    }
}

/**
 * Tree Multiset utility class *
 */
static class TMultiset<T extends Number> extends TreeMap<T, Integer> {
    private int size = 0;
    private T head = null, tail = null;

    void add(T value) {
        Integer count = get(value);
        size++;
        if (count == null) {
            put(value, 1);
            recalculateEdges();
        } else {
            put(value, count + 1);
        }
    }

    private void recalculateEdges() {
        try {
            head = firstKey();
        } catch (NoSuchElementException e) {
            head = null;
        }
        try {
            tail = lastKey();
        } catch (NoSuchElementException e) {
            tail = null;
        }
    }

    @SuppressWarnings(value = "unchecked")
    @Override
    public Integer remove(Object key) {
        if (!containsKey(key)) {
            return null;
        }

        size--;

        Integer value = get(key);
        if (value > 1) {
            return put((T) key, value - 1);
        }

        Integer result = super.remove(key);
        recalculateEdges();
        return result;
    }

    @java.lang.Override
    public int size() {
        return size;
    }
}
