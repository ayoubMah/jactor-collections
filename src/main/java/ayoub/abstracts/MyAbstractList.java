package ayoub.abstracts;

import ayoub.collections.MyList;
import ayoub.list.MyArrayList;

public abstract class MyAbstractList<E> extends MyAbstractCollection<E> implements MyList<E> {

    // Abstract Methods ===============================================================================================================
    public abstract E get(int index); // it's abst cuz it needs storage info
    public abstract E set(int index, E elm); // why E? cuz it's return the previous elm that was in the same position
    public abstract void add(int index, E elm);
    public abstract E remove(int index);

    // concrete imp ===============================================================================================================

    // added in the tail so it's add(size(), E elm)
    public boolean add(E elm){
        int index = size(); // the current index of it
        add(index , elm); // add the elm in the tail of the collection
        System.out.println("the elm added successfully!");
        return size() == index + 1 ; // if the new size() == the prev size() + 1 , cuz we add just by one
    }

    // TODO: cas of null object => o == null!
    public int indexOf(Object o){
        for (int i = 0; i < size(); i++){
            if (get(i).equals(o)) return i;
        }
        return -1;
    }

    public void trimToSize(){
        // default: nothing
    }

    @Override
    public MyList<E> subList(int fromIndex, int toIndex){
        if (fromIndex < 0 || toIndex > size())
            throw new IndexOutOfBoundsException();
        if (fromIndex > toIndex)
            throw new IllegalArgumentException();
        MyList<E> sub = new MyArrayList<>();
        for (int i = fromIndex; i < toIndex; i++){
            sub.add(get(i));
        }
        return sub;
    }
}
