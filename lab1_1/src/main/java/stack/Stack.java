package stack;

/**
 * A small stack interface. You can (1) query the size of the stack, (2) ask
 * whether it is empty, (3) push an item, (4) pop an item, and (5) peek at the
 * top item.
 */
public interface Stack<T> {

    /**
     * Adds the given item to the top of the stack.
     */
    void push(T item);

    /**
     * Removes the top item from the stack and returns it.
     *
     * @exception java.util.NoSuchElementException if the stack is empty.
     */
    T pop();

    /**
     * Returns the top item from the stack without popping it.
     *
     * @exception java.util.NoSuchElementException if the stack is empty.
     */
    T peek();

    /**
     * Returns the number of items currently in the stack.
     */
    int size();

    /**
     * Returns whether the stack is empty or not.
     */
    default boolean isEmpty() {
        return size() == 0;
    }
}
