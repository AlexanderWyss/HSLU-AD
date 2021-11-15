package d.d1.stack;

import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {
    private int index = 0;
    private T[] stack;

    @SuppressWarnings("unchecked")
    public ArrayStack(int size) {
        this.stack = (T[]) new Object[size];
    }

    public boolean isEmpty() {
        return index == 0;
    }

    public boolean isFull() {
        return index == stack.length;
    }

    public int size() {
        return stack.length;
    }

    public boolean push(T element) throws StackFullException {
        if (isFull()) {
            throw new StackFullException(stack.length);
        }

        stack[index++] = element;
        return true;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        T element = stack[--index];
        stack[index] = null; // remove element from stack to allow GC to delete object
        return element;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }

        return stack[index - 1];
    }
}
