package d2;

import java.util.Objects;

public class Node<T extends Comparable<T>> {
    private final T value;
    private Node<T> left;
    private Node<T> right;

    public Node(T value) {
        this.value = Objects.requireNonNull(value);
    }

    public T getValue() {
        return value;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public int compareTo(T value) {
        return this.value.compareTo(value);
    }

    public int compareTo(Node<T> node) {
        return value.compareTo(node.value);
    }

}
