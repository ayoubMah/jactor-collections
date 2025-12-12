package ayoub.abstracts;

import ayoub.collections.MyCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public abstract class MyAbstractCollection<E> implements MyCollection<E> {

    private static final Logger log = LoggerFactory.getLogger(MyAbstractCollection.class);

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
        log.debug("the collection converted successfully!");
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
                log.debug("Removed element: {}", o); // logs better
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
        log.debug("Collection cleaned successfully");
    }

    // just to see how it's looks like
    public void looks() {
        StringBuilder sb = new StringBuilder("[ ");
        for (E e : this) sb.append(e).append(" ");
        sb.append("]");
        log.info(sb.toString());
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