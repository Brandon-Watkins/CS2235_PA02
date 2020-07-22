package edu.isu.cs2235.structures.impl;

import jdk.dynalink.Operation;

import java.math.MathContext;

/**
 * @author Brandon Watkins
 * @author Isaac Griffith
 * @param <E> Element Type
 */
public class SinglyLinkedList<E> implements edu.isu.cs2235.structures.List<E> {

    private class Node<E>{
        private Node<E> next;
        private E value;

        public Node(E value) {
            this.value = value;
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

    public SinglyLinkedList(){
        this.tail = new Node("Tail Sentinel");
        this.tail.setNext(null);
        this.head = new Node("Head Sentinel");
        this.head.setNext(tail);
        length = 0;
    }

    /**
     * increases length, aka size
     */
    private void incLength(){
        length ++;
    }

    /**
     * decreases length, aka size
     */
    private void decLength(){
        length --;
    }

    /**
     * @return first element in the list or null if the list is empty.
     */
    @java.lang.Override
    public E first() {
        if (isEmpty()) return null;
        return (E)head.getNext().getValue();
    }

    /**
     * @return last element in the list or null if the list is empty.
     */
    @java.lang.Override
    public E last() {
        if (!isEmpty())
            return get(length - 1);
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
        if (!isNull(element)){
            Node<E> node = new Node<>(element);
            node.setNext(tail);
            Node<E> prevNode = head;
            // set next node for the previously last node to point to the newly created one.
            for (int i = 0; i < length; i++) {
                prevNode = prevNode.getNext();
            }
            prevNode.setNext(node);
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
        if (!isNull(element)){
            Node<E> node = new Node<>(element);
            node.setNext(head.getNext());
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
        if (!isEmpty()){
            Node<E> firstNode = head.getNext();
            Node<E> secondNode = firstNode.getNext();
            firstNode.setNext(null);
            head.setNext(secondNode);
            decLength();
            return firstNode.getValue();
        }
        else return null;
    }

    /**
     * Removes the element at the end of the list.
     *
     * @return Element at the end of the list(that was just removed), or null if the list is empty.
     */
    @java.lang.Override
    public E removeLast() {
        if (!isEmpty()){
            Node<E> prevNode = head;
            // set 2nd to last node.next to tail, and previously last node.next to null.
            for (int i = 0; i < length - 1; i++) {
                prevNode = prevNode.getNext();
            }
            Node<E> lastNode = prevNode.getNext();
            lastNode.setNext(null);
            prevNode.setNext(tail);
            decLength();
            return lastNode.getValue();
        }
        else return null;
    }

    /**
     * Inserts the given element into the list at the provided index. The
     * element will not be inserted if either the element provided is null or if
     * the index provided is less than 0. If the index is greater than or equal
     * to the current size of the list, the element will be added to the end of
     * the list.
     *
     * @param element Element to be added (as long as it is not null).
     * @param index Index in the list where the element is to be inserted.
     */
    @java.lang.Override
    public void insert(E element, int index) {
        if (!isNull(element) && index >= 0){
            if (index > length) index = length;
            Node<E> node = new Node<>(element);
            Node<E> prevNode = head;
            // set next node for the previously last node to point to the newly created one,
            // and the new node to point to the node that used to be at that index.
            for (int i = 0; i < index; i++) {
                prevNode = prevNode.getNext();
            }
            Node<E> nextNode = prevNode.getNext();
            prevNode.setNext(node);
            node.setNext(nextNode);
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
            Node<E> prevNode = head;
            // sets index node.next to null, and the node before index to point to the node after index.
            for (int i = 0; i < index; i++) {
                prevNode = prevNode.getNext();
            }
            Node<E> nextNode = prevNode.getNext();
            prevNode.setNext(nextNode.getNext());
            nextNode.setNext(null);
            decLength();
            return nextNode.getValue();
        }
        else return null;
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
        if (index >= 0 && index < length){
            Node<E> prevNode = head;
            // returns the value of the element at the index
            for (int i = 0; i < index; i++) {
                prevNode = prevNode.getNext();
            }
            return (E)prevNode.getNext().getValue();
        }
        else return null;
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
        for (int i = 0; i < length; i++){
            node = node.getNext();
            msg += (node.getValue());
            if (i < length - 1)
                msg += " ";
        }
        System.out.print(msg);
    }

    public boolean isNull(E element){
        if (element == null || element == "") return true;
        else return false;
    }
}
