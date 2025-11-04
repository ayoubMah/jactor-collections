package ayoub.collections;
//extends Collection
public interface MyList<E> extends MyCollection<E> {

    E get(int index);
    E set(int index, E elm);
    void add(int index, E elm);
    E remove(int index);
    int indexOf(Object o);
    int lastIndexOf(Object o);
    MyList<E> subList(int fromIndex, int toIndex);
}
