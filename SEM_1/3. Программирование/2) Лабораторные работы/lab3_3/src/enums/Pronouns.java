package enums;

public enum Pronouns {
    Her("ney"), Him("nemu"), Us("нам");// AllFootsToHim("so vsekh nog");

    private final String name;

    Pronouns(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
