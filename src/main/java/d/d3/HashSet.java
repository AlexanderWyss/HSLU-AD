package d.d3;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import static java.lang.Math.abs;

public class HashSet<T> implements Iterable<T> {
    private Object[] elements;

    public HashSet(final int initSize) {
        this.elements = new Object[initSize];
    }

    public boolean add(T element) {
        Objects.requireNonNull(element);
        int i = calcIndex(element);
        if (elements[i] == null) {
            elements[i] = element;
            return true;
        } else if (element.equals(elements[i])) {
            return false;
        } else {
            throw new RuntimeException("HashCode index collision.");
        }
    }


    public boolean remove(T element) {
        Objects.requireNonNull(element);
        int i = calcIndex(element);
        if (element.equals(elements[i])) {
            elements[i] = null;
            return true;
        }
        return false;
    }

    private int calcIndex(T element) {
        return abs(element.hashCode() % elements.length);
    }

    @Override
    public String toString() {
        return "HashSet{" +
                "elements=" + Arrays.toString(elements) +
                '}';
    }

    @NotNull
    @Override
    @SuppressWarnings("unchecked")
    public Iterator<T> iterator() {
        return (Iterator<T>) Arrays.stream(elements).filter(Objects::nonNull).iterator();
    }
}
