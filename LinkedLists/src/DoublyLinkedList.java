;/**
 * Generic Doubly Linked List Class.
 * Each node keeps an explicit reference
 * to the node before it and a reference to the node after it.
 * @param <E>
 */
public class DoublyLinkedList<E> {

    //----------------- Nested Node Class ---------------------
    public static class Node<E> {
        private E element;     //reference to data or element
        private Node<E> prev;  //reference to previous node
        private Node<E> next;  //reference to subsequent node

        public Node(E e,Node<E> p, Node<E> n) {   //Constructor of Node
            this.element = e;
            this.prev = p;
            this.next = n;
        }
        public E getElement() { return element; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
        public void setPrev(Node<E> p) { this.prev = p;}
        public void setNext(Node<E> n) { this.next = n;}
    }
    //---------------- End of Nested Node Class ---------------

    //instance variables
    private Node<E> header; //header sentinel
    private Node<E> trailer; //trailer sentinel
    private int size;

    public DoublyLinkedList() { //Construct an empty list
        this.header = new Node<>(null, null, null); //create header
        this.trailer = new Node<>(null, header, null); //trailer is preceded by header
        header.setNext(trailer);
        this.size = 0;
        /* Note: sentinels are created as dummy nodes to help unify our
           methods. Since new nodes are always placed between a pair of existing nodes. */
    }

    //----- access methods -------
    public int size() { return size; }
    public boolean isEmpty() { return size == 0;}
    public E first() { //returns but does not remove first element
        if (isEmpty()) return null; //if list is empty
        return header.getNext().getElement(); //first element is beyond header
    }
    public E last() { //returns but does not remove last element
        if (isEmpty()) return null; //if list is empty
        return trailer.getPrev().getElement(); //last element is before trailer
    }

    //----- public update methods ------
    /** Adds element e to the front of the list. */
    public void addFirst(E e) {
        addBetween(e, header, header.getNext()); //place just after the header
    }
    /** Adds element e to the end of the list. */
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer); //place just after the header
    }
    /** Removes and returns the first element of the list. */
    public E removeFirst(){
        if(isEmpty()) return null;
        return remove(header.getNext());
    }
    /** Removes and returns the last element of the list. */
    public E removeLast(){
        if(isEmpty()) return null;
        return remove(trailer.getPrev());
    }

    //----- private update methods ------
    /** Adds element e to the linked list in between the given nodes. */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor); //crete and link a new node
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }
    /** Removes the given node from the list and returns its element. */
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor); //Bypass the target node and link each other
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }

}
