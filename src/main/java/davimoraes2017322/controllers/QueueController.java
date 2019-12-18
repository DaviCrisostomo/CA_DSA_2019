package davimoraes2017322.controllers;

import davimoraes2017322.immigrationList.*;

import davimoraes2017322.people.Person;
import davimoraes2017322.people.Status;

import java.util.HashMap;
import java.util.Map;

public class QueueController {

    private DoublyLinkedList<Person> immigrationServiceList = new DoublyLinkedList<>();
    //a way to give an index for each node in the queue. considering that a queue from
    //immigration service tends to be not that huge, it can be considered as an overhead'
    //I mean, most of the work donne for this assignment can be considered like that, but
    //for me it is also a way to learn new things. Sorry...
    private Map<Long, NodeModel> nodeTracker = new HashMap<>();

    public QueueController() {
    }

    //including a new person
    public boolean includeNewPerson(Person person) {

        if (person != null) {
            NodeModel node = immigrationServiceList.addElement(person);
            nodeTracker.put(person.getId(), node);
            return true;
        }

        return false;
    }
//delete one specific person
    public boolean delete(long id) {

        NodeModel node = nodeTracker.get(id);

        if (node == null)
            return false;

        immigrationServiceList.delete(node);
        nodeTracker.remove(id);
        return true;

    }
//get a specific person from the queue, if it exists
    public Person getPerson(long id) {

        if (nodeTracker.containsKey(id))
            return (Person) nodeTracker.get(id).getData();

        return null;
    }
//calling the head of the queue
    public Person callNext() {
        Person person = immigrationServiceList.dequeue();
        if (person != null)
            person.setStatus(Status.CALLED);
        return person;
    }
//get the position of somebody in the queue
    public int getPersonPosition(long id) {
        Person person = (Person) nodeTracker.get(id).getData();
        int index = immigrationServiceList.getIndex(person);
        //if index==-1, the person wasn't found. Else, position is equal index+1
        return index == -1 ? index : ++index;
    }
//delete a number of people, from the tail to the head
    public int deleteAmount(int amount) {

        for (int i = 0; i < amount; i++) {
            NodeModel node = immigrationServiceList.deleteTail();

            if (node == null)
                break;

            Person person = (Person) node.getData();
            nodeTracker.remove(person.getId());
        }
        return immigrationServiceList.size();
    }
}

