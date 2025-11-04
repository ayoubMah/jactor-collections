package ayoub.abstracts;

import ayoub.collections.MyCollection;
import ayoub.collections.MyList;

import java.util.Iterator;
import java.util.ListIterator;

public abstract class MyAbstractSequentialList<E> extends MyAbstractList<E> implements Iterable<E>, MyCollection<E>, MyList<E> {

    // abstract methods
    // todo : later i'll imp imp ListIterator<E> for now just import it
    ListIterator<E> listIterator = new ListIterator<E>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public E next() {
            return null;
        }

        @Override
        public boolean hasPrevious() {
            return false;
        }

        @Override
        public E previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return 0;
        }

        @Override
        public int previousIndex() {
            return 0;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    };

   // concrete methods
   public void add(int index, E elm) {

   }

   public boolean addAll(int index, MyCollection<? extends E> c) {
       return true;
   }

   public E get(int index){
       return null;
   }

   public Iterator<E> iterator() {
       return null;
   }

   public E remove(int index) {
       return null;
   }

   public E set(int index, E elm){
       return null; // the prev elm that was in the same index
   }


}
