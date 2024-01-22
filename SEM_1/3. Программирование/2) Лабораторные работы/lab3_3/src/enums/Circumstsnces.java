package enums;

public enum Circumstsnces {
    COMEING("возвращаясь"), GO_OUT("уходя"), ALL_FOOTS_TO_HIM("со всех ног"), THEREWAS("там был");

    private final String name;

    Circumstsnces(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
