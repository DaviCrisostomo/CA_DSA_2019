package davimoraes2017322.controllers;

import davimoraes2017322.people.Person;
import davimoraes2017322.priority.Priority;

public class PeopleFactory {

    public static Person createPerson(String firstName, String lastName, String passportNumber, Priority priority) {

            if(
                nameValidation(firstName)&&
                nameValidation(lastName)&&
                passportNumber!=null&&
                priority!=null)
            return new Person(firstName, lastName, passportNumber, priority);

        return null;
    }

    public static boolean nameValidation(String name) {

        if (name.matches("[A-Z][a-z]*"))
            return true;

        return false;
    }

    public static void editPerson(Person person, String firstName, String lastName, String passportNumber, Priority priority) {

        if (person == null)
            return;

        if (nameValidation(firstName))
            person.setFirstName(firstName);
        if (nameValidation(lastName))
            person.setLastName(lastName);
        if (passportNumber != null)
            person.setPassportNumber(passportNumber);
        if (priority != null)
            person.setPriority(priority);

    }

    public static void setAsAttended(Person person) {
        if(person!=null)
            person.setAttended();
    }

}
