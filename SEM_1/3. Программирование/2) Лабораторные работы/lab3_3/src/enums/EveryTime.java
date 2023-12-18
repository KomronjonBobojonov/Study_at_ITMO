package enums;

public enum EveryTime {
    Time("понинутно"), Amazing("восхитительно"), Incomparable("бесподобно");

    private final String name;

    EveryTime(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
