package enums;

public enum Condition {
    Amazing("восхитительно"), Incomparable("бесподобно"), Happy("весело");

    private final String name;

    Condition(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
