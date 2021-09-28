package d1.stack;

public interface Stack<E> {
    boolean isEmpty();
    boolean isFull();
    int size();
    boolean push(E element) throws StackFullException;
    E pop();
    E peek();
}

