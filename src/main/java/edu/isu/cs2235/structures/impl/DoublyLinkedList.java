package edu.isu.cs2235.structures.impl;

/**
 * @author Brandon Watkins
 * @author Isaac Griffith
 * @param <E> Element Type
 */
public class DoublyLinkedList<E> implements edu.isu.cs2235.structures.List<E> {

    private class Node<E> {
        private Node<E> next;
        private Node<E> prev;
        private E value;

        public Node(E value) {
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }
    }

    private Node<E> tail;
    private Node<E> head;
    private int length;

    public DoublyLinkedList() {
        this.tail = new Node("Tail Sentinel");
        this.head = new Node("Head Sentinel");
        this.tail.setNext(null);
        this.tail.setPrev(head);
        this.head.setNext(tail);
        this.head.setPrev(null);
        length = 0;
    }

    /**
     * increases length, aka size
     */
    private void incLength() {
        length++;
    }

    /**
     * decreases length, aka size
     */
    private void decLength() {
        length--;
    }

    /**
     * @return first element in the list or null if the list is empty.
     */
    @java.lang.Override
    public E first() {
        if (isEmpty()) return null;
        return (E) head.getNext().getValue();
    }

    /**
     * @return last element in the list or null if the list is empty.
     */
    @java.lang.Override
    public E last() {
        if (!isEmpty())
            return (E)tail.getPrev().getValue();
        else
            return null;
    }

    /**
     * Adds the provided element to the end of the list, only if the element is
     * not null.
     *
     * @param element Element to be added to the end of the list.
     */
    @java.lang.Override
    public void addLast(E element) {
        if (!isNull(element)) {
            Node<E> node = new Node<>(element);
            node.setNext(tail);
            node.setPrev(tail.getPrev());
            tail.getPrev().setNext(node);
            tail.setPrev(node);
            incLength();
        }
    }

    /**
     * Adds the provided element to the front of the list, only if the element
     * is not null.
     *
     * @param element Element to be added to the front of the list.
     */
    @java.lang.Override
    public void addFirst(E element) {
        if (!isNull(element)) {
            Node<E> node = new Node<>(element);
            node.setNext(head.getNext());
            node.setPrev(head);
            head.getNext().setPrev(node);
            head.setNext(node);
            incLength();
        }
    }

    /**
     * Removes the element at the front of the list.
     *
     * @return Element at the front of the list(that was just removed), or null if the list is empty.
     */
    @java.lang.Override
    public E removeFirst() {
        if (!isEmpty()) {
            Node<E> firstNode = head.getNext();
            Node<E> secondNode = firstNode.getNext();
            firstNode.setNext(null);
            firstNode.setPrev(null);
            head.setNext(secondNode);
            secondNode.setPrev(head);
            decLength();
            return firstNode.getValue();
        } else return null;
    }

    /**
     * Removes the element at the end of the list.
     *
     * @return Element at the end of the list(that was just removed), or null if the list is empty.
     */
    @java.lang.Override
    public E removeLast() {
        if (!isEmpty()) {
            Node<E> lastNode = tail.getPrev();
            tail.setPrev(lastNode.getPrev());
            lastNode.getPrev().setNext(tail);
            lastNode.setPrev(null);
            lastNode.setNext(null);
            decLength();
            return lastNode.getValue();
        } else return null;
    }

    /**
     * Inserts the given element into the list at the provided index. The
     * element will not be inserted if either the element provided is null or if
     * the index provided is less than 0. If the index is greater than or equal
     * to the current size of the list, the element will be added to the end of
     * the list.
     *
     * @param element Element to be added (as long as it is not null).
     * @param index   Index in the list where the element is to be inserted.
     */
    @java.lang.Override
    public void insert(E element, int index) {
        if (!isNull(element) && index >= 0) {
            if (index > length) index = length;
            Node<E> oldIndexNode;
            Node<E> node = new Node<>(element);
            /** find the index node that will be moved to make room for inserted node. */
            /** if Index is less than half of the list size, search from head, otherwise search bacwards from tail. */
            if (index < length / 2){
                oldIndexNode = head;
                for (int i = 0; i <= index; i++) {
                    oldIndexNode = oldIndexNode.getNext();
                }
            }
            else {
                oldIndexNode = tail;
                for (int i = length; i > index; i--) {
                    oldIndexNode = oldIndexNode.getPrev();
                }
            }

            /** insert the node, and scoot over the old index node */
            node.setPrev(oldIndexNode.getPrev());
            oldIndexNode.getPrev().setNext(node);
            oldIndexNode.setPrev(node);
            node.setNext(oldIndexNode);

            incLength();
        }
    }

    /**
     * Removes the element at the given index and returns the value.
     *
     * @param index Index of the element to remove
     * @return The value of the element at the given index, or null if the index
     * is greater than or equal to the size of the list or less than 0.
     */
    @java.lang.Override
    public E remove(int index) {
        if (index <= length - 1 && index >= 0) {
            Node<E> nodeToRemove;
            /** find the index node that will be removed */
            /** if Index is less than half of the list size, search from head, otherwise search backwards from tail. */
            if (index < length / 2){
                nodeToRemove = head;
                for (int i = 0; i <= index; i++) {
                    nodeToRemove = nodeToRemove.getNext();
                }
            }
            else {
                nodeToRemove = tail;
                for (int i = length; i > index; i--) {
                    nodeToRemove = nodeToRemove.getPrev();
                }
            }
            /** remove the node, adjusting adjacent nodes accordingly. */
            nodeToRemove.getPrev().setNext(nodeToRemove.getNext());
            nodeToRemove.getNext().setPrev(nodeToRemove.getPrev());
            nodeToRemove.setPrev(null);
            nodeToRemove.setNext(null);

            decLength();
            return nodeToRemove.getValue();
        } else return null;
    }

    /**
     * Retrieves the value at the specified index. Will return null if the index
     * provided is less than 0 or greater than or equal to the current size of
     * the list.
     *
     * @param index Index of the value to be retrieved.
     * @return Element at the given index, or null if the index is less than 0
     * or greater than or equal to the list size.
     */
    @java.lang.Override
    public E get(int index) {
        if (index >= 0 && index < length) {
            Node<E> node;
            /** find the index node that will be moved to make room for inserted node. */
            /** if Index is less than half of the list size, search from head, otherwise search bacwards from tail. */
            if (index < length / 2){
                node = head;
                for (int i = 0; i <= index; i++) {
                    node = node.getNext();
                }
            }
            else {
                node = tail;
                for (int i = length; i > index; i--) {
                    node = node.getPrev();
                }
            }
            return node.getValue();
        } else return null;
    }

    /**
     * @return The current size of the list. Note that 0 is returned for an
     * empty list.
     */
    @java.lang.Override
    public int size() {
        return length;
    }

    /**
     * @return true if there are no items currently stored in the list, false
     * otherwise.
     */
    @java.lang.Override
    public boolean isEmpty() {
        if (length >= 1) return false;
        else return true;
    }

    /**
     * Prints the contents of the list in a single line separating each element
     * by a space to the default System.out
     */
    @java.lang.Override
    public void printList() {
        String msg = "";
        Node<E> node = head;
        for (int i = 0; i < length; i++) {
            node = node.getNext();
            msg += (node.getValue());
            if (i < length - 1)
                msg += " ";
        }
        System.out.print(msg);
    }

    public boolean isNull(E element) {
        if (element == null || element == "") return true;
        else return false;
    }
}