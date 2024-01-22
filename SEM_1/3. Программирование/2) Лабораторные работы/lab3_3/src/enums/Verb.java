package enums;

public enum Verb {
    JUMP("прыгал"), ASK("спросить"), GO("идти"), RUSHED("бросился"), APPEARED("показался");
    private  final String name;

    Verb(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

}
