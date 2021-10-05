package d2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TreeTest {
    @Test
    void test() {
        Tree<String> tree = new Tree<>();
        String[] elements = "G H B F E A D C".split(" ");
        for (String element : elements) {
            tree.insert(element);
        }
        System.out.println(tree);
    }

    @Test
    void multipleValues_insert_correctTree() {
        Tree<String> tree = new Tree<>();
        String[] elements = "G H B F E A D C".split(" ");
        for (String element : elements) {
            tree.insert(element);
        }
        Node<String> nodeG = tree.getRoot();
        assertEquals("G", nodeG.getValue());
        Node<String> nodeB = nodeG.getLeft();
        assertEquals("B", nodeB.getValue());
        assertEquals("A", nodeB.getLeft().getValue());
        Node<String> nodeF = nodeB.getRight();
        assertEquals("F", nodeF.getValue());
        Node<String> nodeE = nodeF.getLeft();
        assertEquals("E", nodeE.getValue());
        Node<String> nodeD = nodeE.getLeft();
        assertEquals("D", nodeD.getValue());
        assertEquals("C", nodeD.getLeft().getValue());
        assertEquals("H", nodeG.getRight().getValue());
    }
}