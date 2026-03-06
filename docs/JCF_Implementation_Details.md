#  Implementation Details

## `MyArrayList<E>`
Dynamic array-based list implementation.

### Fields
```java
private E[] arr;
private int size;
private static final int DEFAULT_CAPACITY = 10;

## Core Methods

| Method                      | Description                      |
| --------------------------- | -------------------------------- |
| `int size()`                | Returns number of elements       |
| `E get(int index)`          | Retrieves element at index       |
| `E set(int index, E e)`     | Replaces and returns old value   |
| `void add(int index, E e)`  | Inserts element and shifts right |
| `boolean add(E e)`          | Adds element at end              |
| `E remove(int index)`       | Removes element and shifts left  |
| `int indexOf(Object o)`     | Finds first occurrence           |
| `int lastIndexOf(Object o)` | Finds last occurrence            |
| `Iterator<E> iterator()`    | Returns internal iterator        |

## Design Considerations & Refactoring Review

### 1. Fail-Fast Iterators
- **`modCount`**: Every structural modification (add, remove, clear, trimToSize) must increment a `modCount` field.
- **Concurrent Modification**: The iterator stores an `expectedModCount` and checks it at each `next()` and `remove()` call.
- **Status**: Identified as a missing feature in `MyArrayList` review.

### 2. Thread-Safety
- **Current Policy**: All current classes are `@NotThreadSafe`.
- **Concurrency**: External synchronization is required for concurrent access.
- **Future**: Synchronized variants (like `MyVector`) or concurrent-aware versions (using `volatile` or `AtomicInteger`) are planned for Phase 5.

### 3. Overflow Protection
- **Resize Limit**: Dynamic array growth must check against `MAX_ARRAY_SIZE` (typically `Integer.MAX_VALUE - 8`).
- **Bitwise Growth**: `newCap = oldCap + (oldCap >> 1)` can overflow to a negative number if not guarded.

### 4. Interface Segregation (Future Design)
- **Problem**: `MyCollection` combines read and write operations.
- **Idea**: Split into `MyCollection` (Read-only) and `MyMutableCollection` (Write operations) to better support immutable collections.
- **Status**: Under evaluation for v0.1.0.

### 5. Null Safety & Equality
- Prefer `Objects.equals(a, b)` for element comparisons to ensure null-safety and cleaner code.
