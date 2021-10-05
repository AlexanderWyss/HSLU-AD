package d2;

import org.junit.jupiter.api.Test;

class TreeTest {
    @Test
    void test() {
        Tree<String> tree = new Tree<>();
        String[] elements = "G H B F E A D C".split(" ");
        for (String element : elements
        ) {
            tree.insert(element);
        }
        System.out.println(tree);
    }
}