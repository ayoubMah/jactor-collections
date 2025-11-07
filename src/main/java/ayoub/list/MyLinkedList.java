package ayoub.list;

import ayoub.abstracts.MyAbstractList;

import java.util.Iterator;

public class MyLinkedList<E> extends MyAbstractList<E> {


    private Node<E> head;
    private Node<E> tail;
    private int size;

    // |head|
    public  MyLinkedList(){
        head = new Node<>(null, null, null); // why null and not tail? cuz in this line we don't know tail , it's null
        tail = new Node<>(head, null, null);
        head.setNext(tail); // link the head forward to tail
    }

    // helper
    private Node<E> nodeAt(int index) {
        // TODO: for now no need to make optimization zie/2  we start always from head
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        Node<E> current = head.getNext(); // At position 0
        for (int i = 0; i < index ; i++) {
            current = current.getNext();
        }
        return current;
    }

    @Override
    public E get(int index) {
        Node<E> node = nodeAt(index);
        return node.getData();
    }

    @Override
    public E set(int index, E elm) {
        Node<E> current = nodeAt(index);
        E old = current.getData();
        current.setData(elm);
        return old;
    }

    @Override
    public void add(int index, E elm) {
        Node<E> current = nodeAt(index);
        Node<E> prev = current.getPrev();
        Node<E> newNode = new Node<>(prev, elm, current);

        prev.setNext(newNode);
        current.setPrev(newNode);
        size ++;
    }

    @Override
    public E remove(int index) {
        Node<E> target = nodeAt(index);
        Node<E> prev = target.getPrev();
        Node<E> next = target.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        // let's help the GC
        target.setPrev(null);
        target.setNext(null);
        size --;

        return target.getData();
    }

    // in a linked list if we have a 2 nodes of more that has the same data => we should return the last index of a node has this data
    // so it's smart move to start with the tail until get the first elm and it's the target
    @Override
    public int lastIndexOf(Object o) {
        int index = size - 1;
        Node<E> current = tail.getPrev();
        for (int i = size - 1; i >= 0; i --){
            if (current == null ? o == null: current.getData().equals(o)){
                return i;
            }
            current = current.getPrev();
        }
        return -1; // if we don't found it
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public E first(){
        if (isEmpty()) return null;
        return head.getNext().getData();
    }

    public E last(){
        if (isEmpty()) return null;
        return tail.getPrev().getData();
    }

    public void addBetween(Node<E> prec, E elm, Node<E> successor){
        Node<E> node = new Node<>(prec, elm, successor);
        prec.setNext(node);
        successor.setPrev(node);
        size ++;
    }

    public void addFirst(E elm){
        addBetween(head, elm, head.getNext());
    }

    public void addLast(E elm){
        addBetween(tail.getPrev(), elm, tail);
    }

    public E removeFirst(){
        if (isEmpty()) return null;
        remove(head.getNext().getData()); // this remove is public boolean remove(Object o) from my abstract collection
        return head.getNext().getData();
    }

    public E removeLast(){
        if (isEmpty()) return null;
        remove(tail.getPrev().getData());
        return tail.getPrev().getData();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> current = head.getNext();
            @Override
            public boolean hasNext() {
                return current != tail;
            }

            @Override
            public E next() {
                E data = current.getData();
                current = current.getNext();
                return data;
            }

            // todo
            @Override
            public void remove(){
                throw new UnsupportedOperationException("remove not yet supported");
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    // with the view method you can see how your linkedList looks like
    public String view(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> current = head.getNext();
        while (current != tail) {
            sb.append(current.getData());
            current = current.getNext();
            if (current != tail) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }


    // inner class Node<E>
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        // i prefer the constructor be like this cuz i imagine a node in this order:   |prev|<--->|data|<--->|next|
        public Node(Node<E> prev, E data, Node<E> next){
            this. prev = prev;
            this.data = data;
            this.next = next;
        }

        // return the current data of a node
        public E getData() {return data;}

        // return the old data that was in this node and replace it with newData
        public E setData(E newData) {
            E oldOne = getData();
            this.data = newData;
            return oldOne;
        }

        public Node<E> getNext() {return next;}

        public Node<E> getPrev() {return prev;}

        // return the old node that was next and set the new one => if the next was null return null
        public Node<E> setNext(Node<E> newNext) {
            Node<E> old = next;
            if (next != null){
                this.next = newNext;
                return old;
            }
            this.next = newNext;
            return null;
        }

        // same logic as setNext
        public Node<E> setPrev(Node<E> newPrev) {
            Node<E> old = prev;
            if (prev != null){
                this.prev = newPrev;
                return old;
            }
            this.prev = newPrev;
            return null;
        }
    }
}