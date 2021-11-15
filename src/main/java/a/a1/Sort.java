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
     *
     * @param array
     */
    public static void selectionSort(final int[] array) {
        for (int i = 0; i < array.length; i++) {
            int smallestValueIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[smallestValueIndex]) {
                    smallestValueIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[smallestValueIndex];
            array[smallestValueIndex] = temp;
        }
    }
}
