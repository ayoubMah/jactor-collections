# Jactor Collection Framework (JCF)

**Artifact ID:** `jactor-collection`
**Author:** Ayoub El-mahjouby
**License:** MIT

## Overview

The Jactor Collection Framework is a clean-room implementation of the standard Java Collections Framework. This project is not merely a usage exercise; it is an architectural deconstruction of core data structures.

The primary objective is to engineer a production-grade library that enforces strict adherence to:
1.  **Time Complexity:** Verifying asymptotic performance ($O(1)$, $O(\log n)$, $O(n)$).
2.  **Memory Efficiency:** Optimizing internal storage layout and load factors.
3.  **Type Safety:** rigorous implementation of Java Generics and variance.
4.  **API Contract:** Full compliance with standard `java.util` behavioral specifications (e.g., fail-fast iterators, null handling).

## Architectural Design

The project follows a strict hierarchy to separate interfaces from implementation details, maximizing code reuse through skeletal implementations.

```text
jactor/
├── src/main/java/ayoub/
│   ├── interfaces/          # Contract definitions (MyList, MyMap)
│   ├── abstracts/           # Skeletal implementations (MyAbstractList)
│   ├── list/                # Linear structures (ArrayList, LinkedList)
│   ├── set/                 # Unique element structures
│   ├── map/                 # Key-Value associative structures
│   ├── queue/               # Processing pipelines (Heaps, Deques)
│   └── util/                # Algorithmic utilities
```

## Implementation Status

### Phase 1: Linear Data Structures (Lists)
Focus: Dynamic resizing, pointer manipulation, and index-based access.

**Interfaces & Abstractions**
- [x] MyCollection<E>: Extends Iterable<E>.
- [x] MyList<E>: Ordered collection contract.
- [x] MyAbstractCollection<E>: Base implementation for common operations.
- [x] MyAbstractList<E>: Base implementation for random-access optimizations.

**implementations**
- [x] MyArrayList<E> (Dynamic Array)
  - [x] Amortized $O(1)$ append via dynamic resizing (growth strategy).
  - [x] $O(1)$ random access (get, set).
  - [x] $O(n)$ insertion/deletion at arbitrary indices (referential shifting).
  - [x] Iterator implementation.
  - [ ] Fail-fast modification checks (ConcurrentModificationException).
- [x] MyLinkedList<E> (Doubly Linked List)
  - [x] Node architecture (prev, data, next).
  - [x] $O(1)$ insertion/deletion at known references.
  - [x] $O(n)$ access time.
  - [ ] Deque interface compliance.

### Phase 2: Queues & Deques
Focus: FIFO/LIFO ordering and circular buffer logic.
- [ ] MyQueue<E> & MyDeque<E> interfaces.
- [ ] MyAbstractQueue<E> implementation.
- [ ] MyArrayDeque<E>
  - [ ] Circular array implementation (head/tail bitwise wrapping).
  - [ ] No null elements allowed.

### Phase 3: Associative Arrays (Hash Maps)
Focus: Hashing algorithms, collision resolution, and load factor management.
- [ ] MyMap<K, V> interface.
- [ ] MyAbstractMap<K, V> implementation.
- [ ] MyHashMap<K, V>
  - [ ] Bucket array implementation.
  - [ ] hashCode() spreading functions (XOR shifting).
  - [ ] Collision resolution: Separate Chaining (Linked Nodes).
  - [ ] Dynamic rehashing when Load Factor threshold is breached.
- [ ] MyLinkedHashMap<K, V>
  - [ ] Hybrid structure: Hash Table + Doubly Linked List for insertion order.

### Phase 4: Sets (Uniqueness)
Focus: Decorator pattern over Maps.
- [ ] MySet<E> interface.
- [ ] MyHashSet<E>
  - [ ] Adapter pattern wrapping MyHashMap (Present/Dummy value objects).
- [ ] MyLinkedHashSet<E>
  - [ ] Adapter pattern wrapping MyLinkedHashMap.

### Phase 5: Balanced Trees & Sorting
Focus: Maintaining sorted order and logarithmic time complexity.
- [ ] MySortedMap<K, V> & MySortedSet<E> interfaces.
- [ ] MyTreeMap<K, V> (Red-Black Tree)
  - [ ] Strict $O(\log n)$ for get, put, remove.
  - [ ] Self-balancing operations (Left/Right Rotations, Recoloring).
- [ ] MyTreeSet<E>
  - [ ] Navigable set implementation backed by MyTreeMap.

### Phase 6: Heaps & Priority
Focus: Binary Heap properties.
- [ ] MyPriorityQueue<E>
  - [ ] Array-based Binary Heap.
  - [ ] Sift-Up/Sift-Down percolation logic.
  - [ ] Natural ordering or Comparator support.

## Engineering Standards

All contributions or implementations must adhere to the following standards:

### 1. Complexity Verification
Every method must be documented with its Time and Space complexity.
Example: `get(int index)` in MyArrayList must be $O(1)$.

### 2. Testing Protocol
The project utilizes JUnit 5 for rigorous verification.
- **Coverage:** Minimum 80% line coverage.
- **Boundary Testing:** Explicit tests for 0, 1, MAX_VALUE, and capacity thresholds.
- **Safety:** Tests for NullPointerException and IndexOutOfBoundsException.

### 3. Code Style
- No magic numbers.
- Clear variable nomenclature (e.g., capacity, size, threshold).
- Strict generic typing (avoid raw types).

## Build Instructions

**Prerequisites:** Java 17+, Maven 3.8+

```bash
# Clone the repository
git clone https://github.com/ayoubMah/mycollections.git

# Clean build and run all unit tests
mvn clean install

# Generate JaCoCo coverage report
mvn jacoco:report
```