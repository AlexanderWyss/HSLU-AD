package d2;

import java.util.function.BiConsumer;

public interface Tree<K extends Comparable<K>, V> {
    /**
     * Add or set value for corresponding key.
     *
     * @param key   not null
     * @param value value to be associated with key
     */
    void add(K key, V value);

    /**
     * Get Value for corresponding key.
     *
     * @param key not null
     * @return value associated with key or null if not exists
     */
    V get(K key);

    /**
     * Traverses tree in order and calls the consumer for every node
     *
     * @param consumer BiConsumer with params key, value
     */
    void traverseInorder(BiConsumer<K, V> consumer);
}
