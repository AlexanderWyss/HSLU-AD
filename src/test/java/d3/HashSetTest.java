package d3;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.*;

class HashSetTest {

    @Test
    void test() {
        new HashSet<String>(10);
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