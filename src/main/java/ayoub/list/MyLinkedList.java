package ayoub.list;

public class MyLinkedList<E> {

    // inner class Node<E>
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
    }


    private Node<E> head;
    private Node<E> tail;
    private int size;

}


