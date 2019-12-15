package davimoraes2017322.rest;

import davimoraes2017322.controllers.PeopleFactory;
import davimoraes2017322.controllers.QueueController;
import davimoraes2017322.people.Person;
import davimoraes2017322.people.Status;
import davimoraes2017322.priority.Priority;


import java.util.Map;

public class RequestHandler {

    private static QueueController queueController = new QueueController();

    static String creator(Map<String, String> body) {

        if(!Utilities.bodyChecker(body))
            return "{\"message\":\"Missing one or more fields.\"}";

        Person person = PeopleFactory.createPerson(
        body.get("name"),
        body.get("surname"),
        body.get("passport"),
        Priority.getEnum(body.get("priority"))
        );

        if(person==null)
            return "{\"message\":\"Invalid value for one or more fields.\"}";

        queueController.includeNewPerson(person);
        return Utilities.getJSON(person);
    }

    static String caller() {

        Person person = queueController.callNext();

        if(person==null)
            return "{\"message\":\"Empty queue\"}";

        PeopleFactory.setAsCalled(person);
        return Utilities.getJSON(person);
    }

    static String updateChecker(String id, Map<String, String> body) {

        if(!Utilities.bodyChecker(body))
            return "{\"message\":\"Missing one or more fields.\"}";

        long personId = Utilities.integerChecker(id);

        if(personId<=0)
            return "{\"message\":\"Wrong value for id.\"}";

        Person person = queueController.getPerson(personId);

        if(person==null)
            return "{\"message\":\"Object not found.\"}";

        PeopleFactory.editPerson(
                person,
                body.get("name"),
                body.get("surname"),
                body.get("passport"),
                Status.getEnum(body.get("status")));

        return Utilities.getJSON(person);

    }

    static String searcher(String id) {

        long personId = Utilities.integerChecker(id);

        if(personId<=0)
            return "{\"message\":\"Wrong value for id.\"}";

        Person person = queueController.getPerson(personId);

        if(person==null)
            return "{\"message\":\"Object not found.\"}";

        return Utilities.getJSON(person);
    }

    static String positionChecker(String id) {

        long personId = Utilities.integerChecker(id);

        if(personId<=0)
            return "{\"message\":\"Wrong value for id.\"}";

        return String.valueOf(queueController.getPersonPosition(personId));
    }


    static String deleteId(String id) {
        long personId = Utilities.integerChecker(id);

        if(personId<=0)
            return "{\"message\":\"Wrong value for id.\"}";

        Person person = queueController.getPerson(personId);

        if(person==null)
            return "{\"message\":\"Object not found.\"}";

        return "{\"position\":\""+queueController.delete(personId)+"\"}\"";
    }

    static String deleteGroup( String value) {

        long amount = Utilities.integerChecker(value);

        if(amount<=0)
            return "{\"message\":\"Value has to be an integer.\"}";

        return "{\"queue size\":\""+queueController.deleteAmount((int)amount)+"\"}\"";
    }
}
