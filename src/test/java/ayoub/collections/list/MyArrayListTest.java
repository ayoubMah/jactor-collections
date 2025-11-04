package ayoub.collections.list;

import ayoub.collections.MyList;
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

    @Test
    void testTrimToSize() {
        // given a list that has extra capacity
        MyArrayList<Integer> list = new MyArrayList<>(20);
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        int oldCapacity = getCapacity(list); // helper to introspect internal length
        assertTrue(oldCapacity >= 20, "Initial capacity should be 20 or more");

        // when
        list.trimToSize();

        // then
        int newCapacity = getCapacity(list);
        assertEquals(list.size(), newCapacity, "Array should shrink to match size");
        assertTrue(newCapacity < oldCapacity, "Capacity should be reduced");
    }

    /**
     * Helper to get internal capacity of MyArrayList via reflection.
     */
    private int getCapacity(MyArrayList<?> list) {
        try {
            java.lang.reflect.Field arrField = MyArrayList.class.getDeclaredField("arr");
            arrField.setAccessible(true);
            Object[] arr = (Object[]) arrField.get(list);
            return arr.length;
        } catch (Exception e) {
            fail("Reflection failed: " + e.getMessage());
            return -1;
        }
    }

    @Test
    void testSubListCopyBehavior() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        // when
        MyList<String> sub = list.subList(1, 4); // expected ["B", "C", "D"]

        // then
        assertEquals(3, sub.size());
        assertEquals("B", sub.get(0));
        assertEquals("D", sub.get(2));

        // modify sublist
        sub.set(1, "X");
        // verify it's a copy, not a view (parent unaffected)
        assertEquals("C", list.get(2));
        assertEquals("X", sub.get(1));
    }

    @Test
    void testSubListOutOfBounds() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1, 2));
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(0, 5));
        assertThrows(IllegalArgumentException.class, () -> list.subList(2, 1)); // from > to
    }
}
