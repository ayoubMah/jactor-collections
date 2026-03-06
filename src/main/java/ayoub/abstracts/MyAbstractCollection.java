package ayoub.abstracts;
// ============================================================
// MyAbstractCollection.java
// ============================================================

import ayoub.collections.MyCollection;

import java.util.Iterator;

public abstract class MyAbstractCollection<E> implements MyCollection<E> {

    public abstract Iterator<E> iterator();
    public abstract int size();

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E elm = it.next();
            // HINT: Objects.equals already handles null-safety — simplify this
            if (elm == null ? o == null : elm.equals(o)) {
                return true;
            }
        }
        return false;
    }

    public Object[] toArray(){
        Iterator<E> it = iterator();
        int len = this.size();
        Object[] arr = new Object[len];
        for (int i = 0; i < len; i++){
            // CONCERN: What if the collection is modified between size() and here?
            // The iterator might have fewer or more elements than `len`.
            // HINT: Look at how AbstractCollection handles this with a growing
            // buffer. What data structure would let you append without knowing size upfront?
            arr[i] = it.next();
        }
        return arr;
    }

    public boolean remove(Object o){
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E elm = it.next();
            // HINT: same as contains() — simplify with Objects.equals
            if (elm == null ? o == null : elm.equals(o)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void clear(){
        Iterator<E> it = iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
        // CONCERN: This is correct for mutable collections, but think ahead:
        // When you introduce immutable variants, clear() makes no sense.
        // QUESTION: Should clear() even exist on MyCollection interface?
        // Or should it live only on a MutableCollection interface?
        // This is the interface segregation problem you'll need to solve soon.
    }

    @Override
    public String toString() {
        // HINT: This is fine for now, but consider: what if an element
        // is a reference to this collection itself? (circular reference)
        // The JDK handles this — search for "ARRAY_SENTINEL" in AbstractCollection.
        // Not urgent, but worth knowing.
        StringBuilder sb = new StringBuilder("[");
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}