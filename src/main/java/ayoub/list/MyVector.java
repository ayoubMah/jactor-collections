package ayoub.list;

import ayoub.abstracts.MyAbstractList;
import ayoub.collections.MyCollection;

import java.util.Iterator;

public class MyVector<E> extends MyAbstractList<E> {

    protected int capacityIncrement = 10; // by default
    protected int elmCount;
    protected Object[] elmData;

    public MyVector(){
        elmData = new Object[capacityIncrement];
        elmCount = 0;
    }

    public MyVector(int intialCapacity){
        elmData = new Object[intialCapacity];
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
        return null;
    }

    @Override
    public E set(int index, E elm) {
        return null;
    }

    public boolean add(E elm) {
        ensureCapacity(elmCount + 1);
        elmData[elmCount++] = elm;
        return true;
    }

    @Override
    public void add(int index, E elm) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public boolean addAll(int index, MyCollection<? extends E> c) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return elmCount;
    }

    private void grow(int newCapacity) {
        Object[] newArr = new Object[newCapacity];
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
}
