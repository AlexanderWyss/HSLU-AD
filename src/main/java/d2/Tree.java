package d2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Binary Tree with unique values
 *
 * @param <T> Type of containing values
 */
public class Tree<T extends Comparable<T>> {
    private Node<T> root;

    public void insert(T value) {
        if (root == null) {
            root = new Node<>(value);
        } else {
            Node<T> closestNode = findClosestNode(root, value);
            setChildForComparisonResult(closestNode, value);
        }
    }

    private Node<T> findClosestNode(Node<T> node, T value) {
        var childNode = getChildForComparisonResult(node, value);
        if (childNode != null) {
            return findClosestNode(childNode, value);
        }
        return node;
    }

    private Node<T> getChildForComparisonResult(Node<T> node, T value) {
        int comparisonResult = node.compareTo(value);
        if (comparisonResult > 0) {
            return node.getLeft();
        }
        if (comparisonResult < 0) {
            return node.getRight();
        }
        return null;
    }

    private void setChildForComparisonResult(Node<T> node, T value) {
        int comparisonResult = node.compareTo(value);
        if (comparisonResult > 0) {
            node.setLeft(new Node<>(value));
        }
        if (comparisonResult < 0) {
            node.setRight(new Node<>(value));
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int nrOfNodesInLastLine = (int) Math.pow(2, height() - 1);
        appendString(sb, List.of(root), nrOfNodesInLastLine);
        return sb.toString();
    }

    private void appendString(StringBuilder sb, List<Node<T>> nodes, int nrOfNodesInLastLine) {
        List<Node<T>> children = new ArrayList<>();
        int paddingBefore = (nrOfNodesInLastLine / nodes.size()) - 1;
        int paddingBetween = paddingBefore * 2 + 1;
        sb.append(" ".repeat(paddingBefore));
        for (Node<T> node : nodes) {
            if (node != null) {
                sb.append(node.getValue());
                children.add(node.getLeft());
                children.add(node.getRight());
            } else {
                sb.append("-");
                children.add(null);
                children.add(null);
            }
            sb.append(" ".repeat(paddingBetween));
        }
        sb.append(System.lineSeparator());
        if (children.stream().anyMatch(Objects::nonNull)) {
            appendString(sb, children, nrOfNodesInLastLine);
        }
    }

    public T search(T value) {
        if (root == null) {
            return null;
        }
        Objects.requireNonNull(value);
        Node<T> closestNode = findClosestNode(root, value);
        if (closestNode.compareTo(value) == 0) {
            return closestNode.getValue();
        }
        return null;
    }

    public void traverseInorder(Consumer<T> consumer) {
        traverseInorder(root, consumer);
    }

    private void traverseInorder(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            traverseInorder(node.getLeft(), consumer);
            consumer.accept(node.getValue());
            traverseInorder(node.getRight(), consumer);
        }
    }

    public Node<T> getRoot() {
        return root;
    }
}
