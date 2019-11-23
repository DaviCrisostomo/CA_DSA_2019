package davimoraes2017322.controllers;

import davimoraes2017322.immigrationList.DoublyLinkedList;
import davimoraes2017322.immigrationList.NodeModel;
import davimoraes2017322.people.Person;

import java.util.HashMap;
import java.util.Map;

public class QueueController {

    private DoublyLinkedList<Person> immigrationServiceList = new DoublyLinkedList<>();
    private Map<Long, NodeModel> nodeTracker = new HashMap<>();

    public QueueController(){}
//change to return the same object but calling from the queue
    public long includeNewPerson(Person person) {

        if (person != null) {
            NodeModel node = immigrationServiceList.addElement(person);
            nodeTracker.put(person.getId(), node);
            return person.getId();
        }

        return -1;
    }

    public boolean delete(long id) {

        NodeModel node = nodeTracker.get(id);

        if (node == null)
            return false;
        else
            immigrationServiceList.delete(node);

        return true;

    }

    public Person getPerson(long id) {

        NodeModel node = nodeTracker.get(id);

        return node!=null? (Person)node.getData():null;
    }

    public Person callNext() {
        return immigrationServiceList.dequeue();
    }

    public int getPersonPosition(long id) {
        Person person = (Person) nodeTracker.get(id).getData();
        int index = immigrationServiceList.getIndex(person);
        //if index==-1, the person wasn't found. Else, position is equal index+1
        return index == -1 ? index : ++index;
    }

    public int deleteFromTail(int amount) {
        immigrationServiceList.delete(amount);
        return immigrationServiceList.size();
    }
}

