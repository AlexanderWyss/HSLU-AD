package d2;

import java.util.Objects;

public class Node<K extends Comparable<K>, V> {
    private final K key;
    private V value;
    private Node<K, V> left;
    private Node<K, V> right;

    public Node(K key, V value) {
        this.key = Objects.requireNonNull(key);
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public int compareTo(K key) {
        return this.key.compareTo(key);
    }

    public int compareTo(Node<K, V> node) {
        return key.compareTo(node.key);
    }
}
