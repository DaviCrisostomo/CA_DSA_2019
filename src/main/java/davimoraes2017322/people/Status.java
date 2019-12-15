package davimoraes2017322.people;

public enum Status {

    WAITING("waiting"),
    CALLED("called"),
    CHECKED("checked");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public static Status getEnum(String value) {

        String checkValue = value.toUpperCase();

        switch (checkValue) {
            case "WAITING":
                return Status.WAITING;

            case "CALLED":
                return Status.CALLED;

            case "CHECKED":
                return Status.CHECKED;

            default:
                return null;

        }
    }
}
