package ayoub.collections.list;

import ayoub.list.MyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    private MyLinkedList<String> list;

    @BeforeEach
    void setup() {
        list = new MyLinkedList<>();
        list.add(0, "A");
        list.add(1, "B");
        list.add(2, "C");
    }

    @Test
    void testAddAndGet() {
        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
    }

    @Test
    void testAddAtIndex() {
        list.add(1, "X");
        assertEquals(4, list.size());
        assertEquals("X", list.get(1));
        assertEquals("[A, X, B, C]", list.view());
    }

    @Test
    void testSet() {
        String old = list.set(1, "Z");
        assertEquals("B", old);
        assertEquals("Z", list.get(1));
    }

    @Test
    void testRemoveByIndex() {
        String removed = list.remove(1);
        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertEquals("[A, C]", list.view());
    }

    @Test
    void testFirstAndLast() {
        assertEquals("A", list.first());
        assertEquals("C", list.last());
    }

    @Test
    void testAddFirstAndAddLast() {
        list.addFirst("Start"); // typo kept intentionally as in your class
        list.addLast("End");
        assertEquals("[Start, A, B, C, End]", list.view());
        assertEquals(5, list.size());
    }

    /*@Test
    void testRemoveFirstAndLast() {
        list.addLast("D");
        String first = list.removeFirst();
        String last = list.removeLast();
        assertEquals("A", first);
        assertEquals("D", last);
        assertEquals("[B, C]", list.view());
    }*/

    @Test
    void testIteratorTraversal() {
        Iterator<String> it = list.iterator();
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        assertEquals("ABC", sb.toString());
    }

    @Test
    void testOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(10, "X"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(99));
    }

    @Test
    void testEmptyListOperations() {
        MyLinkedList<Integer> empty = new MyLinkedList<>();
        assertTrue(empty.isEmpty());
        assertNull(empty.first());
        assertNull(empty.last());
        assertNull(empty.removeFirst());
        assertNull(empty.removeLast());
    }

    @Test
    void testViewRepresentation() {
        assertEquals("[A, B, C]", list.view());
    }
}
