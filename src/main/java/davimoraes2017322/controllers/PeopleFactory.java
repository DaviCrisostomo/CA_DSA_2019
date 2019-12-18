package davimoraes2017322.controllers;

import davimoraes2017322.people.Gender;
import davimoraes2017322.people.Person;
import davimoraes2017322.people.Status;
import davimoraes2017322.priority.Priority;

public class PeopleFactory {

    public static Person createPerson(String firstName, String lastName, String passportNumber, Priority priority, Gender gender) {

        if ( nameValidation(firstName)&&nameValidation(lastName)&&passValidation(passportNumber)&&priority!=null){
            return new Person(firstName, lastName, passportNumber, priority, gender);
        }

        return null;
    }

    public static boolean nameValidation(String name) {

        return name.matches("[A-Z][a-z]*");
    }

    public static boolean passValidation(String passport){
//Two capital letters and six numbers
        return passport.matches("[A-Z]{2}\\d{6}");
    }

    public static void editPerson(Person person, String firstName, String lastName, String passportNumber, Status status, Gender gender) {

        if (person == null)
            return;

        if (nameValidation(firstName))
            person.setFirstName(firstName);
        if (nameValidation(lastName))
            person.setLastName(lastName);
        if (passportNumber != null)
            person.setPassportNumber(passportNumber);
        if (status != null)
            person.setStatus(status);
        if(gender != null)
            person.setGender(gender);

    }

    public static void setAsCalled(Person person) {
        if(person!=null)
            person.setStatus(Status.CALLED);
    }


}
