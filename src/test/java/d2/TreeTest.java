package d2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}