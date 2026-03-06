package ayoub.collections;
// ============================================================
// MyCollection.java
// ============================================================

// CRITICAL DESIGN QUESTION before reading further:
// This single interface declares BOTH read operations (contains, size, isEmpty)
// AND write operations (add, remove, clear).
// When you implement ImmutableArrayList, what will you do with add/remove/clear?
// If you throw UnsupportedOperationException — you've already lost.
// That's what the JDK did, and it's considered a design mistake.
// HINT: Look up "Interface Segregation Principle" and then look at
// how Guava splits ImmutableCollection from Collection.
// The question to answer: should you split this into
// MyCollection (read-only) + MyMutableCollection (adds write ops)?

public interface MyCollection<E> extends Iterable<E> {

    // --- WRITE OPERATIONS ---

    // CONCERN: These three methods assume mutability.
    // An immutable collection cannot honestly implement them.
    // HINT: What if MyCollection only declared the read-only contract,
    // and a sub-interface MyMutableCollection added these?
    // Then ImmutableList implements MyCollection,
    // and MyArrayList implements MyMutableCollection.
    boolean add(E e);
    boolean remove(Object o);
    void clear();

    // --- READ OPERATIONS ---

    // These are fine on any collection, mutable or not.
    boolean contains(Object o);
    int size();
    boolean isEmpty();
    Object[] toArray();

    // MISSING: equals() and hashCode() contracts.
    // The JDK's Collection interface doesn't declare them either —
    // but AbstractCollection inherits them from Object.
    // QUESTION: Should two collections with the same elements
    // always be equal? Does that depend on the collection type?
    // Think: is new ArrayList<>(List.of(1,2,3)).equals(new LinkedList<>(List.of(1,2,3))) true?
    // Why or why not? What does this tell you about where equals/hashCode belong?

    // MISSING: A static factory hint for later.
    // Java 9+ added List.of(), Set.of(), Map.of() as static interface methods.
    // QUESTION: When you're ready for immutable variants,
    // would you put MyList.of(E... elements) here as a static method?
    // What are the tradeoffs vs a separate ImmutableList.of() factory?

    // NOTE ON YOUR COMMENTED-OUT METHODS:
    // addAll, removeAll, containsAll are all bulk operations.
    // HINT: When you uncomment addAll — its signature
    // MyCollection<? extends E> uses a bounded wildcard.
    // Make sure you understand WHY it's <? extends E> and not just <E>
    // before you implement it. This is the classic PECS rule —
    // Producer Extends, Consumer Super. Can you explain it?
}