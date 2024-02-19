package stack;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;

import java.util.NoSuchElementException;

/**
 * Base test for any class that implements the Stack interface.
 */
public class TqsStackTest {

    /**
     * The stack to use in all the tests: set this in subclasses.
     */
    protected TqsStack<Integer> s;

    @BeforeEach
    public void createStack() {
        s = new TqsStack<>();
    }

    @Test
    public void testNewStackIsEmpty() {
        assertTrue(s.isEmpty());
    }

    @Test
    public void testNewStackHasSizeZero() {
        assertEquals(0, s.size());
    }

    @Test
    public void testPushesToEmptyStack() {
        int numberOfPushes = 8;
        for (int i = 0; i < numberOfPushes; i++) {
            s.push(i);
        }
        assertFalse(s.isEmpty());
        assertEquals(numberOfPushes, s.size());
    }

    @Test
    public void testPushThenPop() {
        int num = 2;
        s.push(num);
        assertEquals(num, s.pop());
    }

    @Test
    public void testPushThenPeek() {
        int num = 2;
        s.push(num);
        int size = s.size();
        assertEquals(num, s.peek());
        assertEquals(size, s.size());
    }

    @Test
    public void testPoppingDownToEmpty() {
        int numberOfPushes = 8;
        for (int i = 0; i < numberOfPushes; i++) {
            s.push(i);
        }
        for (int i = 0; i < numberOfPushes; i++) {
            s.pop();
        }
        assertTrue(s.isEmpty());
        assertEquals(0, s.size());
    }

    @Test
    public void testPopOnEmptyStackThrows() {
        assertTrue(s.isEmpty());
        assertThrows(NoSuchElementException.class, () -> s.pop());
    }

    @Test
    public void testPeekIntoEmptyStackThrows() {
        assertTrue(s.isEmpty());
        assertThrows(NoSuchElementException.class, () -> s.peek());
    }

    @Test
    public void testPushToFullStackThrows(){
        int max_size = 5;
        TqsStack<Integer> bounded_s = new TqsStack<>(max_size);

        for (int i = 0; i < max_size; i++) {
            bounded_s.push(i);
        }

        assertEquals(max_size, bounded_s.size());
        assertThrows(IllegalStateException.class, () -> bounded_s.push(0));
    }
}
