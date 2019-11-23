package davimoraes2017322.people;

import davimoraes2017322.priority.*;

import java.time.LocalDate;


public class Person extends Hierarchical {

    private String firstName;
    private String lastName;
    private LocalDate arrivalDate;//maybe change the dataType
    private String passportNumber;
    private Priority priority;
    private long id;
    private boolean attended = false;

    public Person(){
        this.arrivalDate = LocalDate.now();
        this.id = IDGenerator.getId();
    }


    public Person(String firstName, String lastName, String passportNumber, Priority priority){

        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.priority = priority;
        this.arrivalDate = LocalDate.now();
        this.id = IDGenerator.getId();

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Priority getPriority(){
        return this.priority;
    }

    public void setPriority(Priority priority){this.priority = priority;}

    public long getId(){return this.id;}

    public void setAttended(){ this.attended=!attended;}

    public boolean getAttended(){return this.attended;}

}
