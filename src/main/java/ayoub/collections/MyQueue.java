package ayoub.collections;

//extends Collection
public interface MyQueue<E> extends MyCollection<E>{
    boolean add(E e);
    E element();
    boolean offer(E e);
    E peak();
    E poll();
    E remove();
}
