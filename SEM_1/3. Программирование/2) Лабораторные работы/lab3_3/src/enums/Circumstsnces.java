package enums;

public enum Circumstsnces {
    Comeing("возвращаясь"), GoOut("уходя"), AllFootsToHim("so vsekh nog");

    private final String name;

    Circumstsnces(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
