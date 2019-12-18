package davimoraes2017322.people;
//It has to be outside the class People in order
//to avoid the static attribute to be included in the
//Json schema by Jackson
 class IDGenerator{

    private static long idRef = 0;

    public static long getId() {

        return ++idRef;
    }
}
