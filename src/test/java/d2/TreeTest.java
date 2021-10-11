package d2;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

class TreeTest {
    @Test
    void test() {
        Tree<String, String> tree = new Tree<>();
        String[] elements = "G H B F E A D C".split(" ");
        for (String element : elements) {
            tree.add(element, "jep");
        }
        System.out.println(tree);
    }

    @Test
    void multipleValues_insert_correctTree() {
        Tree<String, String> tree = new Tree<>();
        String[] elements = "G H B F E A D C".split(" ");
        for (String element : elements) {
            tree.add(element, "");
        }
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
    void multipleValues_get_returnsCorrectValue() {
        Tree<String, String> tree = new Tree<>();
        String[] elements = "G H B F E A D C".split(" ");
        for (String element : elements) {
            tree.add(element, element + "_Value");
        }
        assertEquals("G_Value", tree.get("G"));
        assertEquals("H_Value", tree.get("H"));
        assertEquals("B_Value", tree.get("B"));
        assertEquals("F_Value", tree.get("F"));
    }

    @Test
    void valueWithKey_insertWithSameKey_valueOverwritten() {
        Tree<String, String> tree = new Tree<>();
        String[] elements = "G H B F E A D C".split(" ");
        for (String element : elements) {
            tree.add(element, "");
        }
        assertEquals("", tree.get("F"));

        tree.add("F", "new Value");
        assertEquals("new Value", tree.get("F"));
    }

    @Test
    @SuppressWarnings("unchecked")
    void multipleValues_traversInOrder_traversedInOrder() {
        Tree<String, String> tree = new Tree<>();
        String[] elements = "G H B F E A D C".split(" ");
        for (String element : elements) {
            tree.add(element, element + "_Value");
        }
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
}