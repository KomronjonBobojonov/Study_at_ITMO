package enums;

public enum Things {
    HOUSE("домик"), BUILDING("здание"), HERE("Сюда"), GARAGE("ГАРАЖ"), CASTLE("замок");
    private  final String name;

    Things(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

}
