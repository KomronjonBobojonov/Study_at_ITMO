package enums;

public enum Pronouns {
    Her("ней"), Him("нему"), Us("нам");

    private final String name;

    Pronouns(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
