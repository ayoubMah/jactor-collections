package ayoub.list;

// ============================================================
// MyLinkedList.java — Refactor Review
// ============================================================

// MISSING: Class-level threading policy documentation.
// @NotThreadSafe
// This class is not thread-safe. All fields (head, tail, size)
// are unsynchronized. See java.util.concurrent.ConcurrentLinkedDeque
// for a lock-free thread-safe variant.

// GOOD: Sentinel nodes (dummy head/tail) — excellent design choice.
// This eliminates null checks on every add/remove operation.
// This is exactly what java.util.LinkedList does internally.
// It simplifies edge cases (empty list, insert at head/tail).

import ayoub.abstracts.MyAbstractList;
import ayoub.collections.MyCollection;

import java.util.Iterator;

public class MyLinkedList<E> extends MyAbstractList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    // MISSING: modCount — same issue as MyArrayList.
    // Every structural modification must increment it.
    // protected int modCount = 0;

    public MyLinkedList() {
        head = new Node<>(null, null, null);
        tail = new Node<>(head, null, null);
        head.setNext(tail);
    }

    private Node<E> nodeAt(int index) {
        // CONCERN: Wrong boundary — should be size-1 for element access,
        // not size. You're allowing nodeAt(size) which points to tail sentinel.
        // This is the same rangeCheck vs rangeCheckForAdd confusion from MyArrayList.
        // HINT: Should nodeAt() ever return a sentinel node?
        // If not, the bound should be:
        //   if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        // GOOD: Bidirectional traversal optimization — O(n/2) average.
        // This is exactly what the JDK's LinkedList.node(int) does.
        // CONCERN: Integer cast is redundant — size is already an int.
        if (((int) size / 2) > index) {
            Node<E> current = head.getNext();
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current;
        }
        Node<E> current = tail.getPrev();
        for (int i = size - 1; i > index; i--) {
            current = current.getPrev();
        }
        return current;
    }

    @Override
    public E get(int index) {
        // GOOD: Delegates to nodeAt cleanly.
        Node<E> node = nodeAt(index);
        return node.getData();
    }

    @Override
    public E set(int index, E elm) {
        Node<E> current = nodeAt(index);
        E old = current.getData();
        current.setData(elm);
        // QUESTION: Should set() increment modCount here?
        // Same question as in MyArrayList — what does the JDK decide?
        return old;
    }

    @Override
    public void add(int index, E elm) {
        // CONCERN: This special-cases index==size to call add(elm).
        // But add(elm) rejects null elements (see below).
        // So add(size, null) would be silently rejected via add(elm),
        // but add(0, null) on a non-empty list would go through the
        // else branch and succeed. Inconsistent null behavior.
        if (index == size) {
            add(elm);
            return;
        }
        Node<E> current = nodeAt(index);
        Node<E> currentPrev = current.getPrev();
        Node<E> newNode = new Node<>(currentPrev, elm, current);
        currentPrev.setNext(newNode);
        current.setPrev(newNode);
        size++;
        // MISSING: modCount++
    }

    @Override
    public boolean add(E elm) {
        // DESIGN CONCERN: You are rejecting null elements here.
        // java.util.LinkedList explicitly ALLOWS nulls.
        // This is a silent failure — you return false instead of throwing.
        // If this is intentional (null-free list), document it clearly.
        // If not intentional, remove this check entirely.
        // QUESTION: What's worse — silently ignoring a null add,
        // or throwing NullPointerException? Think about the principle
        // of least surprise for API callers.
        if (elm == null) {
            System.out.println("we can't add null ?");
            // BUG: System.out in library code — same issue as before.
            // Library code must never print to stdout.
            return false;
        }
        Node<E> currentLastElm = tail.getPrev();
        Node<E> newLastElm = new Node<>(currentLastElm, elm, tail);
        tail.setPrev(newLastElm);
        currentLastElm.setNext(newLastElm);
        size++;
        // MISSING: modCount++
        return true;
    }

    @Override
    public boolean addAll(int index, MyCollection<? extends E> c) {
        // GOOD: Predecessor/successor pattern is correct and efficient.
        // You avoid O(n) re-traversal by threading nodes in one pass.
        // This is exactly the right approach.

        int inc = c.size();

        // CONCERN: What if c is empty? inc==0, loop doesn't execute,
        // but you still do successor.setPrev(predecessor) where
        // predecessor == successor.getPrev() already. It's harmless
        // but you should return false if nothing was added.
        // HINT: if (inc == 0) return false;

        Node<E> successor = (index == size) ? tail : nodeAt(index);
        Node<E> predecessor = successor.getPrev();
        Iterator<E> it = (Iterator<E>) c.iterator();

        for (int i = 0; i < c.size(); i++) {
            // CONCERN: You're calling c.size() on every iteration.
            // If c is a live collection being modified concurrently,
            // this could loop forever or skip elements.
            // HINT: Capture inc once (you already did) — use that:
            //   for (int i = 0; i < inc; i++)
            Node<E> node = new Node<E>(null, it.next(), null);
            predecessor.setNext(node);
            node.setPrev(predecessor);
            predecessor = node;
        }
        successor.setPrev(predecessor);
        predecessor.setNext(successor);
        size += inc;
        // MISSING: modCount++
        return true;
    }

    @Override
    public E remove(int index) {
        Node<E> target = nodeAt(index);
        Node<E> prev = target.getPrev();
        Node<E> next = target.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        target.setPrev(null);
        target.setNext(null);
        size--;
        // MISSING: modCount++
        // GOOD: Nulling out prev/next helps GC — correct instinct.
        return target.getData();
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        // CONCERN: Returning null on empty is a bad API pattern.
        // It forces every caller to null-check the return value.
        // The JDK throws NoSuchElementException instead.
        // HINT: What's the difference between removeFirst() and pollFirst()?
        // One throws, one returns null. Both have a place — but be explicit.
        Node<E> target = head.getNext();
        Node<E> newFirst = target.next;
        // CONCERN: Accessing target.next directly instead of target.getNext().
        // You have getters — use them consistently, or don't have them.
        target.setPrev(null);
        target.setNext(null);
        head.setNext(newFirst);
        newFirst.setPrev(head);
        size--;
        // MISSING: modCount++
        return target.getData();
    }

    public E removeLast() {
        if (isEmpty()) return null;
        Node<E> target = tail.getPrev();
        Node<E> newLast = target.next;
        // BUG: target.next here should be target.getPrev().
        // The new last element is the one BEFORE target, not after.
        // Think about it: target is the current last real node.
        // After removing it, the new last is target.getPrev().
        // Then: tail.setPrev(newLast), newLast.setNext(tail).
        // Draw it out if unsure.
        target.setPrev(null);
        target.setNext(null);
        tail.setPrev(newLast);
        newLast.setNext(tail);
        size--;
        return target.getData();
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> current = tail.getPrev();
        for (int i = size - 1; i >= 0; i--) {
            // CONCERN: Null check is on current (the node), not current.getData().
            // The node itself should never be null (sentinels prevent that).
            // What you want to check is current.getData() == null.
            // HINT: Use Objects.equals(current.getData(), o) here,
            // same as you did in MyArrayList.indexOf().
            if (current == null ? o == null : current.getData().equals(o)) {
                return i;
            }
            current = current.getPrev();
        }
        return -1;
    }

    @Override
    public int indexOf(Object o) {
        // MISSING: You have no indexOf() override here.
        // You're inheriting the broken one from MyAbstractList
        // that calls get(i).equals(o) and will NPE on null data.
        // Add an override here using Objects.equals(current.getData(), o)
        // and traverse via node references — not via get(i) which
        // costs O(n/2) per call making indexOf O(n²) overall.
        return super.indexOf(o); // <-- this is O(n²), fix it
    }

    public void addBetween(Node<E> prec, E elm, Node<E> successor) {
        // CONCERN: This is public. You're exposing internal Node references
        // in your public API. A caller could pass arbitrary nodes from
        // another list instance — silent corruption.
        // HINT: This should be private. addFirst/addLast are the public API.
        Node<E> node = new Node<>(prec, elm, successor);
        prec.setNext(node);
        successor.setPrev(node);
        size++;
        // MISSING: modCount++
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> current = head.getNext();

            // MISSING: modCount snapshot for fail-fast behavior.
            // int expectedModCount = modCount;
            // Check it at the start of next() and remove().

            // MISSING: lastReturned node reference — needed for remove().
            // Node<E> lastReturned = null;

            @Override
            public boolean hasNext() {
                return current != tail;
            }

            @Override
            public E next() {
                // MISSING: Check for ConcurrentModificationException.
                // MISSING: NoSuchElementException if current == tail.
                E data = current.getData();
                // HINT: Save current to lastReturned before advancing:
                //   lastReturned = current;
                //   current = current.getNext();
                current = current.getNext();
                return data;
            }

            @Override
            public void remove() {
                // TODO is fine for now but here's what you need:
                // 1. Check lastReturned != null (throw IllegalStateException if null)
                // 2. Check expectedModCount == modCount
                // 3. Unlink lastReturned from the list
                // 4. Set lastReturned = null
                // 5. Update expectedModCount = modCount (after the structural mod)
                throw new UnsupportedOperationException("remove not yet supported");
            }
        };
    }

    @Override
    public int size() {
        return size;
    }


    // ============================================================
    // Inner class Node<E>
    // ============================================================

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E data, Node<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }

        public E getData() { return data; }

        public E setData(E newData) {
            E oldOne = getData();
            this.data = newData;
            return oldOne;
        }

        public Node<E> getNext() { return next; }
        public Node<E> getPrev() { return prev; }

        public Node<E> setNext(Node<E> newNext) {
            Node<E> old = next;
            // CONCERN: This conditional is unnecessary.
            // setNext should always set next = newNext and return old.
            // The null check only skips the assignment when next IS null,
            // which means setting next to something after it was null
            // silently does nothing. That's a bug waiting to happen.
            // HINT: Simplify to:
            //   Node<E> old = this.next;
            //   this.next = newNext;
            //   return old;
            if (next != null) {
                this.next = newNext;
                return old;
            }
            this.next = newNext;
            return null;
        }

        // SAME CONCERN applies to setPrev — same fix needed.
        public Node<E> setPrev(Node<E> newPrev) {
            Node<E> old = prev;
            if (prev != null) {
                this.prev = newPrev;
                return old;
            }
            this.prev = newPrev;
            return null;
        }
    }
}