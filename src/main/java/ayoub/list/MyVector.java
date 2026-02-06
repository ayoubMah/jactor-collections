package ayoub.list;

import ayoub.abstracts.MyAbstractList;
import ayoub.collections.MyCollection;

import java.util.Iterator;
import java.util.Objects;

public class MyVector<E> extends MyAbstractList<E> {

    protected int capacityIncrement = 10; // by default
    protected int elmCount;
    protected E[] elmData;

    public MyVector(){
        elmData = (E[]) new Object[capacityIncrement];
        elmCount = 0;
    }

    public MyVector(int intialCapacity){
        elmData = (E[]) new Object[intialCapacity];
        elmCount = 0;
    }

    public MyVector(MyCollection<? extends E> c){
        MyVector<E> vector = new MyVector<>();
        for (E elm : c){
            vector.add(elm);
        }
        elmCount++;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elmData[index];
    }

    @Override
    public E set(int index, E elm) {
        rangeCheck(index);
        E old = elmData[index];
        elmData[index] = elm;
        return old;
    }

    public boolean add(E elm) {
        ensureCapacity(elmCount + 1);
        elmData[elmCount++] = elm;
        return true;
    }

    @Override
    public void add(int index, E elm) {
        rangeCheck(index);
        ensureCapacity(elmCount + 1);
        System.arraycopy(elmData, index, elmData, index+1, elmCount-index); // length:  the number of array elements to be copied see @javadoc
        elmData[index] = elm;
        elmCount++;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E elm = elmData[index];
        System.arraycopy(elmData, index + 1, elmData, index, elmCount-index);
        elmData[--elmCount] = null;
        return elm;
    }

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = elmCount;
        for (int i = lastIndex; i >= 0; i--){
            if (Objects.equals(o, elmData[i])) return i;
        }
        return -1;
    }

    @Override
    public boolean addAll(int index, MyCollection<? extends E> c) {
        rangeCheck(index);
        ensureCapacity(c.size());
        for (E elm : c){
            add(elm);
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor < elmCount;
            }

            @Override
            public E next() {
                return elmData[cursor++];
            }

            @Override
            public void remove(){
                MyVector.this.remove(--cursor);
            }
        };
    }

    @Override
    public int size() {
        return elmCount;
    }

    private void grow(int newCapacity) {
        E[] newArr = (E[]) new Object[newCapacity];
        System.arraycopy(elmData,0, newArr, 0, elmCount);
        elmData = newArr;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elmData.length){
            int newCap = 2 * elmCount; // growth 2X
            if (newCap < minCapacity) newCap = minCapacity;
            grow(newCap);
        }
    }

    private void rangeCheck(int index) {
        if (index < 0 || index > elmCount){
            throw new IndexOutOfBoundsException("index: "+index + ", size: "+elmCount);
        }
    }
}
