# == MyCollection Framework - Roadmap ==

##  Phase 1 — Core Lists (v0.0.x)
Goal: Build the foundation and list implementations.

| Component | Status | Notes |
|------------|---------|-------|
| `MyCollection<E>` | ✅ Done | Base interface |
| `MyList<E>` | ✅ Done | List interface |
| `AbstractMyCollection<E>` | ✅ Done | Shared logic |
| `AbstractMyList<E>` | ✅ Done | Abstract list features |
| `MyArrayList<E>` | ✅ Done | Dynamic array |
| `MyArrayListTest` | ✅ Done | Add JUnit tests |
| `MyLinkedList<E>` | 🚧 In Progress | Node-based list |
| `MyLinkedListTest` | 🧪 Planned |  |

###  v0.0.2 Milestone Goals
- Write complete JUnit 5 tests for `MyArrayList`
- Fix small internal issues (iterator fail-fast, lastIndexOf)
- Add `trimToSize()` and `subList()` methods
- Begin `MyLinkedList` implementation (basic CRUD)
- Write README usage examples

###  v0.0.3 In Progress
- Implemented MyLinkedList<E> (doubly linked)
- Added LinkedListIterator
- Added MyLinkedListTest (unit coverage)


---

##  Phase 2 — Sets & Queues (v0.1.x)
Goal: Introduce `MySet`, `MyQueue`, and linked implementations.

- `MySet<E>` + `MyAbstractSet<E>`
- `MyHashSet<E>` (using internal `MyHashMap`)
- `MyQueue<E>` + `MyArrayDeque<E>`
- `MyLinkedList` implements `MyDeque<E>`

---

##  Phase 3 — Maps (v0.2.x)
Goal: Key–Value storage system.

- `MyMap<K,V>` + `MyAbstractMap<K,V>`
- `MyHashMap<K,V>` (Separate chaining)
- `MyLinkedHashMap<K,V>` (Ordered)
- Iterators for keySet, values, entrySet

---

##  Phase 4 — Sorted Collections (v0.3.x)
Goal: Implement tree-based structures.

- `MyTreeMap<K,V>` (Red-Black Tree)
- `MyTreeSet<E>` (backed by `MyTreeMap`)
- `MyComparator<T>` interface
- Introduce an inner SubList class (backed by parent list). 
- Implement fail-fast behavior (modCount check). 
- Upgrade MyArrayList.subList() to return that view.

---

##  Phase 5 — Utilities & Finalization (v0.5.x → v1.0.0)
Goal: Utilities, documentation, and publication.

- `MyObjects` and `MyCollections` helpers
- Javadoc documentation
- Code coverage > 80%
- Maven Central publication

---

##  Long-term Ideas
- Streams & Lambdas support
- Concurrency-safe collections (`MyVector`, `MyHashtable`)
- Benchmark suite vs Java’s JCF
- GUI visualization of internal structures
