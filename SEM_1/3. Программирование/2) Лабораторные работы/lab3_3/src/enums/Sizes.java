package enums;

public enum Sizes {
    BIG("ОГРОМНЫМИ"),SMALL("Маленькими");
    private  final String name;

    Sizes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
