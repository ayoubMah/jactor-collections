package ayoub.collections.list;

import ayoub.list.MyArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyArrayListTest {

    private MyArrayList<String> list;

    @BeforeEach
    void setup() {
        list = new MyArrayList<>();
        list.add("Ayoub");
        list.add("Basma");
        list.add("Dev ❤️");
    }

    @Test
    void testSizeAndGet() {
        assertEquals(3, list.size());
        assertEquals("Ayoub", list.get(0));
        assertEquals("Dev ❤️", list.get(2));
    }

    @Test
    void testAddElement() {
        list.add("Code");
        assertEquals(4, list.size());
        assertEquals("Code", list.get(3));
    }

    @Test
    void testAddAtIndex() {
        list.add(1, "Inserted");
        assertEquals("Inserted", list.get(1));
        assertEquals(4, list.size());
    }

    @Test
    void testSetElement() {
        String old = list.set(1, "Updated");
        assertEquals("Basma", old);
        assertEquals("Updated", list.get(1));
    }

    @Test
    void testRemoveByIndex() {
        String removed = list.remove(1);
        assertEquals("Basma", removed);
        assertEquals(2, list.size());
        assertEquals("Dev ❤️", list.get(1));
    }

    @Test
    void testIndexOfAndLastIndexOf() {
        list.add("Ayoub");
        assertEquals(0, list.indexOf("Ayoub"));
        assertEquals(3, list.lastIndexOf("Ayoub"));
    }

    @Test
    void testContainsAndIsEmpty() {
        assertTrue(list.contains("Ayoub"));
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void testIterator() {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(" ");
        }
        assertEquals("Ayoub Basma Dev ❤️ ", sb.toString());
    }

    @Test
    void testRemoveObject() {
        list.remove("Basma");
        assertEquals(2, list.size());
        assertFalse(list.contains("Basma"));
    }

    @Test
    void testOutOfBoundsAccess() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, "oops"));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(99));
    }
}
