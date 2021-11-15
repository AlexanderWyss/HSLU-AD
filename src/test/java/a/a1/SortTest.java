package a.a1;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortTest {
    @Test
    void insertionSort() {
        Integer[] ints = {0, -7, 2, 1, 3, 4, 1, -7, 15, 4, 7, 8};
        Sort.insertionSort(ints);
        assertArrayEquals(new Integer[]{-7, -7, 0, 1, 1, 2, 3, 4, 4, 7, 8, 15}, ints);
    }

    private final static int n = 100_000;
    private final static int tries = 10;
    private final static boolean printArray = false;

    @Test
    void insertionSort_preSorted_runtime() {
        Integer[] ints = getPreSortedArray();
        Measurement measurement = getMeasurement("insertionSort.preSorted", ints, Sort::insertionSort);
        measurement.printLog();
    }

    @Test
    void insertionSort_reverseSorted_runtime() {
        Integer[] ints = getReverseSorted();
        Measurement measurement = getMeasurement("insertionSort.reverseSorted", ints, Sort::insertionSort);
        measurement.printLog();
    }

    @Test
    void insertionSort_randomSorted_runtime() {
        Integer[] ints = getRandomSorted();
        Measurement measurement = getMeasurement("insertionSort.randomSorted", ints, Sort::insertionSort);
        measurement.printLog();
    }

    @NotNull
    private Integer[] getPreSortedArray() {
        Integer[] ints = new Integer[n];
        for (int i = 0; i < n; i++) {
            ints[i] = i;
        }
        return ints;
    }

    @NotNull
    private Integer[] getReverseSorted() {
        Integer[] ints = new Integer[n];
        for (int i = 0; i < n; i++) {
            ints[n - i - 1] = i;
        }
        return ints;
    }

    private Integer[] getRandomSorted() {
        Integer[] ints = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            ints[i] = random.nextInt();
        }
        return ints;
    }

    @NotNull
    private <T> Measurement getMeasurement(final String name, final T[] originalArray, final Consumer<T[]> sorter) {
        if (printArray) {
            System.out.println(Arrays.toString(originalArray));
        }
        Measurement measurement = new Measurement(name);
        for (int i = 0; i < tries; i++) {
            T[] array = Arrays.copyOf(originalArray, originalArray.length);
            measurement.start();
            sorter.accept(array);
            measurement.stop();
            if (printArray && i == 0) {
                System.out.println(Arrays.toString(array));
            }
        }
        return measurement;
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