package davimoraes2017322.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import davimoraes2017322.controllers.PeopleFactory;
import davimoraes2017322.controllers.QueueController;
import davimoraes2017322.people.Person;
import davimoraes2017322.priority.Priority;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
public class RequestController {

    QueueController queueController = new QueueController();


    @RequestMapping("/call")
    public String call() {

        Person person = queueController.callNext();
        person.setAttended();
        return getJSON(person);
    }

    @PostMapping("/create")
    public String create(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String surname = body.get("surname");
        String passport = body.get("passport");
        Priority priority = Priority.getEnum(body.get("priority"));

        Person person = PeopleFactory.createPerson(name, surname, passport, priority);
        long id = queueController.includeNewPerson(person);
        return getJSON(queueController.getPerson(id));
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable String id, @RequestBody Map<String, String> body) {
        long personId = Long.parseLong(id);
        String name = body.get("name");
        String surname = body.get("surname");
        String passport = body.get("passport");
        Priority priority = Priority.getEnum(body.get("priority"));

        Person person = queueController.getPerson(personId);
        PeopleFactory.editPerson(person, name, surname, passport, priority);
        return getJSON(person);

    }

    @GetMapping("/search/{id}")
    public String search(@PathVariable String id) {
        long personId = Long.parseLong(id);
        Person person = queueController.getPerson(personId);
        return getJSON(person);
    }

    @GetMapping("/position/{id}")
    public long showPosition(@PathVariable String id) {

        long personId = Long.parseLong(id);
        return queueController.getPersonPosition(personId);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable String id) {
        long personId = Long.parseLong(id);
        return queueController.delete(personId);
    }

    @DeleteMapping("/delete/amount/{amount}")
    public int deleteAmount(@PathVariable String value) {
        int amount = Integer.parseInt(value);
        return queueController.deleteFromTail(amount);
    }


    public String getJSON(Object object) {
        String json = "";
        ObjectMapper obj = new ObjectMapper();

        try {

            json = obj.writeValueAsString(object);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

}

