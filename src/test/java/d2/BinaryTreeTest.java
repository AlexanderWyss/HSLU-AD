package d2;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BinaryTreeTest {
    @Test
    void test() {
        Tree<String, String> tree = buildTree("A B C D E");
        System.out.println(tree);
    }

    @Test
    void multipleValues_add_correctTree() {
        BinaryTree<String, String> tree = buildTree();

        Node<String, String> nodeG = tree.getRoot();
        assertEquals("G", nodeG.getKey());
        Node<String, String> nodeB = nodeG.getLeft();
        assertEquals("B", nodeB.getKey());
        assertEquals("A", nodeB.getLeft().getKey());
        Node<String, String> nodeF = nodeB.getRight();
        assertEquals("F", nodeF.getKey());
        Node<String, String> nodeE = nodeF.getLeft();
        assertEquals("E", nodeE.getKey());
        Node<String, String> nodeD = nodeE.getLeft();
        assertEquals("D", nodeD.getKey());
        assertEquals("C", nodeD.getLeft().getKey());
        assertEquals("H", nodeG.getRight().getKey());
    }

    @Test
    void valueWithKey_addWithSameKey_valueOverwritten() {
        Tree<String, String> tree = buildTree();
        assertEquals("F_Value", tree.get("F"));

        tree.add("F", "new Value");
        assertEquals("new Value", tree.get("F"));
    }

    @Test
    void emptyTree_addKeyNull_throwNullPointerException() {
        Tree<String, String> tree = new BinaryTree<>();

        assertThrows(NullPointerException.class, () -> tree.add(null, ""));
    }

    @Test
    void treeWithMultipleValue_addKeyNull_throwNullPointerException() {
        Tree<String, String> tree = buildTree();

        assertThrows(NullPointerException.class, () -> tree.add(null, ""));
    }

    @Test
    void treeWithMultipleValue_addValueNull_valueSet() {
        Tree<String, String> tree = buildTree();
        assertEquals("G_Value", tree.get("G"));

        tree.add("G", null);

        assertNull(tree.get("G"));
    }

    @Test
    void multipleValues_get_returnsCorrectValue() {
        Tree<String, String> tree = buildTree();

        assertEquals("G_Value", tree.get("G"));
        assertEquals("H_Value", tree.get("H"));
        assertEquals("B_Value", tree.get("B"));
        assertEquals("F_Value", tree.get("F"));
    }

    @Test
    void multipleValues_getWithNonExistentKey_returnsNull() {
        Tree<String, String> tree = buildTree();

        assertNull(tree.get("nonExistentKey"));
    }

    @Test
    void emptyTree_getWithNonExistentKey_returnsNull() {
        Tree<String, String> tree = new BinaryTree<>();

        assertNull(tree.get("nonExistentKey"));
    }

    @Test
    void emptyTree_getKeyNull_throwNullPointerException() {
        Tree<String, String> tree = new BinaryTree<>();

        assertThrows(NullPointerException.class, () -> tree.get(null));
    }

    @Test
    void treeWithMultipleValue_getKeyNull_throwNullPointerException() {
        Tree<String, String> tree = buildTree();

        assertThrows(NullPointerException.class, () -> tree.get(null));
    }

    @Test
    @SuppressWarnings("unchecked")
    void multipleValues_traversInOrder_traversedInOrder() {
        Tree<String, String> tree = buildTree();
        BiConsumer<String, String> biConsumer = mock(BiConsumer.class);
        InOrder inOrder = inOrder(biConsumer);

        tree.traverseInorder(biConsumer);

        inOrder.verify(biConsumer).accept("A", "A_Value");
        inOrder.verify(biConsumer).accept("B", "B_Value");
        inOrder.verify(biConsumer).accept("C", "C_Value");
        inOrder.verify(biConsumer).accept("D", "D_Value");
        inOrder.verify(biConsumer).accept("E", "E_Value");
        inOrder.verify(biConsumer).accept("F", "F_Value");
        inOrder.verify(biConsumer).accept("G", "G_Value");
        inOrder.verify(biConsumer).accept("H", "H_Value");
    }

    @Test
    @SuppressWarnings("unchecked")
    void emptyTree_traversInOrder_traversedInOrder() {
        Tree<String, String> tree = new BinaryTree<>();
        BiConsumer<String, String> biConsumer = mock(BiConsumer.class);

        tree.traverseInorder(biConsumer);

        verifyNoInteractions(biConsumer);
    }

    private BinaryTree<String, String> buildTree() {
        return buildTree("G H B F E A D C");
    }

    private BinaryTree<String, String> buildTree(final String keyValues) {
        BinaryTree<String, String> tree = new BinaryTree<>();
        String[] elements = keyValues.split(" ");
        for (String element : elements) {
            tree.add(element, element + "_Value");
        }
        return tree;
    }
}