package enums;

public enum Verb {
    Jump("прыгал"), Ask("спросить"), Go("идти"), Rushed("бросился"), Appeared("показался");
    private  final String name;

    Verb(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

}
