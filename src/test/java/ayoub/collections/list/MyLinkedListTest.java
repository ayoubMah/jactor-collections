package ayoub.collections.list;

import ayoub.list.MyLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
    }

    @Test
    void testAddAndGet() {
        assertEquals(3, list.size());
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));

        list.add(1, "X"); // A, X, B, C
        assertEquals("X", list.get(1));
        assertEquals(4, list.size());
    }

    @Test
    void testSet() {
        String old = list.set(1, "Z"); // A, Z, C
        assertEquals("B", old);
        assertEquals("Z", list.get(1));
    }

    @Test
    void testRemove() {
        String removed = list.remove(1); // remove "B"
        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertEquals("C", list.get(1));
    }

    @Test
    void testIndexOfAndLastIndexOf() {
        list.add("A"); // A, B, C, A
        assertEquals(0, list.indexOf("A"));
        assertEquals(3, list.lastIndexOf("A"));
        assertEquals(-1, list.indexOf("Z"));
    }

    @Test
    void testIterator() {
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("A", it.next());
        assertEquals("B", it.next());
        assertEquals("C", it.next());
        assertFalse(it.hasNext());
    }

    @Test
    void testSubList() {
        var sub = list.subList(0, 2); // [A, B]
        assertEquals(2, sub.size());
        assertEquals("A", sub.get(0));
        assertEquals("B", sub.get(1));
    }

    @Test
    void testViewMethod() {
        assertEquals("[A, B, C]", list.view());
    }
}
