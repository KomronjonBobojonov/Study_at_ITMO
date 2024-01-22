package enums;

public enum Sizes {
    BIG("ОГРОМНЫМИ"),SMALL("Маленькими"), MEDIUM("Средними");
    private  final String name;

    Sizes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
