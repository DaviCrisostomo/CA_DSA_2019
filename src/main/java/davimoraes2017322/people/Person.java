package davimoraes2017322.people;

import davimoraes2017322.priority.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class Person extends Hierarchical {

    private long id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String arrivalDate;//maybe change the dataType
    private Priority priority;
    private Status status = Status.WAITING;

    public Person(){
        LocalDate now = LocalDate.now();
        this.arrivalDate = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
        this.id = IDGenerator.getId();
    }


    public Person(String firstName, String lastName, String passportNumber, Priority priority){
        LocalDate now = LocalDate.now();
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.priority = priority;
        this.arrivalDate = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
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

    public String getArrivalDate() {
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

    public void setStatus(Status status){ this.status=status;}

    public Status getStatus(){return this.status;}

}
