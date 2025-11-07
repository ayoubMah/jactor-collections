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
