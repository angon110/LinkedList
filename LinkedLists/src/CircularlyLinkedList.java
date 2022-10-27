/**
 * Generic Circularly Linked List Class.
 * Essentially a singularly linked list in which
 * the next reference of the tail node is set to refer back
 * to the head of the list (rather than null).
 * @param <E>
 */
public class CircularlyLinkedList<E> {

    //----------------- Nested Node Class ---------------------
    //Identical to SinglyLinkedList Class
    public static class Node<E> {
        private E element;  //reference to data or element
        private Node<E> next;   //reference to subsequent node

        public Node(E e, Node<E> n) {   //Constructor of Node
            this.element = e;
            this.next = n;
        }
        public E getElement() { return element; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> n) { this.next = n;}
    }
    //---------------- End of Nested Node Class ---------------

    //head can be pointed by tail.getNext()
    private Node<E> tail; //tail pointer
    private int size;

    public CircularlyLinkedList() { //Construct an empty list
        this.tail = null;
        this.size = 0;
    }

    //----- access methods -------
    public int size() { return size; }
    public boolean isEmpty() { return size == 0;}
    public E first() { //returns but does not remove first element
        if (isEmpty()) return null; //if list is empty
        return tail.getNext().getElement(); //tail.getNext() points to first
    }
    public E last() { //returns but does not remove last element
        if (isEmpty()) return null; //if list is empty
        return tail.getElement();
    }

    //----- update methods ------
    public void rotate() {  //rotates the first element to the back of the list
        if (tail != null)   //if empty, do nothing
            tail = tail.getNext();  //the old head becomes the new tail
    }
    public void addFirst(E e) { //adds element e to the front of the list
        if(isEmpty()) {
            tail = new Node<>(e, null); //for a list of 1 element
            tail.setNext(tail);            //link circularly to itself
        }
        size++;
    }
    //NOTE: new Node<> data type will be specified by head!
    public void addLast(E e) { //adds element e to the end of the list
        addFirst(e);           //insert new element at front
        tail = tail.getNext(); //now new element becomes the tail
    }
    public E removeFirst() {    //removes and returns the first element
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail) tail = null; //if it's the only element;
        else tail.setNext(head.getNext()); //removes head from the list
        size--;
        return head.getElement();
    }

}