package davimoraes2017322.priority;

public enum Priority{

    HIGH("High Priority"),
    MEDIUM("Medium Priority"),
    LOW("Low Priority");

    private final String priorityLevel;

    Priority(String priorityLevel){

        this.priorityLevel = priorityLevel;

    }


    @Override
    public String toString() {
        return priorityLevel;
    }


    public static Priority getEnum(String value){

        String checkValue = value.toUpperCase();

        switch(checkValue){
            case "HIGH": return Priority.HIGH;

            case "MEDIUM": return Priority.MEDIUM;

            case "LOW": return Priority.LOW;

            default: return null;


        }

    }
}
