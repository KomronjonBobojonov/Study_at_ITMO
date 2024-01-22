package enums;

public enum Pronouns {
    HER("ней"), HIM("нему"), US("нам"), THEM("им");

    private final String name;

    Pronouns(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
