# My Java Collection Framework (JCF)

A complete implementation of Java's Collection Framework from scratch - built for deep learning and understanding of data structures, algorithms, and design patterns.

## 🎯 Project Goals

- Master core data structures (arrays, linked lists, hash tables, trees, heaps)
- Understand Java generics deeply
- Learn classic design patterns (Iterator, Template Method, Factory)
- Practice Test-Driven Development (TDD)
- Build production-quality, publishable code

## 📦 Project Structure

```
mycollections/
├── src/main/java/ayoub/Collections/
│   ├── interfaces/
│   ├── abstracts/
│   ├── list/
│   ├── set/
│   ├── queue/
│   ├── map/
│   └── util/
└── src/test/java/ayoub/Collections/
```

## ✅ Implementation Checklist

### Phase 1: Foundation & List Implementations

#### Core Interfaces
- [x] `MyCollection<E>` interface (extends `Iterable<E>`)
- [x] `Iterator<E>` interface (I use just `java.util.Iterator`)
- [x] `MyList<E>` interface (extends `MyCollection<E>`)

#### Abstract Classes
- [x] `MyAbstractCollection<E>` (implements `MyCollection<E>`)
- [x] `MyAbstractList<E>` (extends `MyAbstractCollection`, implements `MyList<E>`)

#### ArrayList Implementation
- [ ] `MyArrayList<E>` class
    - [x] Internal array storage
    - [x] Constructor(s) - default capacity
    - [x] `add(E element)` with dynamic resizing
    - [x] `add(int index, E element)`
    - [x] `get(int index)` with bounds checking
    - [x] `set(int index, E element)`
    - [x] `remove(int index)` with element shifting
    - [x] `remove(Object o)`
    - [x] `size()`, `isEmpty()`, `clear()`
    - [x] `contains(Object o)`
    - [x] `indexOf(Object o)`, `lastIndexOf(Object o)`
    - [x] Inner class: `ArrayListIterator` implementing `Iterator<E>`
    - [x] `iterator()` method
    - [ ] Fail-fast iterator (ConcurrentModificationException)
- [x] `MyArrayListTest` - comprehensive unit tests

#### LinkedList Implementation
- [x] `MyLinkedList<E>` class
    - [x] Node inner class (with `data`, `next`, `prev`)
    - [x] Head and tail references
    - [ ] `add(E element)` - add to end
    - [x] `add(int index, E element)`
    - [ ] `addFirst(E element)`, `addLast(E element)`
    - [x] `get(int index)`
    - [x] `set(int index, E element)`
    - [x] `remove(int index)`
    - [ ] `removeFirst()`, `removeLast()`
    - [ ] `size()`, `isEmpty()`, `clear()`
    - [ ] Inner class: `LinkedListIterator`
    - [x] `iterator()` method
    - [ ] Fail-fast iterator
- [x] `MyLinkedListTest` - comprehensive unit tests

#### Stack Implementation
- [ ] `MyStack<E>` class
    - [ ] `push(E element)`
    - [ ] `pop()`
    - [ ] `peek()`
    - [ ] `isEmpty()`
    - [ ] `size()`
- [ ] `StackTest` - unit tests

---

### Phase 2: Queue & Deque

#### Interfaces
- [ ] `MyQueue<E>` interface (extends `MyCollection<E>`)
- [ ] `MyDeque<E>` interface (extends `Queue<E>`)

#### Abstract Classes
- [ ] `MyAbstractQueue<E>` (extends `MyAbstractCollection`, implements `MyQueue<E>`)

#### Implementations
- [ ] Update `MyLinkedList<E>` to implement `MyDeque<E>`
    - [ ] `offer(E element)`, `poll()`, `peek()`
    - [ ] `offerFirst()`, `offerLast()`
    - [ ] `pollFirst()`, `pollLast()`
    - [ ] `peekFirst()`, `peekLast()`

- [ ] `MyArrayDeque<E>` class
    - [ ] Circular array implementation
    - [ ] All deque operations
    - [ ] Dynamic resizing
- [ ] `MyArrayDequeTest` - unit tests

---

### Phase 3: Map Implementations (Hash-Based)

#### Map Interfaces
- [ ] `MyMap<K, V>` interface (standalone, NOT extending MyCollection)
    - [ ] `put(K key, V value)`
    - [ ] `get(Object key)`
    - [ ] `remove(Object key)`
    - [ ] `containsKey(Object key)`, `containsValue(Object value)`
    - [ ] `size()`, `isEmpty()`, `clear()`
    - [ ] `keySet()`, `values()`, `entrySet()`
- [ ] `MyMap.Entry<K, V>` inner interface

#### Abstract Classes
- [ ] `MyAbstractMap<K, V>` (implements `Map<K, V>`)

#### HashMap Implementation
- [ ] `MyHashMap<K, V>` class
    - [ ] Internal array of buckets (Entry[])
    - [ ] Hash function
    - [ ] Collision resolution (separate chaining with linked list)
    - [ ] `put(K key, V value)` - with collision handling
    - [ ] `get(Object key)` - O(1) average
    - [ ] `remove(Object key)`
    - [ ] `containsKey()`, `containsValue()`
    - [ ] Load factor and rehashing/resizing
    - [ ] `size()`, `isEmpty()`, `clear()`
    - [ ] `keySet()`, `values()`, `entrySet()` views
    - [ ] Inner class: `HashMapEntry<K, V>` (implements `Map.Entry`)
    - [ ] Iterator support for entry set
- [ ] `HashMapTest` - comprehensive unit tests

#### LinkedHashMap Implementation
- [ ] `MyLinkedHashMap<K, V>` class (extends `HashMap`)
    - [ ] Maintains insertion order with doubly-linked list
    - [ ] Override necessary methods
- [ ] `MyLinkedHashMapTest` - unit tests

---

### Phase 4: Set Implementations (Hash-Based)

#### Set Interfaces
- [ ] `MySet<E>` interface (extends `MyCollection<E>`)
    - [ ] No duplicate elements
    - [ ] No index-based access

#### Abstract Classes
- [ ] `MyAbstractSet<E>` (extends `MyAbstractCollection`, implements `MySet<E>`)

#### HashSet Implementation
- [ ] `MyHashSet<E>` class
    - [ ] Internal `MyHashMap<E, Object>` (value is dummy object)
    - [ ] `add(E element)` - returns false if duplicate
    - [ ] `remove(Object o)`
    - [ ] `contains(Object o)` - O(1) average
    - [ ] `size()`, `isEmpty()`, `clear()`
    - [ ] `iterator()`
- [ ] `MyHashSetTest` - unit tests

#### LinkedHashSet Implementation
- [ ] `MyLinkedHashSet<E>` class (extends `MyHashSet`)
    - [ ] Uses `MyLinkedHashMap` internally
    - [ ] Maintains insertion order
- [ ] `MyLinkedHashSetTest` - unit tests

---

### Phase 5: Sorted MyCollections (Tree-Based)

#### Utility Interfaces
- [ ] `MyComparator<T>` interface
    - [ ] `compare(T o1, T o2)`
- [ ] Use `java.lang.Comparable<T>` (built-in)

#### Sorted Interfaces
- [ ] `MySortedMap<K, V>` interface (extends `MyMap<K, V>`)
    - [ ] `firstKey()`, `lastKey()`
    - [ ] `subMap()`, `headMap()`, `tailMap()`
    - [ ] `comparator()`

- [ ] `MySortedSet<E>` interface (extends `MySet<E>`)
    - [ ] `first()`, `last()`
    - [ ] `subSet()`, `headSet()`, `tailSet()`
    - [ ] `comparator()`

#### TreeMap Implementation
- [ ] `MyTreeMap<K, V>` class (Red-Black Tree)
    - [ ] Node inner class (key, value, color, left, right, parent)
    - [ ] `put(K key, V value)` with BST insertion + balancing
    - [ ] Red-Black Tree rotations (left, right)
    - [ ] Red-Black Tree recoloring
    - [ ] `get(Object key)` - O(log n)
    - [ ] `remove(Object key)` with rebalancing
    - [ ] In-order traversal for sorted iteration
    - [ ] `firstKey()`, `lastKey()`
    - [ ] `comparator()` support
    - [ ] Iterator with in-order traversal
- [ ] `MyTreeMapTest` - unit tests

#### TreeSet Implementation
- [ ] `MyTreeSet<E>` class
    - [ ] Internal `MyTreeMap<E, Object>` (value is dummy)
    - [ ] `add(E element)` - maintains sorted order
    - [ ] `remove(Object o)` - O(log n)
    - [ ] `contains(Object o)` - O(log n)
    - [ ] `first()`, `last()`
    - [ ] `iterator()` returns sorted iterator
- [ ] `MyTreeSetTest` - unit tests

---

### Phase 6: Priority Queue (Heap-Based)

#### PriorityQueue Implementation
- [ ] `MyPriorityQueue<E>` class
    - [ ] Internal array-based binary heap
    - [ ] Min-heap or max-heap (with Comparator support)
    - [ ] `offer(E element)` - heapify up
    - [ ] `poll()` - remove min/max, heapify down
    - [ ] `peek()` - view min/max without removing
    - [ ] `size()`, `isEmpty()`, `clear()`
    - [ ] Comparator support
    - [ ] Dynamic resizing
- [ ] `MyPriorityQueueTest` - unit tests

---

### Phase 7: Legacy MyCollections (Optional)

- [ ] `MyVector<E>` class (synchronized ArrayList)
    - [ ] All ArrayList operations
    - [ ] Synchronized methods
- [ ] `MyVectorTest` - unit tests

- [ ] `MyHashtable<K, V>` class (synchronized HashMap)
    - [ ] All HashMap operations
    - [ ] Synchronized methods
    - [ ] No null keys or values
- [ ] `MyHashtableTest` - unit tests

---

### Phase 8: Utility Classes

- [ ] `MyObjects` utility class
    - [ ] `equals(Object a, Object b)` null-safe
    - [ ] `hashCode(Object o)` null-safe
    - [ ] `requireNonNull(T obj)`

- [ ] `MyCollections` utility class (optional advanced)
    - [ ] `sort(List<T> list)`
    - [ ] `reverse(List<?> list)`
    - [ ] `shuffle(List<?> list)`
    - [ ] `binarySearch(List<T> list, T key)`
    - [ ] `min()`, `max()`

---

### Phase 9: Documentation & Publishing

- [ ] Complete Javadoc for all public APIs
- [ ] Add usage examples in README
- [ ] Code coverage report (JaCoCo) - aim for 80%+
- [ ] Performance benchmarks (optional)
- [ ] Package for Maven Central (optional)

---

## 🧪 Testing Strategy

- **Unit tests for every class** using JUnit 5
- **Test coverage**: Aim for 80%+ code coverage
- **Test categories**:
    - Basic operations (add, remove, get)
    - Edge cases (empty, single element, full capacity)
    - Boundary conditions (index out of bounds, null handling)
    - Iterator behavior (hasNext, next, fail-fast)
    - Concurrency (ConcurrentModificationException)

---

## 📚 Learning Resources

### When You Get Stuck

**Generics**:
- Oracle Java Generics Tutorial
- Effective Java (Joshua Bloch) - Chapter on Generics

**Iterator Pattern**:
- Gang of Four Design Patterns book
- Refactoring.guru - Iterator Pattern

**Hashing**:
- Introduction to Algorithms (CLRS) - Chapter 11
- Practice: Understand hash functions, collision resolution

**Red-Black Trees**:
- Visualgo.net - visualize rotations
- Introduction to Algorithms (CLRS) - Chapter 13
- GeeksforGeeks - Red-Black Tree tutorials

**Binary Heaps**:
- Introduction to Algorithms (CLRS) - Chapter 6
- Visualgo.net - heap visualization

---

## 🏆 Success Criteria

- [ ] All interfaces and classes implemented
- [ ] All tests passing
- [ ] Code coverage > 80%
- [ ] Clean, readable code following Java conventions
- [ ] Complete Javadoc documentation
- [ ] README with usage examples
- [ ] Project builds successfully with Maven
- [ ] (Optional) Published to Maven Central

---

## 🚀 Getting Started

```bash
# Clone the repository
git clone https://github.com/ayoubMah/mycollections.git

# Build the project
mvn clean install

# Run tests
mvn test

# Generate coverage report
mvn jacoco:report
```

---

## 📝 Notes

- **Start simple**: ArrayList → LinkedList → HashMap → HashSet → TreeMap → TreeSet
- **Test-driven development**: Write tests first, then implement
- **Don't skip abstracts**: MyAbstractCollection and MyAbstractList save you tons of code
- **Iterator is crucial**: Fail-fast behavior is important for correctness
- **HashMap before HashSet**: HashSet is just a HashMap wrapper!
- **TreeMap before TreeSet**: TreeSet is just a TreeMap wrapper!

---

## 📄 License

MIT License - feel free to use and learn from this project

---
## ✍️ Author

**Ayoub El-mahjouby**
[GitHub Profile](https://github.com/ayoubMah)
