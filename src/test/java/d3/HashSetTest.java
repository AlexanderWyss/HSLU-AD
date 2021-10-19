package d3;

import d1.stack.StackFullException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class HashSetTest {
    private static final Logger LOGGER = LogManager.getLogger(HashSetTest.class);
    private final int performanceSize = 10_000_000;
    private final int performanceTries = 5;

    @Test
    void performance_hashSet() {
        long durationSum = 0;
        for (int tries = 0; tries < performanceTries; tries++) {
            HashSet<Integer> set = new HashSet<>(performanceSize);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < performanceSize; i++) {
                set.add(i);
            }
            long duration = System.currentTimeMillis() - startTime;
            durationSum += duration;
            LOGGER.info("Performance fill HashSet ({}): {}ms", tries, duration);
        }
        LOGGER.info("Performance fill HashSet average: {}ms", durationSum / performanceTries);
    }

    @Test
    void performance_javaStack() {
        long durationSum = 0;
        for (int tries = 0; tries < performanceTries; tries++) {
            Stack<Integer> javaStack = new Stack<>();
            javaStack.ensureCapacity(performanceSize);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < performanceSize; i++) {
                javaStack.add(i);
            }
            long duration = System.currentTimeMillis() - startTime;
            durationSum += duration;
            LOGGER.info("Performance fill JavaStack ({}): {}ms", tries, duration);
        }
        LOGGER.info("Performance fill JavaStack average: {}ms", durationSum / performanceTries);
    }

    @Test
    void performance_ownStack() throws StackFullException {
        long durationSum = 0;
        for (int tries = 0; tries < performanceTries; tries++) {
            d1.stack.Stack<Integer> ownStack = new d1.stack.ArrayStack<>(performanceSize);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < performanceSize; i++) {
                ownStack.push(i);
            }
            long duration = System.currentTimeMillis() - startTime;
            durationSum += duration;
            LOGGER.info("Performance fill own Stack ({}): {}ms", tries, duration);
        }
        LOGGER.info("Performance fill own Stack average: {}ms", durationSum / performanceTries);
    }

    @Test
    void performance_deque() {
        long durationSum = 0;
        for (int tries = 0; tries < performanceTries; tries++) {
            Deque<Integer> deque = new ArrayDeque<>(performanceSize);
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < performanceSize; i++) {
                deque.push(i);
            }
            long duration = System.currentTimeMillis() - startTime;
            durationSum += duration;
            LOGGER.info("Performance fill Deque ({}): {}ms", tries, duration);
        }
        LOGGER.info("Performance fill Deque average: {}ms", durationSum / performanceTries);
    }

    @Test
    void set_addNewElement_addReturnsTrue() {
        HashSet<Integer> set = new HashSet<>(10);

        assertTrue(set.add(42));
    }

    @Test
    void emptySet_add_elementAdded() {
        HashSet<Integer> set = new HashSet<>(10);

        set.add(42);
        set.add(10);
        set.add(4);

        assertThat(set, containsInAnyOrder(42, 10, 4));
    }

    @Test
    void setWithElement_sameElementAddedAgain_addReturnFalseSetContainsElementOnce() {
        HashSet<Integer> set = new HashSet<>(10);
        set.add(42);

        assertFalse(set.add(42));

        assertThat(set, containsInAnyOrder(42));
    }

    @Test
    void setWithElement_elementWithSameCalculatedIndex_throwsRuntimeException() {
        HashSet<Integer> set = new HashSet<>(10);
        set.add(0);

        validateIndexCollision(set, 10);
        assertThat(set, containsInAnyOrder(0));
    }

    @Test
    void set_addNull_throwsNullPointerException() {
        HashSet<Integer> set = new HashSet<>(10);

        assertThrows(NullPointerException.class, () -> set.add(null));
    }

    @Test
    void setWithMultipleElement_removeElement_elementRemovedReturnTrue() {
        HashSet<Integer> set = new HashSet<>(10);
        set.add(4);
        set.add(15);
        set.add(17);

        assertTrue(set.remove(15));

        assertThat(set, containsInAnyOrder(4, 17));
    }

    @Test
    void setWithMultipleElement_removeNonExistentElement_setUnchangedReturnFalse() {
        HashSet<Integer> set = new HashSet<>(10);
        set.add(4);
        set.add(15);
        set.add(17);

        assertFalse(set.remove(16));

        assertThat(set, containsInAnyOrder(4, 15, 17));
    }

    @Test
    void setWithMultipleElement_removeNonExistentElementWithSameCalcIndexAsExisting_setUnchangedReturnFalse() {
        HashSet<Integer> set = new HashSet<>(10);
        set.add(4);
        set.add(15);
        set.add(17);
        validateIndexCollision(set, 25);

        assertFalse(set.remove(25));

        assertThat(set, containsInAnyOrder(4, 15, 17));
    }

    @Test
    void set_removeNull_throwsNullPointerException() {
        HashSet<Integer> set = new HashSet<>(10);

        assertThrows(NullPointerException.class, () -> set.remove(null));
    }

    private <T> void validateIndexCollision(HashSet<T> set, T value) {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> set.add(value));
        assertEquals("HashCode index collision.", exception.getMessage());
    }
}