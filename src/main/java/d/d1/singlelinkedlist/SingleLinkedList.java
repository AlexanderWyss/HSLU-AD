package d.d1.singlelinkedlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SingleLinkedList<T> implements List<T> {
    private int size = 0;
    private final Node<T> head = new Node<>(null);

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator<>(head);
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T element) {
        add(0, element);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (T element : c) {
            add(index, element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head.link(null);
        size = 0;
    }

    @Override
    public T get(int index) {
        Node<T> currentNode = head;
        for (int i = -1; i < index; i++) {
            if (!currentNode.hasNext()) {
                throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size()));
            }
            currentNode = currentNode.next();
        }
        return currentNode.get();
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {
        int currentIndex = 0;
        Node<T> currentNode = head;
        while (currentNode.hasNext() && currentIndex < index) {
            currentNode = currentNode.next();
            currentIndex++;
        }
        Node<T> node = new Node<>(element);
        node.link(currentNode.next());
        currentNode.link(node);
        size++;
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}
