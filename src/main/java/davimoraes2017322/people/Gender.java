package davimoraes2017322.people;
//I decided to include it just because of the front end
//And Undefined is probably not the best term, I know
public enum Gender {

    FEMALE("Female"),
    MALE("Male"),
    UNDEFINED("Undefined");
    String gender;
    Gender(String gender){
        this.gender = gender;
    }

    public static Gender getEnum(String value) {

        String checkValue = value.toUpperCase();

        switch (checkValue) {
            case "FEMALE":
                return Gender.FEMALE;

            case "MALE":
                return Gender.MALE;

            case "UNDEFINED":
                return Gender.UNDEFINED;

            default:
                return null;

        }
    }
}
