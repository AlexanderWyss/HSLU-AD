package a.a1;

public class Sort {
    /**
     * Sortiert das int-Array aufsteigend
     *
     * @param array Zu sortierendes Array.
     */
    public static void insertionSort(final Integer[] array) {
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
}
