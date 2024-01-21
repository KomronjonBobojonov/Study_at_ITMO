package enums;

public enum Circumstsnces {
    Comeing("возвращаясь"), GoOut("уходя"), AllFootsToHim("со всех ног"), Therewas("там был");

    private final String name;

    Circumstsnces(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
