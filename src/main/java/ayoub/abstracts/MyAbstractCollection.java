package ayoub.abstracts;

import ayoub.collections.MyCollection;


import java.util.Iterator;

public abstract class MyAbstractCollection<E> implements MyCollection<E> {


    // this 2 methods are abst cuz they'll be different in imp for each collection: list, linkedlist ...
    public abstract Iterator<E> iterator();
    public abstract int size();

    // imp
    public boolean isEmpty() {
        return size() == 0;
    }

    //TODO : contains(Object o)
    public boolean contains(Object o) {
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E elm = it.next();
            if (elm == null ? o == null : elm.equals(o)) { // we can do it with Objects.equals(elm, o)
                return true;
            }
        }
        return false;
    }

    // TODO: toArray()
    public Object[] toArray(){
        Iterator<E> it = iterator();
        int len = this.size();
        Object[] arr = new Object[len];
        for (int i = 0; i < len; i++){
            arr[i] = it.next();
        }
        return arr;
    }

    //TODO: remove(Object o)
    public boolean remove(Object o){
        // remove the first elm in case we have duplicate elms
        // like col = [1,2,3,3,4,2] => col.remove(2) => col = [1,3,3,4,2]
        // even it's a null elm, we apply the same logic
        // return false if not exist in the col
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            E elm = it.next();
            if (elm == null ? o == null : elm.equals(o)) {
                it.remove(); // the remove func provided by Iterator interface
                return true;
            }
        }
        return false;
    }

    //TODO: clear()
    public void clear(){
        Iterator<E> it = iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
    }


    @Override
    public String toString() {
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