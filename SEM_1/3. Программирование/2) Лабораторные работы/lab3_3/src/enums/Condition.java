package enums;

public enum Condition {
    AMAZING("восхитительно"), INCOMPARABLE("бесподобно"), HAPPY("весело");

    private final String name;

    Condition(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
