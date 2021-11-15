package d.d1.stack;

import d.d1.stack.ArrayStack;
import d.d1.stack.Stack;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

class ArrayStackTest {
    @Test
    void test_emptyStack() {
        Stack<String> stack = new ArrayStack<>(5);

        assertThat(stack.isEmpty()).isTrue();
    }

    @Test
    void test_push_oneElement()  throws Exception{
        Stack<String> stack = new ArrayStack<>(1);

        stack.push("Hallo");

        assertThat(stack.isEmpty()).isFalse();
        assertThat(stack.isFull()).isTrue();
    }

    @Test
    void test_push_multiple() throws Exception {
        Stack<String> stack = new ArrayStack<>(5);

        stack.push("Hallo");
        stack.push("2");

        assertThat(stack.isFull()).isFalse();
    }

    @Test
    void test_pop_multipleElements() throws Exception {
        Stack<String> stack = new ArrayStack<>(5);
        stack.push("Hallo1");
        stack.push("Test2");
        stack.push("Test3");

        String element1 = stack.pop();
        String element2 = stack.pop();
        String element3 = stack.pop();

        assertThat(element1).isEqualTo("Test3");
        assertThat(element2).isEqualTo("Test2");
        assertThat(element3).isEqualTo("Hallo1");
        assertThat(stack.isEmpty()).isTrue();
    }

}