public class Stack<T> {
    private class StackNode {
        T value;
        StackNode prev;

        StackNode(T value, StackNode prev) {
            this.value = value;
            this.prev = prev;
        }
    }

    StackNode current = null;

    public void push(T element) {
        current = new StackNode(element, current);
    }

    public T top() {
        if (isEmpty()) {
            throw new StackException("Peeking empty stack.");
        }

        return current.value;
    }

    public T pop() {
        if (isEmpty()) {
            throw new StackException("Popping from empty stack.");
        }

        T value = current.value;
        current = current.prev;

        return value;
    }

    public boolean isEmpty() {
        return (current == null);
    }
}

class StackException extends RuntimeException {
    public StackException() {
        super();
    }

    public StackException(String message) {
        super(message);
    }
}
