package enums;

public enum Things {
    House("домик"), Building("здание");
    private  final String name;

    Things(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

}
