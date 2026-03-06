package ayoub.list;
// ============================================================
// MyArrayList.java — Refactor Review
// ============================================================

// MISSING: Class-level thread-safety documentation.
// Every class should declare its threading policy.
// HINT: Add this before the class declaration:
//
// @NotThreadSafe  (from JCIP annotations, or just as a comment)
// This class is not thread-safe. External synchronization required
// for concurrent access. See MyVector for a synchronized variant,
// and ImmutableArrayList for a thread-safe immutable variant.

import ayoub.abstracts.MyAbstractList;
import ayoub.collections.MyCollection;

import java.util.Iterator;
import java.util.Objects;

public class MyArrayList<E> extends MyAbstractList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    // CONCERN: These two fields are not declared volatile, not final,
    // and not protected by any lock. That's fine for now since this
    // class is single-threaded. BUT — document that assumption.
    // When you build ConcurrentArrayList later, you'll need to revisit
    // both fields. Ask yourself: which operations on arr and size
    // need to be atomic with respect to each other?
    private E[] arr;
    private int size;

    // MISSING: modCount field — add this here.
    // HINT: It should be:
    //   protected int modCount = 0;
    // Why protected and not private?
    // Because your iterator (and future SubList inner class) needs it.
    // Every structural modification must increment it.
    // What counts as "structural"? size-changing ops only, or set() too?

    public MyArrayList() {
        arr = (E[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int initialCapacity) {
        // CONCERN: What should happen if initialCapacity is negative?
        // The JDK throws IllegalArgumentException here, not a silent zero.
        // HINT: Add a guard:
        //   if (initialCapacity < 0) throw new IllegalArgumentException(...)
        arr = (E[]) new Object[initialCapacity];
        size = 0;
    }

    // MISSING: A copy constructor — MyArrayList(MyCollection<? extends E> c)
    // This is essential for immutable variants later.
    // An immutable list needs to be initialized from an existing collection
    // and then never modified. Where would defensive copying happen?
    // HINT: Think about this signature:
    //   public MyArrayList(MyCollection<? extends E> c) { ... }

    @Override
    public int size() {
        return size;
    }

    private void rangeCheck(int index) {
        // GOOD: Correct boundary now (size - 1).
        // HINT: Consider extracting the message to a helper:
        //   private String outOfBoundsMsg(int index) {
        //       return "Index: " + index + ", Size: " + size;
        //   }
        // The JDK does exactly this — it keeps throw sites clean.
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    private void rangeCheckForAdd(int index) {
        // GOOD: Correct — allows index == size for tail insertion.
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }

    private void grow(int newCapacity) {
        // CONCERN: What if newCapacity overflows int?
        // If arr.length is near Integer.MAX_VALUE, arr.length + (arr.length >> 1)
        // will overflow to a negative number silently.
        // HINT: Look at how ArrayList handles MAX_ARRAY_SIZE:
        //   private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
        // Why minus 8? (Hint: some VMs reserve header words in arrays)
        E[] newArr = (E[]) new Object[newCapacity];
        System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > arr.length) {
            int newCap = arr.length + (arr.length >> 1);
            if (newCap < minCapacity) newCap = minCapacity;
            // MISSING: overflow guard here too — what if newCap went negative?
            grow(newCap);
        }
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return arr[index];
    }

    @Override
    public E set(int index, E elm) {
        rangeCheck(index);
        E prevElm = arr[index];
        arr[index] = elm;
        // QUESTION: Should set() increment modCount?
        // It doesn't change size, but it does change structure.
        // What does the JDK's ArrayList do here, and why?
        return prevElm;
    }

    @Override
    public void add(int index, E elm) {
        rangeCheckForAdd(index);
        ensureCapacity(size + 1);
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = elm;
        size++;
        // MISSING: modCount++ — add this after size++
        // Every structural modification must be counted.
    }

    @Override
    public boolean add(E elm) {
        ensureCapacity(size + 1);
        arr[size++] = elm;
        // MISSING: modCount++ here too
        return true;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E removed = arr[index];
        System.arraycopy(arr, index + 1, arr, index, size - index - 1);
        arr[--size] = null;
        // MISSING: modCount++ here too
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        // GOOD: Objects.equals handles null correctly.
        for (int i = 0; i < size; i++) {
            if (Objects.equals(arr[i], o)) return i;
        }
        return -1;
    }



    @Override
    public boolean addAll(int index, MyCollection<? extends E> c) {
        // HINT for when you implement this:
        // Step 1 — rangeCheckForAdd(index)
        // Step 2 — get c as an array (c.toArray())
        // Step 3 — ensureCapacity(size + numNew)
        // Step 4 — System.arraycopy to shift existing elements right
        // Step 5 — copy new elements in
        // Step 6 — size += numNew, modCount++
        // QUESTION: What should this return if c is empty?
        return false;
    }

    @Override
    public int lastIndexOf(Object o) {
        // GOOD: off-by-one fixed, Objects.equals used.
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(arr[i], o)) return i;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;

            // MISSING: modCount snapshot — add this:
            //   int expectedModCount = modCount;
            // Then at the start of next() and remove(), check:
            //   if (expectedModCount != modCount)
            //       throw new ConcurrentModificationException();
            // This makes your iterator fail-fast.

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                // MISSING: bounds check before access.
                // If someone calls next() without checking hasNext(),
                // you'll get an ArrayIndexOutOfBoundsException — which is
                // an implementation detail leaking through your API.
                // HINT: The JDK throws NoSuchElementException here instead.
                //   if (cursor >= size) throw new NoSuchElementException();
                return arr[cursor++];
            }

            @Override
            public void remove() {
                // CONCERN: What if remove() is called before next()?
                // cursor would be 0, --cursor becomes -1,
                // and you'd call MyArrayList.this.remove(-1) => exception.
                // HINT: Track a boolean `lastReturnedValid` or
                // mirror the JDK's `lastRet` field (initialized to -1).
                // Also: after remove(), update expectedModCount to the new
                // modCount value — otherwise the next next() call will
                // throw ConcurrentModificationException incorrectly.
                MyArrayList.this.remove(--cursor);
            }
        };
    }

    @Override
    public void trimToSize() {
        // GOOD: Correct implementation.
        // MISSING: modCount++ — trimToSize is a structural modification
        // because it replaces the internal array. Iterators in flight
        // should be invalidated.
        if (arr.length != size) {
            E[] newArr = (E[]) new Object[size];
            System.arraycopy(arr, 0, newArr, 0, size);
            arr = newArr;
        }
    }
}