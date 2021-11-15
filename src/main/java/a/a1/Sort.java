package a.a1;

public class Sort {
    /**
     * Sortiert das int-Array aufsteigend
     *
     * @param array Zu sortierendes Array.
     */
    public static void insertionSort(final int[] array) {
        int element;
        int j;
        for (int i = 1; i < array.length; i++) {
            element = array[i]; // next element for insert
            j = i; // array[1]..array[j-1] already sorted
            while (j > 0 && array[j - 1] > element) {
                array[j] = array[j - 1]; // shift right
                j--; // go further left
            }
            array[j] = element; // insert element
        } // array[1]...array[j] sorted
    }

    /**
     * b)Bsp instabil (| bis hier sortiert):
     * [|4, 3[a], 1, 3[b], 2]
     * [1, |3[a], 4, 3[b], 2]
     * [1, 2, |4, 3[b], 3[a]] // unstable (kann ebenfalls passieren wenn das letzte 3 ersetzt wird) bsp:  [|4, 3[a], 1, 2, 3[b]]
     * [1, 2, 3[b], |4, 3[a]]
     * [1, 2, 3[b], 3[a], 4]
     * <p>
     * c) nein, ist immer gleich 0(n^2) unabhängig von den Daten
     * f) pro durchlauf von i sind 2(n - i) vergleiche notwendig
     * [n]E[i=0]2(n-i)+1 = (n+1)^2 = n^2 + 2n + 1
     * O(n^2)
     *
     * @param array
     */
    public static void selectionSort(final int[] array) {
        for (int i = 0; i < array.length; i++) { // n | 1
            int smallestValueIndex = i;
            for (int j = i + 1; j < array.length; j++) { // n - i | 1
                if (array[j] < array[smallestValueIndex]) { // | 1
                    smallestValueIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[smallestValueIndex];
            array[smallestValueIndex] = temp;
        }
    }

    public static void bubbleSort(final int[] array) {
        boolean isSorted = false;
        for (int i = 0; i < array.length - 1 && !isSorted; i++) {
            isSorted = true;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    isSorted = false;
                }
            }
        }
    }
}
