/**
 * Generic Singly Linked List Class.
 * Each node stores a reference to an object that is an element of the sequence,
 * as well as a reference to the next node of the list.
 * @param <E>
 */
public class SinglyLinkedList<E> {

    //----------------- Nested Node Class ---------------------
    public static class Node<E> {
        private E element;      //reference to data or element
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

    private Node<E> head; //head pointer
    private Node<E> tail; //tail pointer
    private int size;

    public SinglyLinkedList() { //Construct an empty list
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    //----- access methods -------
    public int size() { return size; }
    public boolean isEmpty() { return size == 0;}
    public E first() { //returns but does not remove first element
        if (isEmpty()) return null; //if list is empty
        return head.getElement();
    }
    public E last() { //returns but does not remove last element
        if (isEmpty()) return null; //if list is empty
        return tail.getElement();
    }

    //----- update methods ------
    public void addFirst(E e) { //adds element e to the front of the list
        head = new Node<>(e,head);//creates a node and links with previous head
        if (isEmpty())   //SPECIAL CASE for a list of 1 element
            tail = head; //1 element is both head and tail
        size++;
    }
    //NOTE: new Node<> data type will be specified by head!
    public void addLast(E e) { //adds element e to the end of the list
        Node<E> newest = new Node<>(e,null);//creates a node and links with previous head
        if (isEmpty())   //SPECIAL CASE for a list of 1 element
            head = newest; //1 element is both head and tail
        else
            tail.setNext(newest);
        tail = newest;
        size++;
    }
    public E removeFirst() {    //removes and returns the first element
        if (isEmpty()) return null;
        E answer = head.getElement(); //store head element to return later
        head = head.getNext(); //change head to the next node
        size--; //decrement size
        if(isEmpty()) { tail = null; } //if the list had only one element
        return answer;
    }

}
