package davimoraes2017322.immigrationList;

import davimoraes2017322.priority.*;

import java.util.ArrayList;

public class DoublyLinkedList<E extends Hierarchical> {

    private Node head;
    private Node tail;
    private Node lastHigh;
    private Node lastMedium;
    private int size = 0;

    public DoublyLinkedList() {
    }

    public boolean isEmpty() {

        return size == 0;
    }

    public int size() {

        return this.size;
    }

    /*
    (Amilcar)The user can add a new person to the queue and it gets automatically
    organised according to its priority. Code is well structured and commented.

    (Davi)The operation is divided into three parts: addElement, addMedium and addHigh.
    In order to keep the list well organized only addElement can be called from outside of
    the class. There the new node is created and, if it's possible, included in the list.
    If the operation is not possible to be entirely handled by addElement, the new nod is
    sent to addHigh() or addMedium() according to the data priority level.
     */
    public Node addElement(E data) {

        Node nodeToAdd = new Node(data);

        if (isEmpty()) {
            this.head = this.tail = nodeToAdd;
            if (nodeToAdd.getDataPriority() == Priority.HIGH)
                this.lastHigh = nodeToAdd;
            else if (nodeToAdd.getDataPriority() == Priority.MEDIUM)
                this.lastMedium = nodeToAdd;
        } else if (data.getPriority() == Priority.HIGH)
            addHigh(nodeToAdd);
        else if (data.getPriority() == Priority.MEDIUM)
            addMedium(nodeToAdd);
            //if the code reaches this point the data is a Low priority
            //and can be added at the end of the list
        else {
            this.tail.next = nodeToAdd;
            nodeToAdd.previous = this.tail;
            this.tail = nodeToAdd;
        }

        size++;
        //The method returns the node in order to give some flexibility
        //to the outside class. But the Node object can be edited only from inside
        //this class
        return nodeToAdd;
    }
//addMedium and addHigh will never take an empty queue
    private void addMedium(Node nodeToAdd) {
        ///a medium priority occupying the queue's head
        if (this.head.data.getPriority() == Priority.LOW) {
            nodeToAdd.next = this.head;
            this.head.previous = nodeToAdd;
            this.head = nodeToAdd;
        }
        ///a medium priority occupying the next position after the last high priority -
        ///if the head is not LowPriority and lastMedium is null, the head is necessarily HighPriority
        else if (lastMedium == null /*&& lastHigh != null*/) {
            nodeToAdd.previous = lastHigh;
            nodeToAdd.next = lastHigh.next;

            if (lastHigh.next != null)
                lastHigh.next.previous = nodeToAdd;
            else
                tail = nodeToAdd;

            lastHigh.next = nodeToAdd;
        }
        ///newNode is defined as the lastMediumPriority
        else /*if (lastMedium != null)*/ {
            nodeToAdd.previous = lastMedium;
            nodeToAdd.next = lastMedium.next;

            if (tail == lastMedium)
                tail = nodeToAdd;
            else
                lastMedium.next.previous = nodeToAdd;

            lastMedium.next = nodeToAdd;

        }
        lastMedium = nodeToAdd;
    }

    private void addHigh(Node nodeToAdd) {
//a high priority going straight to the head
        if (lastHigh == null) {
            nodeToAdd.next = this.head;
            this.head.previous = nodeToAdd;
            this.head = nodeToAdd;
        } else {
            nodeToAdd.previous = this.lastHigh;
            nodeToAdd.next = this.lastHigh.next;
            //making sure we are not trying to access an object's attribute from a null pointer
            if (this.lastHigh.next != null)
                this.lastHigh.next.previous = nodeToAdd;
            else
                this.tail = nodeToAdd;

            this.lastHigh.next = nodeToAdd;

        }

        lastHigh = nodeToAdd;
    }

    public E viewHead() {
        return head == null ? null : head.data;
    }

    public E viewTail() {
        return tail == null ? null : tail.data;
    }

    /*
    (Amilcar)The user can remove the first person from the queue when this has been
    looked after. Code is well structured and commented.
     */
    public E dequeue() {

        if (isEmpty())
            return null;

        E elementBeingCalled = head.data;
        //we cannot just delete it here and reassign the head to another node
        //due the amount of possibilities involving all the variables
        delete(head);
        return elementBeingCalled;
    }

    /*
    (Amilcar)A person in any position can be deleted from the queue, connecting the
    person who was in front of them to the person who was behind them.
    (Davi)We have two possibilities: the first one - delete(E data) - iterates throughout
    the list till find the desirable node and then send it to delete(Node nodeToBeDeleted)
    and then the second method is going to take care of all process involving the variables.
    The second possibility is call delete(Node nodeToBeDeleted) directly - but only if the
    outside class already knows what node has to be deleted.
     */
    public boolean delete(E data) {

        Node current;
        Priority priorityOfTheDataToDelete = data.getPriority();
//defining the starting point of the search
        if (priorityOfTheDataToDelete == Priority.MEDIUM)
            current = lastMedium;
        else if (priorityOfTheDataToDelete == Priority.HIGH)
            current = lastHigh;
        else
            current = tail;
        //means that the data wasn't found in the list
        while (current != null) {

            if (priorityOfTheDataToDelete != current.getDataPriority())
                current = null;

            else if (current.data == data) {
                delete(current);
                break;
            } else
                current = current.previous;

        }
        //false means that the data wasn't found
        return current != null;
    }

    public void delete(NodeModel node) {
        //as we are dealing with a node that can be in anywhere in the list
        //we have to check all possibilities in order to do not loose the track
        //of the variables
        Node nodeToBeDeleted = checkingNodeClassCompatibility(node);
        if (nodeToBeDeleted == null)
            return;

        if (this.head == nodeToBeDeleted)
            this.head = nodeToBeDeleted.next;
        if (this.tail == nodeToBeDeleted)
            this.tail = nodeToBeDeleted.previous;

        if (lastMedium == nodeToBeDeleted) {//if I invert it the program may crash
            if (nodeToBeDeleted.previous == null || nodeToBeDeleted.previous.getDataPriority() == Priority.HIGH)
                lastMedium = null;
            else
                lastMedium = nodeToBeDeleted.previous;
        } else if (lastHigh == nodeToBeDeleted) {
            if (nodeToBeDeleted.previous == null)
                lastHigh = null;
            else//before a high only another high
                lastHigh = nodeToBeDeleted.previous;
        }
        nodeToBeDeleted.detachNode();
        size--;
    }

    public Node checkingNodeClassCompatibility(NodeModel nodeToCheck) {
        Node checkedNode;
        try {
            checkedNode = (Node) nodeToCheck;
        } catch (ClassCastException ex) {
            return null;
        }
        return checkedNode;
    }

    /*
   Delete last element
     */
    public Node deleteTail() {

        if(this.tail==null)
            return null;

        Node current = this.tail;

        this.tail = current.previous;
        current.detachNode();

        size--;
//passing the new last node/tail
        reassigningLastNodeVariables(this.tail);
        return current;

    }

    //The function considers that the last node in the list (the tail) is the one being parsed
    private void reassigningLastNodeVariables(Node node) {
        ///updating lastHigh and also making sure that
        ///lastMedium is not pointing to a person that
        ///is not part of the queue anymore
        if (node == null)
            this.head = this.tail = this.lastHigh = this.lastMedium = null;
        else if (node.getDataPriority() == Priority.HIGH) {
            this.lastHigh = node;
            this.lastMedium = null;
        } else if (node.getDataPriority() == Priority.MEDIUM)
            this.lastMedium = node;
    }

    /*
    (Amilcar)The user can check to see what position in the queue a person currently
     is. Code is well structured and commented.
     (Davi) I'm passing an index instead a position. Maybe I could go straight away.
     */
    public int getIndex(E data) {
        //starting by the head, counting starting from 0
        Node current = head;
        int index = 0;

        if (data == null||isEmpty())
            return -1;
        //keep the loop if the data is not that what we want
        while (data != current.data) {

            current = current.next;
            //the loop reached the end of the list and the data wasn't found
            if (current == null)
                return -1;
            //increasing value at the end of each iteration
            index++;

        }

        return index;

    }
//I don't know if I gonna need this one. let's see
    public ArrayList<E> gettingList() {

        ArrayList<E> fullList = new ArrayList<>();

        Node current = head;

        while (current != null) {
            fullList.add(current.data);
            current = current.next;
        }
        return fullList;
    }

    //as Node is a inner class set as private, the attributes don't have to be set as
    //private as well. They wont be visible anyway. But here I made the class Node to implement
    //an interface just to give some possibilities from an class outside the list - like deleting
    //a node straight away by using the method delete(Node nodeToBeDeleted). The NodeModel
    //interface has only getters, so the attributes from the class Node can not be edited by a
    //class outside the list.
    private class Node implements NodeModel<E> {
        //even if they are set as private, the list has access to them
        private Node previous;
        private Node next;
        private E data;

        Node(E data) {
            this.data = data;
        }

        //just to make the code cleaner up there
        public Priority getDataPriority() {

            return data.getPriority();

        }

        //here the node is responsible for detach itself from the mother ship
        //connecting its previous node to its next node, if they exist
        public void detachNode() {

            if (this.next != null)
                this.next.previous = this.previous;
            if (this.previous != null)
                this.previous.next = this.next;
            //TODO: Check if it is really necessary in order to make the node eligible to garbage collector
            this.next = null;
            this.previous = null;
            //this.data = null;

        }

        @Override
        public E getData() {
            return this.data;
        }
    }

}

/*
    public void delete(int n) {

        Node current;

        while (n > 0 && size > 0) {

            current = this.tail;
            this.tail = current.previous;
            current.detachNode();
            n--;
            size--;
        }

        reassigningLastNodeVariables(this.tail);

    }
 */
