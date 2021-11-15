package d.d1.singlelinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SingleLinkedListIterator<T> implements Iterator<T> {
    private Node<T> current;

    public SingleLinkedListIterator(Node<T> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return current.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        current = current.next();
        return current.get();
    }
}
