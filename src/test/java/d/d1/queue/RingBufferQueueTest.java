package d.d1.queue;

import d.d1.queue.Queue;
import d.d1.queue.RingBufferQueue;
import org.junit.jupiter.api.Test;

import java.nio.BufferOverflowException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RingBufferQueueTest {
    @Test
    void test_add_oneElement() {
        Queue<String> queue = new RingBufferQueue<>();

        queue.add("Test");

        assertThat(queue.isEmpty()).isFalse();
        assertThat(queue.poll()).isEqualTo("Test");
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test
    void test_add_multipleElements() {
        Queue<String> queue = new RingBufferQueue<>();

        queue.add("Test1");
        queue.add("Test2");
        queue.add("Test3");

        assertThat(queue.poll()).isEqualTo("Test1");
        assertThat(queue.poll()).isEqualTo("Test2");
        assertThat(queue.poll()).isEqualTo("Test3");
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test()
    void test_add_fullQueue() {
        Queue<String> queue = new RingBufferQueue<>(2);
        queue.add("1");
        queue.add("2");

        assertThrows(BufferOverflowException.class, () -> queue.add("3"));
    }

    @Test
    void test_add_moreThanBufferSize() {
        Queue<String> queue = new RingBufferQueue<>(4);
        queue.add("Test1");
        queue.add("Test2");
        queue.add("Test3");
        queue.add("Test4");

        String firstElement = queue.poll();
        queue.add("Test5");

        assertThat(firstElement).isEqualTo("Test1");
        assertThat(queue.poll()).isEqualTo("Test2");
        assertThat(queue.poll()).isEqualTo("Test3");
        assertThat(queue.poll()).isEqualTo("Test4");
        assertThat(queue.poll()).isEqualTo("Test5");
        assertThat(queue.isEmpty()).isTrue();
    }

    @Test()
    void test_poll_withEmptyQueue() {
        Queue<String> queue = new RingBufferQueue<>();

        assertThrows(NoSuchElementException.class, queue::poll);
    }
}
