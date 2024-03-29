package d.d2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Binary Tree Map, allows null values
 *
 * @param <K> Type of key, must be comparable
 * @param <V> Type of value
 */
public class BinaryTree<K extends Comparable<K>, V> implements Tree<K, V> {
    private Node<K, V> root;

    @Override
    public void add(K key, V value) {
        Node<K, V> closestNode = findClosestNode(key);
        if (closestNode == null) {
            root = new Node<>(key, value);
        } else {
            setValueForComparisonResult(closestNode, key, value);
        }
    }

    private Node<K, V> findClosestNode(K key) {
        if (root == null) {
            return null;
        }
        return findClosestNode(root, key);
    }

    private Node<K, V> findClosestNode(Node<K, V> node, K key) {
        var childNode = getChildForComparisonResult(node, key);
        if (childNode != null) {
            return findClosestNode(childNode, key);
        }
        return node;
    }

    private Node<K, V> getChildForComparisonResult(Node<K, V> node, K key) {
        int comparisonResult = node.compareTo(key);
        if (comparisonResult > 0) {
            return node.getLeft();
        }
        if (comparisonResult < 0) {
            return node.getRight();
        }
        return null;
    }

    private void setValueForComparisonResult(Node<K, V> node, K key, V value) {
        int comparisonResult = node.compareTo(key);
        if (comparisonResult > 0) {
            node.setLeft(new Node<>(key, value));
        } else if (comparisonResult < 0) {
            node.setRight(new Node<>(key, value));
        } else {
            node.setValue(value);
        }
    }

    @Override
    public V get(K key) {
        Objects.requireNonNull(key);
        var node = findNode(key);
        if (node != null) {
            return node.getValue();
        }
        return null;
    }

    private Node<K, V> findNode(K key) {
        Node<K, V> closestNode = findClosestNode(key);
        if (closestNode != null && closestNode.compareTo(key) == 0) {
            return closestNode;
        }
        return null;
    }


    @Override
    public void traverseInorder(BiConsumer<K, V> consumer) {
        traverseInorder(root, consumer);
    }

    private void traverseInorder(Node<K, V> node, BiConsumer<K, V> consumer) {
        if (node != null) {
            traverseInorder(node.getLeft(), consumer);
            consumer.accept(node.getKey(), node.getValue());
            traverseInorder(node.getRight(), consumer);
        }
    }

    public Node<K, V> getRoot() {
        return root;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int nrOfNodesInLastLine = (int) Math.pow(2, height() - 1);
        appendString(sb, List.of(root), nrOfNodesInLastLine);
        return sb.toString();
    }

    private void appendString(StringBuilder sb, List<Node<K, V>> nodes, int nrOfNodesInLastLine) {
        List<Node<K, V>> children = new ArrayList<>();
        int paddingBefore = (nrOfNodesInLastLine / nodes.size()) - 1;
        int paddingBetween = paddingBefore * 2 + 1;
        sb.append(" ".repeat(paddingBefore));
        for (var node : nodes) {
            if (node != null) {
                sb.append(node.getKey());
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

    public int height() {
        return height(root);
    }

    private int height(Node<K, V> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }
}
