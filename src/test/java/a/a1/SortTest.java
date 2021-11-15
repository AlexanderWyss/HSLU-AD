package a.a1;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortTest {
    private final static int N = 10000;
    private final static int TRIES = 10;
    private final static boolean PRINT_ARRAY = false;
    private final static int[] PRE_SORTED_ARRAY = createPreSortedArray();
    private final static int[] REVERSE_SORTED_ARRAY = createReverseSortedArray();
    private final static int[] RANDOM_SORTED_ARRAY = createRandomSortedArray();

    @Test
    void insertionSort() {
        testSort(Sort::insertionSort);
    }
    @Test
    void insertionSort_preSorted_runtime() {
        measurePreSorted("insertionSort", Sort::insertionSort);
    }

    @Test
    void insertionSort_reverseSorted_runtime() {
        measureReverseSorted("insertionSort", Sort::insertionSort);
    }

    @Test
    void insertionSort_randomSorted_runtime() {
        measureRandomSorted("insertionSort", Sort::insertionSort);
    }

    @Test
    void selectionSort() {
        testSort(Sort::selectionSort);
    }

    @Test
    void selectionSort_preSorted_runtime() {
        measurePreSorted("selectionSort", Sort::selectionSort);
    }

    @Test
    void selectionSort_reverseSorted_runtime() {
        measureReverseSorted("selectionSort", Sort::selectionSort);
    }

    @Test
    void selectionSort_randomSorted_runtime() {
        measureRandomSorted("selectionSort", Sort::selectionSort);
    }
    
    @Test
    void bubbleSort() {
        testSort(Sort::bubbleSort);
    }

    @Test
    void bubbleSort_preSorted_runtime() {
        measurePreSorted("bubbleSort", Sort::bubbleSort);
    }

    @Test
    void bubbleSort_reverseSorted_runtime() {
        measureReverseSorted("bubbleSort", Sort::bubbleSort);
    }

    @Test
    void bubbleSort_randomSorted_runtime() {
        measureRandomSorted("bubbleSort", Sort::bubbleSort);
    }

    private void testSort(Consumer<int[]> sorter) {
        int[] ints = {0, -7, 2, 1, 3, 4, 1, -7, 15, 4, 7, 8};
        sorter.accept(ints);
        assertArrayEquals(new int[]{-7, -7, 0, 1, 1, 2, 3, 4, 4, 7, 8, 15}, ints);
    }

    private void measureRandomSorted(String name, Consumer<int[]> sorter) {
        Measurement measurement = measureRuntime(name + ".randomSorted", RANDOM_SORTED_ARRAY, sorter);
        measurement.printLog();
    }

    private void measureReverseSorted(String name, Consumer<int[]> sorter) {
        Measurement measurement = measureRuntime(name + ".reverseSorted", REVERSE_SORTED_ARRAY, sorter);
        measurement.printLog();
    }

    private void measurePreSorted(String name, Consumer<int[]> sorter) {
        Measurement measurement = measureRuntime(name + ".preSorted", PRE_SORTED_ARRAY, sorter);
        measurement.printLog();
    }

    private Measurement measureRuntime(String name, int[] originalInts, Consumer<int[]> sorter) {
        if (PRINT_ARRAY) {
            System.out.println(Arrays.toString(originalInts));
        }
        Measurement measurement = new Measurement(name);
        for (int i = 0; i < TRIES; i++) {
            int[] ints = copyArray(originalInts);
            measurement.start();
            sorter.accept(ints);
            measurement.stop();
            if (PRINT_ARRAY && i == 0) {
                System.out.println(Arrays.toString(ints));
            }
        }
        return measurement;
    }

    private static int[] createPreSortedArray() {
        int[] ints = new int[N];
        for (int i = 0; i < N; i++) {
            ints[i] = i;
        }
        return ints;
    }

    private static int[] createReverseSortedArray() {
        int[] ints = new int[N];
        for (int i = 0; i < N; i++) {
            ints[N - i - 1] = i;
        }
        return ints;
    }

    private static int[] createRandomSortedArray() {
        int[] ints = new int[N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            ints[i] = random.nextInt();
        }
        return ints;
    }

    private int[] copyArray(int[] originalArray) {
        return Arrays.copyOf(originalArray, originalArray.length);
    }

    private static class Measurement {
        private final String name;
        private Long startMs;
        private final List<Long> measurements = new ArrayList<>();

        public Measurement(String name) {
            this.name = name;
        }

        public void start() {
            startMs = System.currentTimeMillis();
        }

        public void stop() {
            measurements.add(System.currentTimeMillis() - startMs);
            startMs = null;
        }

        public void printLog() {
            System.out.printf("%s: %gms%n", name, measurements.stream().mapToLong(Long::longValue).average().getAsDouble());
            for (Long measurement : measurements) {
                System.out.printf("\t%dms%n", measurement);
            }
        }
    }
}