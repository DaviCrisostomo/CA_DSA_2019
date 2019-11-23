package davimoraes2017322.people;

 class IDGenerator{

    private static long idRef = 0;

    public static long getId() {

        return ++idRef;
    }
}
