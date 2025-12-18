package ayoub.list;


import ayoub.abstracts.MyAbstractList;
import ayoub.collections.MyCollection;

import java.util.Iterator;

public class MyArrayList<E> extends MyAbstractList<E> {

    private static final int DEFAULT_CAPACITY = 10;
    private E[] arr;
    private int size;

    public MyArrayList(){
        arr = (E[])new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    // if i want to create an instance of MyArrayList with an explicit capacity
    public MyArrayList(int initialCapacity){
        arr = (E[]) new Object[initialCapacity];;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("index: "+index + ", size: "+size);
        }
    }

    // this is the magic behind dynamic list
    // it's just creating a new array with a length bigger then the first one by 50%
    // and then copy/paste the odl elms of the prev array to the new one
    // we use arraycopy for better performance
    private void grow(int newCapacity){
        E [] newArr = (E[]) new Object[newCapacity];
        System.arraycopy(arr,0,newArr,0,size);
        arr = newArr; // replace the old arr with the new one
    }


    public void ensureCapacity(int minCapacity) {
        if (minCapacity > arr.length) {
            int newCap = arr.length + (arr.length >> 1);
            if (newCap < minCapacity) newCap = minCapacity;
            grow(newCap);
        }
    }

    @Override
    public E get(int index) {
        // before we return it -> we should verify if the index not out of the array
        rangeCheck(index);
        return arr[index];
    }

    // return the prev elm that was reserved in arr[index]
    @Override
    public E set(int index, E elm) {
        rangeCheck(index);
        E prevElm = arr[index];
        arr[index] = elm;
        return prevElm;
    }

    @Override
    public void add(int index, E elm) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();
        ensureCapacity(size+1); // the minimum capacity accepted to add one elm is size+1
        System.arraycopy(arr,index,arr,index+1,size-index);
        arr[index] = elm;
        size++;
    }

    @Override
    public boolean add(E elm) {
        ensureCapacity(size + 1);
        arr[size++] = elm;
        return true;
    }

    // we should get a new array with size - 1 as length
    // the method return the removed elm
    @Override
    public E remove(int index) {
        rangeCheck(index);
        E removed = arr[index];
        System.arraycopy(arr,index+1,arr,index,size-index-1);
        arr[--size] = null; // or arr[size -1] = null; size --;
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++){
            if (arr[i] == null ? o == null : arr[i].equals(o)) return i;
        }
        return -1;
    }

    // todo
    @Override
    public boolean addAll(int index, MyCollection<? extends E> c) {
        return false;
    }

    // return last index of the last occurrence of a specific elm
    // and -1 if not exist
    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = size;
        for (int i = lastIndex; i >= 0 ; i--){
            if (arr[i] == null ? o == null : arr[i].equals(o)) return i;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public E next() {
                return arr[cursor++];
            }

            @Override
            public void remove(){
                MyArrayList.this.remove(--cursor);
            }
        };
    }

    @Override
    public void trimToSize(){
        if(arr.length != size){
            E [] newArr = (E[]) new Object [size];
            System.arraycopy(arr,0,newArr,0,size);
            arr = newArr;
        }
    }
}