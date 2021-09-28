package d1.singlelinkedlist;

import java.util.Objects;

public class Node<T> {
    private final T element;
    private Node<T> next;

    public Node(T element) {
        this.element = element;
    }

    public T get() {
        return element;
    }

    public void link(Node<T> next) {
        this.next = next;
    }

    public boolean hasNext() {
        return next != null;
    }

    public Node<T> next() {
        return next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(element, node.element) && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element, next);
    }
}
